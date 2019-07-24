package com.lion.lab.singleton;

public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {

    }

    public static Singleton getStaticInnerClassInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static Singleton getDoubleLockInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    private static class SingletonHolder {
        private static Singleton INSTANCE = new Singleton();
    }
}
