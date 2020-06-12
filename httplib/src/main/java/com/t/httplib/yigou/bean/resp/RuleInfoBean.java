package com.t.httplib.yigou.bean.resp;

/**
 * 系统规则
 */
public class RuleInfoBean {

    private String template;
    private long addTime;
    private String description;
    private long updateTime;
    private int id;
    private String title;
    private String singleKey;
    private String content;
    private String singleContent;
    private int status;

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingleKey() {
        return singleKey;
    }

    public void setSingleKey(String singleKey) {
        this.singleKey = singleKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSingleContent() {
        return singleContent;
    }

    public void setSingleContent(String singleContent) {
        this.singleContent = singleContent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
