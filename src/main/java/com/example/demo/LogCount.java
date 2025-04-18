package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Properties;

public class LogCount {
    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Properties properties = System.getProperties();
        properties.setProperty("HADOOP_USER_NAME", "root");
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
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}