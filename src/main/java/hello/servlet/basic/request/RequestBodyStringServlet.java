package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //HTTP API 방식이니까 요청 메시지 바디 에 요청데이터를 담아서 보냄 - 이걸 꺼내기 위해서 getInputStream 메서드 이용
        ServletInputStream inputStream = request.getInputStream(); // 바이트 코드로 꺼냄 -> String으로 변환해주어야 함
        //변환에는 여러 방법이 있지만 우선 스프링이 제공하는 메서드 이용
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messagebody = " + messagebody);

        response.getWriter().write("ok");


    }
}
