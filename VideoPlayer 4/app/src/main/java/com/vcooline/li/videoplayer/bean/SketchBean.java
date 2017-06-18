package com.vcooline.li.videoplayer.bean;

import com.vcooline.li.videoplayer.tools.JSONUtil;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Trace on 2014/8/18.
 */
public class SketchBean implements Serializable {
    private String id;
    private String materialName;
    private String materialPath;
    private String materialKey;
    private String materialType;

    private String materialClasses;
    private String materialSubClasses;
    private String isMediaImage;  // 存储的时候用 0 1
    private String thumbnailName;
    private String thumbnailPath;

    private String finishedName;
    private String finishedPath;
    private String finishedHdName;
    private String finishedHdPath;
    private String sortId;

    private String classSort;
    private String uploadedAt;
    private String createAt;
    private String updateAt;
    private String status;


    public String getClassSort() {
        return classSort;
    }

    public void setClassSort(String classSort) {
        this.classSort = classSort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialPath() {
        return materialPath;
    }

    public void setMaterialPath(String materialPath) {
        this.materialPath = materialPath;
    }

    public String getMaterialKey() {
        return materialKey;
    }

    public void setMaterialKey(String materialKey) {
        this.materialKey = materialKey;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialClasses() {
        return materialClasses;
    }

    public void setMaterialClasses(String materialClasses) {
        this.materialClasses = materialClasses;
    }

    public String getMaterialSubClasses() {
        return materialSubClasses;
    }

    public void setMaterialSubClasses(String materialSubClasses) {
        this.materialSubClasses = materialSubClasses;
    }

    public String isMediaImage() {
        return isMediaImage;
    }

    public void setMediaImage(String isMediaImage) {
        this.isMediaImage = isMediaImage;
    }

    public String getThumbnailName() {
        return thumbnailName;
    }

    public void setThumbnailName(String thumbnailName) {
        this.thumbnailName = thumbnailName;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getFinishedName() {
        return finishedName;
    }

    public void setFinishedName(String finishedName) {
        this.finishedName = finishedName;
    }

    public String getFinishedPath() {
        return finishedPath;
    }

    public void setFinishedPath(String finishedPath) {
        this.finishedPath = finishedPath;
    }

    public String getFinishedHdName() {
        return finishedHdName;
    }

    public void setFinishedHdName(String finishedHdName) {
        this.finishedHdName = finishedHdName;
    }

    public String getFinishedHdPath() {
        return finishedHdPath;
    }

    public void setFinishedHdPath(String finishedHdPath) {
        this.finishedHdPath = finishedHdPath;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(String uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static SketchBean getSketchBeanByJson(JSONObject jsonObject) {
        JSONUtil jsonUtil = new JSONUtil(jsonObject);
        SketchBean sketchBean = new SketchBean();
        sketchBean.setId(jsonUtil.optString("id"));
        sketchBean.setMaterialName(jsonUtil.optString("materialName"));
        sketchBean.setMaterialPath(jsonUtil.optString("materialPath"));
        sketchBean.setMaterialKey(jsonUtil.optString("materialKey"));
        sketchBean.setMaterialType(jsonUtil.optString("materialType"));
        sketchBean.setMaterialClasses(jsonUtil.optString("materialClasses"));
        sketchBean.setMaterialSubClasses(jsonUtil.optString("materialSubClasses"));
        sketchBean.setMediaImage(jsonUtil.optString("isMediaImage"));
        sketchBean.setThumbnailName(jsonUtil.optString("thumbnailName"));
        sketchBean.setThumbnailPath(jsonUtil.optString("thumbnailPath"));
        sketchBean.setFinishedHdName(jsonUtil.optString("finishedName"));
        sketchBean.setFinishedPath(jsonUtil.optString("finishedPath"));
        sketchBean.setFinishedHdName(jsonUtil.optString("finishedHdName"));
        sketchBean.setFinishedHdPath(jsonUtil.optString("finishedHdPath"));
        sketchBean.setSortId(jsonUtil.optString("sortId"));
        sketchBean.setClassSort(jsonUtil.optString("classesSort"));

        return sketchBean;
    }
}
