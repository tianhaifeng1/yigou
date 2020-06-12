package com.xzte;

import java.util.List;

public class TestRecyclerOutInfoBean {


    private String name;

    private List<TestRecyclerInInfoBean> inList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestRecyclerInInfoBean> getInList() {
        return inList;
    }

    public void setInList(List<TestRecyclerInInfoBean> inList) {
        this.inList = inList;
    }

    public static class TestRecyclerInInfoBean{

        private String name;
        private String path;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }


}
