package com.zqw.bean;

import java.util.ArrayList;

import com.zqw.util.DBUtil;

public class MyArrayList<E> extends ArrayList<E>{
    public boolean add(E e) {
    	DBUtil.insert(e);
        super.add(e);
        return true;
    }
}
