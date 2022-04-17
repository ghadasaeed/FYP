package com.example.fyp.groups;

public class Group {

    private String GroupName, ImageURL, RandomUID, UserId;

    public Group(String groupName, String imageURL, String randomUID, String userId) {

        GroupName = groupName;
        ImageURL = imageURL;
        RandomUID = randomUID;
        UserId = userId;
    }

    public Group(){}

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getRandomUID() {
        return RandomUID;
    }

    public void setRandomUID(String randomUID) {
        RandomUID = randomUID;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
