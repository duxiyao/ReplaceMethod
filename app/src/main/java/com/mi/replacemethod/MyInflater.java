package com.mi.replacemethod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

/**
 * create by niuxiaowei
 * date : 21-12-15
 **/
public class MyInflater {
    public static View abc(Context context, @LayoutRes int resource, ViewGroup root) {
        LayoutInflater factory = LayoutInflater.from(context);
        return factory.inflate(resource, root);
    }

    public static View inflate(Context context, @LayoutRes int resource, ViewGroup root) {
        LayoutInflater factory = LayoutInflater.from(context);
        return factory.inflate(resource, root);
    }

    private static int aaa = 0;

    public static void testtt() {
        MyInflater.testa();
        MyInflater.abcd();
        new MyInflater().dxya();
    }

    public static void abcd(){}

    public void dxya() {
        android.util.Log.e("-----", "111111111");
    }

    public static void testa() {
        android.util.Log.e("-----", "111111111");
    }


    private String aa = "";

    public void test() {
        this.aa = "1";
        test1(1);
    }

    public int test1(int a) {
        int c = 1;
        int b = a + c;
        return b;
    }


    public static void testb(Object[] params) {
        android.util.Log.e("-----", "22222222");
        MyInflater.testa();
    }

    public static void dxyb(MyInflater o, Object[] params) {
        android.util.Log.e("-----", "3333333");

        o.dxya();
    }
}
