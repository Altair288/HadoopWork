package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Load_File {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop330:9100");
        FileSystem fs = FileSystem.get(conf);
        Path frompath = new Path("/user/root/view_log/raceData.csv");
        Path topath = new Path("src/main/resources/download/raceData.csv");
        fs.copyToLocalFile(false, frompath, topath, true);
        System.out.println("File copied from " + frompath.toString() + " to " + topath.toString());
        fs.close();
    }
}
