package com.pbboard.error.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

// 에러페이지 커스텀
public class CustomErrorController implements ErrorController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private String VIEW_PATH = "/error/";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // HTTP 상태코드 값 저장
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // 값에 따라 보여줄 에러페이지 뷰 지정
        if(status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return VIEW_PATH + "404";
            }
            if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return VIEW_PATH  + "500";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
