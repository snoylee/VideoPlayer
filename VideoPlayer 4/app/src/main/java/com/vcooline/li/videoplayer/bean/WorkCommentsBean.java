package com.vcooline.li.videoplayer.bean;

import com.vcooline.li.videoplayer.tools.JSONUtil;

import org.json.JSONObject;

/**
 * Created by Trace on 2014/8/31.
 */
public class WorkCommentsBean {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkPic() {
        return workPic;
    }

    public void setWorkPic(String workPic) {
        this.workPic = workPic;
    }

    public String getPersionId() {
        return persionId;
    }

    public void setPersionId(String persionId) {
        this.persionId = persionId;
    }

    public String getPersionName() {
        return persionName;
    }

    public void setPersionName(String persionName) {
        this.persionName = persionName;
    }

    public boolean isCommited() {
        return isCommited;
    }

    public void setCommited(boolean isCommited) {
        this.isCommited = isCommited;
    }

    public String getCommitText() {
        return commitText;
    }

    public void setCommitText(String commitText) {
        this.commitText = commitText;
    }

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

    private int id;
    private String workName;
    private String workPic;
    private String persionId;
    private String persionName;
    private boolean isCommited;
    private String commitText;
    private long createTime;
    private long updateTime;


    public static WorkCommentsBean getWorkComment(JSONObject jsonObject){
        WorkCommentsBean workCommentsBean = new WorkCommentsBean();
        JSONUtil jsonUtil = new JSONUtil(jsonObject);
        workCommentsBean.setId(jsonUtil.optInteger("id"));
        workCommentsBean.setWorkName(jsonUtil.optString("workName"));
        workCommentsBean.setWorkPic(jsonUtil.optString("workPic"));
        workCommentsBean.setPersionId(jsonUtil.optString("personId"));
        workCommentsBean.setPersionName(jsonUtil.optString("personName"));
        workCommentsBean.setCommited(jsonUtil.optBoolean("isComment"));
        workCommentsBean.setUpdateTime(jsonUtil.optLong("createAt"));
        workCommentsBean.setUpdateTime(jsonUtil.optLong("updateAt"));
        workCommentsBean.setCommitText(jsonUtil.optString("commentInfo"));
        return workCommentsBean;
    }
}
