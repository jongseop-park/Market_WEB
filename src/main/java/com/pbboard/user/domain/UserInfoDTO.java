package com.pbboard.user.domain;

public class UserInfoDTO {
    /** 아이디 */
    private String id;

    /** 비밀번호 */
    private String password;

    /** 권한 */
    private String auth;

    /** 이름 */
    private String name;

    /** 연락처 */
    private String phone;

    /** 이메일 */
    private String email;

    /** 닉네임 */
    private String nickName;

    /** 주소 */
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
