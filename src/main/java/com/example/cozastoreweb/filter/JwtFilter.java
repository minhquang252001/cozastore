package com.example.cozastoreweb.filter;

import com.example.cozastoreweb.jwt.JwtHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

//OncePerRequestFilter:Kích hoạt khi người dùng gọi bất kì link nào

@Service
public class JwtFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private Gson gson = new Gson();

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");
        Optional optional = Optional.ofNullable(bearerToken);
        if (optional.isPresent() && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            String decode = jwtHelper.decodeToken(token);
            if (decode !=null){
                Type listType = new TypeToken<List<SimpleGrantedAuthority>>(){}.getType();
                List<GrantedAuthority> listRole = gson.fromJson(decode,listType);
                logger.info("Đăng nhập thành công " + decode);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("","",listRole);
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authenticationToken);
            }
        }






        filterChain.doFilter(request,response);
    }
}
