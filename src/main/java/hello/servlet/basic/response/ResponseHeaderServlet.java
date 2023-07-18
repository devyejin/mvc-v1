package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setStatus(200); //응답코드 - 직접 숫자로 적기보다 상수(final)로 정의된 걸 사용하는게 더 좋음
          response.setStatus(HttpServletResponse.SC_OK); //200
//           response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //400

        //[response-headers]
//        response.setHeader("content-Tyoe","text/plain;charset=utf-8");
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //캐시 무효화, HTTP 강의 마지막강의
        response.setHeader("Pragma","no-cache");//캐시 무효화, HTTP 강의 마지막강의
        response.setHeader("my-header","hello"); // 캐시 만들어서 넣는것도 가능

        //[Header 편의 메서드]
//        content(response);

        //쿠키 관련
        cookie(response);

        //리다이렉트 관련
        redirect(response);


        PrintWriter writer = response.getWriter();
        writer.println("ok");



    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html  -- 여기로 보내버려!!

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");

        response.sendRedirect("/basic/hello-form.html");
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); - header메세지로 넣어도 되지만 편리하게 쿠키 객체랑 메서드 제공함

        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2

        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성) - 보통 생략함
    }
}
