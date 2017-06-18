package com.vcooline.li.videoplayer.bean;

import java.io.Serializable;

/**
 * Created by Trace on 2014/8/23.
 */
public class InformationBean implements Serializable {
    private int id;
    private String type;
    private String title;
    private String content;
    private String appenddix;
    private String pictureUrl;
    private String download;
    private long createTime;
    private long updateTime;


    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAppenddix() {
        return appenddix;
    }

    public void setAppenddix(String appenddix) {
        this.appenddix = appenddix;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }
}
