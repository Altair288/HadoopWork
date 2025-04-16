package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Put_File {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop330:9100");
        FileSystem fs = FileSystem.get(conf);
        Path fromPath = new Path("src/main/resources/raceData.csv");
        Path toPath = new Path("/user/root/view_log/raceData.csv");
        fs.copyFromLocalFile(fromPath, toPath);
        System.out.println("File copied from " + fromPath.toString() + " to " + toPath.toString());
        fs.close();
    }
}
