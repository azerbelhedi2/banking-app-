# Start from Ubuntu OS image
FROM ubuntu:latest

# set root user
USER root

# install utilities on up-to-date node
RUN apt-get update && apt-get -y dist-upgrade
RUN apt-get install -y openssh-server openjdk-8-jdk wget nano python2 zookeeperd unzip 

# set java home
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

# setup ssh with no passphrase
RUN ssh-keygen -t rsa -f $HOME/.ssh/id_rsa -P "" \
    && cat $HOME/.ssh/id_rsa.pub >> $HOME/.ssh/authorized_keys

# download & extract & move hadoop & clean up
# TODO: write a way of untarring file to "/usr/local/hadoop" directly
RUN wget -O /hadoop.tar.gz -q https://archive.apache.org/dist/hadoop/core/hadoop-2.7.3/hadoop-2.7.3.tar.gz \
	&& tar xfz hadoop.tar.gz \
	&& mv /hadoop-2.7.3 /usr/local/hadoop \
	&& rm /hadoop.tar.gz

# download & extract & move hbase & clean up
# TODO: write a way of untarring file to "/usr/local/hbase" directly
RUN wget -O /hbase.tar.gz https://archive.apache.org/dist/hbase/stable-1/hbase-1.4.13-bin.tar.gz \
	&& tar xfz hbase.tar.gz \
	&& mv /hbase-1.4.13 /usr/local/hbase \
	&& rm /hbase.tar.gz
# download & extract & move phoenix & clean up
# TODO: write a way of untarring file to "/usr/local/hbase/phoenix" directly
RUN wget -O /phoenix.tar.gz http://archive.apache.org/dist/phoenix/apache-phoenix-4.14.0-HBase-1.4/bin/apache-phoenix-4.14.0-HBase-1.4-bin.tar.gz \
	&& tar xfz phoenix.tar.gz \
	&& mv /apache-phoenix-4.14.0-HBase-1.4-bin /usr/local/hbase/phoenix \
    && cp /usr/local/hbase/phoenix/phoenix-4.14.0-HBase-1.4-server.jar /usr/local/hbase/lib/ \
	&& rm /phoenix.tar.gz
# download & extract & move zookeeper & clean up
# TODO: write a way of untarring file to "/usr/local/hbase/zookeeper" directly

# hadoop environment variables
ENV HADOOP_HOME=/usr/local/hadoop
ENV HBASE_HOME=/usr/local/hbase
ENV PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$HBASE_HOME/bin

# hadoop-store
RUN mkdir -p $HADOOP_HOME/hdfs/namenode \
	&& mkdir -p $HADOOP_HOME/hdfs/datanode

# setup configs - [standalone, pseudo-distributed mode, fully distributed mode]
# NOTE: Directly using COPY/ ADD will NOT work if you are NOT using absolute paths inside the docker image.
# Temporary files: http://refspecs.linuxfoundation.org/FHS_3.0/fhs/ch03s18.html
COPY config/ /tmp/
RUN mv /tmp/ssh_config $HOME/.ssh/config \
    && mv /tmp/hadoop-env.sh $HADOOP_HOME/etc/hadoop/hadoop-env.sh \
    && mv /tmp/core-site.xml $HADOOP_HOME/etc/hadoop/core-site.xml \
    && mv /tmp/hdfs-site.xml $HADOOP_HOME/etc/hadoop/hdfs-site.xml \
    && mv /tmp/mapred-site.xml $HADOOP_HOME/etc/hadoop/mapred-site.xml.template \
    && cp $HADOOP_HOME/etc/hadoop/mapred-site.xml.template $HADOOP_HOME/etc/hadoop/mapred-site.xml \
    && mv /tmp/yarn-site.xml $HADOOP_HOME/etc/hadoop/yarn-site.xml \
    && cp /tmp/slaves $HADOOP_HOME/etc/hadoop/slaves \
    && mv /tmp/slaves $HBASE_HOME/conf/regionservers \
    && mv /tmp/hbase/hbase-env.sh $HBASE_HOME/conf/hbase-env.sh \
    && mv /tmp/hbase/hbase-site.xml $HBASE_HOME/conf/hbase-site.xml

RUN cp /usr/local/hbase/conf/hbase-site.xml /usr/local/hbase/phoenix/bin/
# Add startup script
ADD scripts/hadoop-services.sh $HADOOP_HOME/hadoop-services.sh

# set permissions
RUN chmod 744 -R $HADOOP_HOME
RUN chmod 744 -R $HBASE_HOME

# format namenode
RUN $HADOOP_HOME/bin/hdfs namenode -format

# run hadoop services
ENTRYPOINT service ssh start; cd $HBASE_HOME;$HADOOP_HOME/hadoop-services.sh; bash

