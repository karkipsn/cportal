
package com.example.colors2web.clientportal.POJO.Users;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseUsers {

    @SerializedName("message")
    private String mMessage;
    @SerializedName("returnType")
    private String mReturnType;
    @SerializedName("users")
    private List<User> mUsers;

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getReturnType() {
        return mReturnType;
    }

    public void setReturnType(String returnType) {
        mReturnType = returnType;
    }

    public List<User> getUsers() {
        return mUsers;
    }

    public void setUsers(List<User> users) {
        mUsers = users;
    }

}
