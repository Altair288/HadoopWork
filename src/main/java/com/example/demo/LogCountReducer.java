package com.example.demo;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LogCountReducer extends Reducer<MemberLogTime, IntWritable, MemberLogTime, IntWritable> {
    @Override
    protected void reduce(MemberLogTime key, Iterable<IntWritable> values, Reducer<MemberLogTime, IntWritable, MemberLogTime, IntWritable>.Context context) throws IOException, InterruptedException {
        if (key.getLogTime().contains("2021/1")) {
            context.getCounter("OutPutCounter", "JanuaryResult").increment(1);
        } else if (key.getLogTime().contains("2021/2")) {
            context.getCounter("OutPutCounter", "FebruaryResult").increment(1);
        }

        int sum = 0;
        for (IntWritable iw : values) {
            sum += iw.get();
        }
        context.write(key, new IntWritable(sum));
    }
}