package com.xzte.test;

import java.util.List;

public abstract class Demo1<T> {

    private List<T> list;

    public void setList(List<T> list) {
        this.list = list;
    }

    public void addList(T bean){
        list.add(bean);
    }

    public abstract List<T> removeBean(int index);


}
