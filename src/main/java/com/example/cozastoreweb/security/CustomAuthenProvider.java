package com.example.cozastoreweb.security;

import com.example.cozastoreweb.entity.UsersEntity;
import com.example.cozastoreweb.service.imp.LoginServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenProvider implements AuthenticationProvider {

    @Autowired
    private LoginServiceIMP loginServiceIMP;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        UsersEntity usersEntity = loginServiceIMP.checklogin(username,password);
        if (usersEntity !=null){
            // Tạo ra 1 cái list  nhận vào danh sách quyền theo chuẩn Security
            List<GrantedAuthority> listRole = new ArrayList<>();
            SimpleGrantedAuthority role = new SimpleGrantedAuthority(usersEntity.getRole().getName());
            listRole.add(role);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken("","",listRole);
            return authenticationToken;
        }

            return null;

    }
    //So sánh UsernamePasswordAuthenticationToken.class khác thì trả ra fales
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
