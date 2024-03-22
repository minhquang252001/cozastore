package com.example.cozastoreweb.service;

import com.example.cozastoreweb.dto.UsersDTO;
import com.example.cozastoreweb.entity.RoleEntity;
import com.example.cozastoreweb.entity.UsersEntity;
import com.example.cozastoreweb.exception.ProductNotfoundException;
import com.example.cozastoreweb.jwt.JwtHelper;
import com.example.cozastoreweb.repository.LoginRepository;
import com.example.cozastoreweb.service.imp.LoginServiceIMP;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements LoginServiceIMP {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginRepository loginRepository;

    private Logger logger = LoggerFactory.getLogger(LoginService.class);

    private Gson gson = new Gson();


    @Override
    public UsersEntity checklogin(String username, String password) {
        UsersEntity usersEntity = loginRepository.findByEmail(username);
        //Nếu username tồn tại thì sẽ vào điều kiện if bên trong còn không sẽ return null
        if(usersEntity !=null){
            //Nếu password người dùng nhập khác password trong UserEntity trả ra null
            if(passwordEncoder.matches(password,usersEntity.getPassword())){
                return usersEntity;
            }
        }

        return null;
    }

    @Override
    public Boolean save(String email, String password,String password2) {
        String passwordJwt= passwordEncoder.encode(password);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setEmail(email);
        usersEntity.setPassword(passwordJwt);
        RoleEntity role = new RoleEntity();
        role.setId(2);
        usersEntity.setRole(role);
        Optional<UsersEntity> checkemail = loginRepository.getByEmail(usersEntity.getEmail());
        if (!checkemail.isPresent()){
            if (password.equals(password2)) {
                String emailRegex = "^[A-Za-z0-9._%+-]+@gmail.com$";
                if (!email.matches(emailRegex)){
                    throw new ProductNotfoundException("Email phải có định dạng là @gmail.com");
                }
                UsersEntity users = loginRepository.save(usersEntity);
                return true;
            }else {
                throw new ProductNotfoundException("Mật khẩu nhập lại không trùng khớp");

            }
        }else {
            throw new ProductNotfoundException("Email đã tồn tại ");
        }

    }


    @Override
    public Boolean update(String email,String password,String password1,String password2) {
        UsersEntity usersEntity = loginRepository.findByEmail(email);
        if (usersEntity != null) {
            if (passwordEncoder.matches(password, usersEntity.getPassword())) {
                if (password1.equals(password2)){
                    String passwordJwt = passwordEncoder.encode(password1);
                    usersEntity.setPassword(passwordJwt);
                    loginRepository.save(usersEntity);
                    return true;
                }else {
                    throw new ProductNotfoundException("Password Nhập lại không trùng khớp");
                }
            }else{
                throw new ProductNotfoundException("Mật Khẩu cũ không trùng khớp");
            }
        } else {
            throw new ProductNotfoundException("Email Không Tồn tại ");
        }
    }


    @Override
    public Optional<UsersEntity> checkEmail(String email) {
        return loginRepository.getByEmail(email);
    }

    @Override
    public UsersDTO findByEmail(String email) {
        String encodeToken = jwtHelper.decodeToken(email);
            System.out.println("Kiểm tra usersEntity có tồn tại");
            if (encodeToken !=null){
                UsersEntity usersEntity = loginRepository.findByEmail(encodeToken);

                UsersDTO usersDTO = new UsersDTO();
                usersDTO.setId(usersEntity.getId());
                return usersDTO;

            }else {
                throw new ProductNotfoundException("Token Không trùng khớp với email");
            }
    }

}
