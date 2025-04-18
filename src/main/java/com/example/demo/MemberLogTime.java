package com.example.demo;

import org.apache.hadoop.io.WritableComparable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class MemberLogTime implements WritableComparable<MemberLogTime> {
    private String member_name;
    private String logTime;

    public MemberLogTime() {}

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getLogTime() {
        return logTime;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(member_name == null ? "" : member_name);
        out.writeUTF(logTime == null ? "" : logTime);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        member_name = in.readUTF();
        logTime = in.readUTF();
    }

    @Override
    public int compareTo(MemberLogTime o) {
        int cmp = member_name.compareTo(o.member_name);
        if (cmp != 0) return cmp;
        return logTime.compareTo(o.logTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member_name, logTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MemberLogTime that = (MemberLogTime) obj;
        return Objects.equals(member_name, that.member_name) &&
               Objects.equals(logTime, that.logTime);
    }

    @Override
    public String toString() {
        return member_name + "\t" + logTime;
    }
}