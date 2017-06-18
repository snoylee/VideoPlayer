package com.vcooline.li.videoplayer.bean;

import com.vcooline.li.videoplayer.tools.JSONUtil;

import org.json.JSONObject;

/**
 * Created by Trace on 2014/9/1.
 */
public class WwComment {
    public String getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(String commentatorId) {
        this.commentatorId = commentatorId;
    }

    public String getCommentatorName() {
        return commentatorName;
    }

    public void setCommentatorName(String commentatorName) {
        this.commentatorName = commentatorName;
    }

    public String getImagesId() {
        return imagesId;
    }

    public void setImagesId(String imagesId) {
        this.imagesId = imagesId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    private String commentatorId;
    private String commentatorName;
    private String imagesId;
    private String comment;
    private long createAt;

    public static WwComment getWwCommentByJson(JSONObject jsonObject){
        WwComment wwComment = new WwComment();
        JSONUtil jsonUtil = new JSONUtil(jsonObject);
        wwComment.setCommentatorId(jsonUtil.optString("commentatorId"));
        wwComment.setCommentatorName(jsonUtil.optString("commentatorName"));
        wwComment.setImagesId(jsonUtil.optString("imagesId"));
        wwComment.setComment(jsonUtil.optString("comment"));
        wwComment.setCreateAt(jsonUtil.optLong("createAt"));
        return wwComment;
    }

}
