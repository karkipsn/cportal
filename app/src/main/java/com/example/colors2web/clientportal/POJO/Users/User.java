
package com.example.colors2web.clientportal.POJO.Users;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("activated")
    private Boolean mActivated;
    @SerializedName("activated_at")
    private Object mActivatedAt;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("customer_id")
    private Long mCustomerId;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("first_name")
    private String mFirstName;
    @SerializedName("id")
    private Long mId;
    @SerializedName("last_login")
    private String mLastLogin;
    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("login_token")
    private String mLoginToken;
    @SerializedName("permissions")
    private List<Object> mPermissions;
    @SerializedName("remember_token")
    private Object mRememberToken;
    @SerializedName("reset_password")
    private String mResetPassword;
    @SerializedName("special_program")
    private String mSpecialProgram;
    @SerializedName("updated_at")
    private String mUpdatedAt;
    @SerializedName("user")
    private User mUser;
    @SerializedName("userGroup")
    private List<String> mUserGroup;

    public Boolean getActivated() {
        return mActivated;
    }

    public void setActivated(Boolean activated) {
        mActivated = activated;
    }

    public Object getActivatedAt() {
        return mActivatedAt;
    }

    public void setActivatedAt(Object activatedAt) {
        mActivatedAt = activatedAt;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public Long getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(Long customerId) {
        mCustomerId = customerId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getLastLogin() {
        return mLastLogin;
    }

    public void setLastLogin(String lastLogin) {
        mLastLogin = lastLogin;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getLoginToken() {
        return mLoginToken;
    }

    public void setLoginToken(String loginToken) {
        mLoginToken = loginToken;
    }

    public List<Object> getPermissions() {
        return mPermissions;
    }

    public void setPermissions(List<Object> permissions) {
        mPermissions = permissions;
    }

    public Object getRememberToken() {
        return mRememberToken;
    }

    public void setRememberToken(Object rememberToken) {
        mRememberToken = rememberToken;
    }

    public String getResetPassword() {
        return mResetPassword;
    }

    public void setResetPassword(String resetPassword) {
        mResetPassword = resetPassword;
    }

    public String getSpecialProgram() {
        return mSpecialProgram;
    }

    public void setSpecialProgram(String specialProgram) {
        mSpecialProgram = specialProgram;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public List<String> getUserGroup() {
        return mUserGroup;
    }

    public void setUserGroup(List<String> userGroup) {
        mUserGroup = userGroup;
    }

}
