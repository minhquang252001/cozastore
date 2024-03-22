package com.example.cozastoreweb.controller;

import com.example.cozastoreweb.entity.UsersEntity;
import com.example.cozastoreweb.jwt.JwtHelper;
import com.example.cozastoreweb.payload.response.BaseResponse;
import com.example.cozastoreweb.service.imp.LoginServiceIMP;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
//Viết API và có trả ra Http
@RestController
//Định nghĩa tên đường dẫn
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private LoginServiceIMP loginServiceIMP;

    private Gson gson = new Gson();


    @GetMapping("/demo")
    public ResponseEntity<?> getLogin(){
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String jwtKey = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println("key " + jwtKey);

        return new ResponseEntity<>("demo",HttpStatus.OK);
    }

    //Method:POST
    @PostMapping("")
    public ResponseEntity<?> checkLogin(@RequestParam String username,@RequestParam String password){
    //Trả ra kết quả | ResponseEntity: có thể trả ra theo định dạng người dùng | HttpStatus.OK là kết quả 200
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        //Chuyển về dạng Json
        String json = gson.toJson( authentication.getAuthorities());
        //Tạo mã hóa SecreKey
        String jwtToken = jwtHelper.generateToken(json);
        String jwtTokenEmail = jwtHelper.generateToken(username);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(jwtTokenEmail);
        baseResponse.setData(jwtToken);

        // lưu trữ Logger khi đăng nhập thành công
        if(authentication != null){
            logger.info("Username " + username);
        }
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/inser")
    public ResponseEntity<?> inserLogin(@RequestParam String username,@RequestParam String password ,@RequestParam String password2){
        Boolean usersEntity = loginServiceIMP.save(username,password,password2);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Tạo tài khoản thành công");
        baseResponse.setData(usersEntity);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update (
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String password1,
            @RequestParam String password2
            ){
        Boolean usersEntity = loginServiceIMP.update(email,password,password1,password2);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Đổi mật khẩu thành công");
        baseResponse.setData(usersEntity);

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/email")
    public ResponseEntity<?> findByEmail(@RequestParam String email){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Hiển thị id khi login ");
        baseResponse.setData(loginServiceIMP.findByEmail(email));
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
