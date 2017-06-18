package com.vcooline.li.videoplayer.bean;

import com.vcooline.li.videoplayer.tools.JSONUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Trace on 2014/8/31.
 */
public class WorksExchange {
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public ArrayList<WwComment> getWwComments() {
        return wwComments;
    }

    public void setWwComments(ArrayList<WwComment> wwComments) {
        this.wwComments = wwComments;
    }

    private int personId;
    private String personName;
    private String imageName;
    private String imageUrl;
    private String imageKey;
    private String description;
    private long createAt;
    private ArrayList<WwComment> wwComments = new ArrayList<WwComment>();

    public static WorksExchange getWorkExchangeByJson(JSONObject jsonObject){
        WorksExchange worksExchange = new WorksExchange();
        JSONUtil jsonUtil = new JSONUtil(jsonObject);
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("wwComments");
            for(int i=0; i<jsonArray.length(); i++){
                worksExchange.getWwComments().add(WwComment.getWwCommentByJson((JSONObject)jsonArray.get(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        worksExchange.setPersonId(jsonUtil.optInteger("personId"));
        worksExchange.setPersonName(jsonUtil.optString("personName"));
        worksExchange.setImageName(jsonUtil.optString("imageName"));
        worksExchange.setImageKey(jsonUtil.optString("imageUrl"));
        worksExchange.setImageKey(jsonUtil.optString("imageKey"));
        worksExchange.setDescription(jsonUtil.optString("description"));
        worksExchange.setCreateAt(jsonObject.optLong("createAt"));

        return worksExchange;
    }

}
