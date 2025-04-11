package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class View_folders {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","master:8020");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/root/user");
        FileStatus[] fileStatuses = fs.listStatus(path);
        for (FileStatus file : fileStatuses) {
            if (file.isDirectory()) {
                System.out.println(file.getPath().toString());
            }
        }
        fs.close();
    }
}
