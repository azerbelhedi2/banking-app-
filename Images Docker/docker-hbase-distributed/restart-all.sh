#!/bin/bash

# variables
IMG_NAME="hadoop-hbase:latest"
HOST_PREFIX="hadoop"
NETWORK_NAME=$HOST_PREFIX

# if desired, clean up containers
#docker stop $(docker ps -a -q)
#docker rm $(docker ps -a -q)

# if desired, clean up images
#docker rmi $(docker images -q)

# total number of slave nodes
N=${1:-3}
rm -f config/slaves
i=1
while [ $i -le $N ]
do
	HADOOP_SLAVE="$HOST_PREFIX"-slave-$i
	echo $HADOOP_SLAVE >> config/slaves
	i=$(( $i + 1 ))
done

/bin/cp -rf config/hbase/hbase-site.template.xml   config/hbase/hbase-site.xml
ZOOKEEPER_CONNECT=""
N=${1:-3}
INIT=1
i=$INIT
while [ $i -le $N ]
do
	HADOOP_SLAVE="$HOST_PREFIX"-slave-$i
	ZOOKEEPER_CONNECT="$ZOOKEEPER_CONNECT$HADOOP_SLAVE:2181"
	if [ $i -lt $N ]
	then
		ZOOKEEPER_CONNECT="$ZOOKEEPER_CONNECT,"
	fi

	i=$(( $i + 1 ))
done
sed -i -e "s/__quorum_nodes__/$ZOOKEEPER_CONNECT/g" config/hbase/hbase-site.xml
sed -i -e "s/__master_node__/$HOST_PREFIX-master/g" config/hbase/hbase-site.xml

# build the Dockerfile
docker build  -t "$IMG_NAME" "$(pwd)"

# Default docker network name is 'bridge', driver is 'bridge', scope is 'local'
# Hadoop multi-node cluster does NOT work on default network.
# Create a new network with any name, and keep 'bridge' driver for 'local' scope.
NET_QUERY=$(docker network ls | grep -i $NETWORK_NAME)
if [ -z "$NET_QUERY" ]; then
	docker network create --driver=bridge $NETWORK_NAME
fi

# start hadoop slave container(s)
i=1
while [ $i -le $N ]
do
	HADOOP_SLAVE="$HOST_PREFIX"-slave-$i
	docker run --name $HADOOP_SLAVE -h $HADOOP_SLAVE.$NETWORK_NAME --net=$NETWORK_NAME -p 218$i:2181 -itd "$IMG_NAME" 
	i=$(( $i + 1 ))
done

# start hadoop master container
: '

Daemon                   Default Port  Configuration Parameter
-----------------------  ------------ ----------------------------------
ResourceManager          9800         yarn.resourcemanager.webapp.address
Namenode                 9770        dfs.http.address
Secondarynamenode        9790        dfs.secondary.http.address
HMaster			         16010
'
HADOOP_MASTER="$HOST_PREFIX"-master
docker run --name $HADOOP_MASTER -h $HADOOP_MASTER.$NETWORK_NAME --net=$NETWORK_NAME \
		-p  9800:8088  -p 9770:50070 -p 9790:50090 -p 9801:8080 -p 9802:8085 \
		-p 16010:16010 \
		-itd "$IMG_NAME"

# see active docker containers
docker ps

# start multi-node cluster
docker exec -it $HADOOP_MASTER "/usr/local/hadoop/hadoop-services.sh"

# attach to hadoop master container
docker attach $HADOOP_MASTER
