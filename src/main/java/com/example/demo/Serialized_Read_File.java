package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.BufferedWriter; 
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Serialized_Read_File {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9100");
        FileSystem fs = FileSystem.get(conf);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path("/user/root/result/1/part-m-00000"), conf);
        Text key = new Text();
        Text value = new Text();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/main/resources/download/janfeb.txt")));
        while (reader.next(key, value)) {
            out.write(key.toString() + "\t" + value.toString()+"\r\n");
        }
        out.close();
        reader.close();
        System.out.println("Data written to file successfully.");
    }    
}
