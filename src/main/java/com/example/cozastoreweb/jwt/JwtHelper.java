package com.example.cozastoreweb.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {

    @Value("${token.key}")
    private String strKey;
    //Thời gian hết hạn
    private int expired = 8 * 60 * 60 * 1000;

    private int tokenEmai = 60000;

    public String generateToken(String data){
        //Chuyển key từ base64 về lại Secretkey
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
            Date date = new Date();
            //getTime() trả ra milisecons
            long futureMilis= date.getTime() + expired;
            //giờ hiện tại truyền futureMilis vào để thời giạn hết hạn sau 8 tiếng.
            Date futureDate = new Date(futureMilis);
            return Jwts.builder().subject(data).expiration(futureDate).signWith(key).compact();
        }catch (Exception e){
            throw new RuntimeException("Lỗi GenerateToken " + e.getMessage());
        }
    }

    public String decodeToken(String data){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(data).getPayload().getSubject();
        }catch (Exception e){
            throw new RuntimeException("Lỗi DecodeToken " + e.getMessage());
        }

    }

}
