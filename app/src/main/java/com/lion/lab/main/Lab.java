package com.lion.lab.main;

class Lab {
    private String topic;
    private Class clazz;

    Lab(String topic, Class clazz) {
        this.topic = topic;
        this.clazz = clazz;
    }

    String getTopic() {
        return topic;
    }

    Class getClazz() {
        return clazz;
    }
}
