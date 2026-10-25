package com.itheima;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReponseController {
    @RequestMapping("/reponse")
    public void Reponse(HttpServletResponse response) throws IOException {
        //响应状态码
        response.setStatus(401);
        //响应头
        response.setHeader("Content-Type", "text/plain");
        //响应体
        response.getWriter().write("ok hello");
    }
    @RequestMapping("/response2")
    public ResponseEntity<String> response2(HttpServletResponse response)throws IOException {
        return ResponseEntity.status(401)
                .header("name","javaweb-ai")
                .body("<h1>hello response</h1>");
    }
}
