package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Mkdir_Folders {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop330:9100");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/user/root/view_log");
        fs.mkdirs(path);
        System.out.println("Directory created: " + path.toString());
        fs.close();
    }
}
