package com.example.cozastoreweb.security;

import com.example.cozastoreweb.filter.JwtFilter;
import com.example.cozastoreweb.security.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// là 1 class đọc từng Bean (IOC)
@Configuration
//Được sử dụng để kích hoạt của class của mình
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private CustomAuthenProvider customAuthenProvider;

    //Lấy AuthenticationManager để chuyển sang AuthenticatinProvider
    //AuthenticationManagerBuilder.class: share đường dẫn của thằng sping manager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //Đưa lên IOC
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /**
         *  csrf().disable():tắt đi bảo mật chặn tấn công giả
         *  .authorizeHttpRequests() được phép làm những gì trong https
         *  .requestMatchers(): đường dẫn này phải chứng thực
         *  .permitAll() là được phép vào không cần chứng thực
         *  .and().build(): đóng gói trả ra kết quả.
         */
        return httpSecurity.csrf().disable()
                //Hủy bỏ Session trong project
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .requestMatchers("/login/**","/category/**","/files/**","/rabbit/**","/product-detail/**","/color/**","/size/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/product/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/product/**").permitAll()
                .requestMatchers("/login/email").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                //Add jwtFilter trước khi chạy vào Security
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
