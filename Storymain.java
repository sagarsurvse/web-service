
package com.viralmeme.viralmeme.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Storymain {

    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("ProfileUrl")
    @Expose
    private String profileUrl;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Stories")
    @Expose
    private List<Story> allIdeasList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Story> getAllIdeasList() {
        return allIdeasList;
    }

    public void setAllIdeasList(List<Story> allIdeasList) {
        this.allIdeasList = allIdeasList;
    }


}
