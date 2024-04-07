package com.example.loomdemo.util;

public enum ThreadType
{
    PLATFORM("Platform Threads"), VIRTUAL("Virtual Threads");
    private String desc;

    ThreadType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

