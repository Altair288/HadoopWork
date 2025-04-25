package com.example.demo;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.Properties;

public class LogCount extends Configured implements Tool {
    
    public static void main(String[] args)  {
        String[] myArgs = {
                "/user/root/janfeb.txt",
                "/user/root/result/LogCount2"
        };
        try {
            ToolRunner.run(new Configuration(), new LogCount(), myArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int run(String[] args) throws Exception {
        Configuration conf = getMyConfiguration(); // 用自定义配置
        System.setProperty("HADOOP_USER_NAME", "root");
        Job job = Job.getInstance(conf, "LogCount");
        job.setJarByClass(LogCount.class);
        job.setMapperClass(LogCountMapper.class);
        job.setMapOutputKeyClass(MemberLogTime.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(LogCountReducer.class);
        job.setOutputKeyClass(MemberLogTime.class);
        job.setOutputValueClass(IntWritable.class);
        job.setNumReduceTasks(2);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileSystem.get(conf).delete(new Path(args[1]), true);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        return job.waitForCompletion(true) ? 0 : 1;
    }
    public static Configuration getMyConfiguration() {
        
        Configuration conf = new Configuration();
        conf.setBoolean("mapreduce.app-submission.cross-platform", true);
        conf.set("fs.defaultFS", "hdfs://hadoop330:9100");
        conf.set("mapreduce.framework.name", "yarn");
        String resourcenode = "hadoop330";
        conf.set("yarn.resourcemanager.address", resourcenode + ":8032");
        conf.set("yarn.resourcemanager.scheduler.address", resourcenode + ":8030");
        conf.set("mapreduce.jobhistory.address", resourcenode + ":10020");
        conf.set("mapreduce.job.jar", JarUtil.jar(LogCount.class));
        return conf;
    }
}