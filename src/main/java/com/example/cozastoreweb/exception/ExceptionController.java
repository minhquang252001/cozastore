package com.example.cozastoreweb.exception;

import com.example.cozastoreweb.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//dùng để lắng nghe tất cả Exception
@ControllerAdvice
public class ExceptionController {

        @ExceptionHandler({ProductNotfoundException.class})
        public ResponseEntity<?> hanlderException(ProductNotfoundException e) {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            baseResponse.setMessage(e.getMessage());
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);
        }

}
