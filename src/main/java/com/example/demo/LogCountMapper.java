package com.example.demo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LogCountMapper extends Mapper<LongWritable, Text, MemberLogTime, IntWritable> {
    private MemberLogTime mt = new MemberLogTime();
    private IntWritable one = new IntWritable(1);

    enum LogCounter {
        January,
        February
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, MemberLogTime, IntWritable>.Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        if (fields.length < 2) return;
        String member_name = fields[0];
        String logTime = fields[1];
        if (logTime.contains("2021/1")) {
            context.getCounter(LogCounter.January).increment(1);
        } else if (logTime.contains("2021/2")) {
            context.getCounter(LogCounter.February).increment(1);
        }
        mt.setMember_name(member_name);
        mt.setLogTime(logTime);
        context.write(mt, one);
    }
}