package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class View_folders {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://hadoop330:9100");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/user/root");
        FileStatus[] fileStatuses = fs.listStatus(path);
        for (FileStatus file : fileStatuses) {
            if (file.isFile()) {
                System.out.println(file.getPath().toString());
            }
        }
        fs.close();
    }
}
