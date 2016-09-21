package com.site.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Created by wang0 on 2016/9/19.
 */

@Getter
@Setter
@ToString
public class Member implements UserDetails{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long stuId;
    private int grade;
    private String profession;
    private String loginName;
    private String pwd;
    private int group;
    private Set<Roles> roles =new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //2
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        Set<Roles> roles=this.getRoles();
        for(Roles role:roles){
            auths.add(new SimpleGrantedAuthority(role.getMark()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
