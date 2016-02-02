package com.learner.learncode.jni;

/**
 * Created by liting on 2016/1/21.
 */
public class Test {

    static{
        System.loadLibrary("test");
    }


    public native void test();
}
