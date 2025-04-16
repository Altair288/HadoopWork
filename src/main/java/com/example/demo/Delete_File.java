package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Delete_File {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop330:9100");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/user/root/raceData.csv");
        boolean isDeleted = fs.delete(path, true);
        if (isDeleted) {
            System.out.println("Directory deleted: " + path.toString());
        } else {
            System.out.println("Failed to delete directory: " + path.toString());
        }
        fs.close();
    }    
}
