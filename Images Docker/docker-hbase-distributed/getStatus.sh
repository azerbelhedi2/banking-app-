echo "<---------------------------ALL STARTED SERVICES-------------------------------------------> "
jps
sleep 5s
echo "<----------------------------STATUS HBASE MASTER AND SERVERS-------------------------------> "
echo -e 'status' | $HBASE_HOME/bin/hbase shell
sleep 5s
echo "<----------------------------STATUS HADOOP SERVERS (DATANODES)------------------------------->"
hdfs dfsadmin -report

# echo "HBase Write Benchmark: using 1 thread and no MapReduce"
# time hbase org.apache.hadoop.hbase.PerformanceEvaluation --nomapred randomWrite 1

# echo "HBase Read Benchmark: using 1 thread and no MapReduce"
# time hbase org.apache.hadoop.hbase.PerformanceEvaluation --nomapred randomRead 1

# echo "HBase Scan Benchmark: using 1 thread"
# time hbase org.apache.hadoop.hbase.PerformanceEvaluation scan 1