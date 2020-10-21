package com.pbboard.user.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserInfo implements UserDetails {
    /** 아이디 */
    private String id;

    /** 사용자 이름 */
    private String name;

    /** 비밀번호 */
    private String password;

    /** 권한 */
    private String auth;

    /** 계정 만료 여부 */
    private Boolean accountExpired;

    /** 계정 잠금 여부 */
    private Boolean accountLocked;

    /** 비밀번호 만료 여부 */
    private Boolean creExpired;

    /** 사용 가능 여부 */
    private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한을 Collection 형태로 반환
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        return roles;
    }

    @Override
    public String getPassword() {
        getAuthorities();

        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return creExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCreExpired(Boolean creExpired) {
        this.creExpired = creExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
