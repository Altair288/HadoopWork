package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Write_File {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop330:9100");
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path("/user/root/30zmz-test1.txt");
        Path newPath = new Path("/user/root/zmz/new_zmz-test1.txt");
        fs.delete(newPath, true);
        FSDataOutputStream outputStream = fs.create(newPath);
        FSDataInputStream inputStream = fs.open(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        String line = "";
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
        outputStream.close();
        reader.close();
        inputStream.close();
        fs.close();
        System.out.println("File written successfully.");
    }
}
