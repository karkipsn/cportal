
package com.example.colors2web.clientportal.POJO.login;

import com.google.gson.annotations.SerializedName;


public class ResponseLogin {

    @SerializedName("id")
    private Long mId;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("token")
    private String mToken;

    @SerializedName("type")
    private String mType;

    @SerializedName("user")
    private UserLogin mUser;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public UserLogin getUser() {
        return mUser;
    }

    public void setUser(UserLogin user) {
        mUser = user;
    }

}
