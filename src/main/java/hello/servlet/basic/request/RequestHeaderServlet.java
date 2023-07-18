package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    //service 함수는 public이 아닌 protected 로 만들어야 한다 - why?
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);

    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("---Headers - start ---");

/*        Enumeration<String> headerNames = request.getHeaderNames(); // 모든 헤더정보를 가져오는 방법 - old
        while (headerNames.hasMoreElements()) { //더 가져올 정보가 있니?
            String headerName = headerNames.nextElement(); // 있으면 꺼내기
            System.out.println(headerName + ":  " + headerName);

        }*/

        //요즘 방식 iterator 이용
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println(headerName + " : " + headerName));

        System.out.println("---Headers - end ---");
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        //username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString());
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 ---");
        System.out.println("[Host]");
        System.out.println("request.getServerName() = " + request.getServerName());
        System.out.println("request.getServerPort() = " + request.getServerPort());
        System.out.println();

        System.out.println("[Accept-Language]");
        request.getLocales().asIterator().forEachRemaining(locale -> { //어떤 언어를 선호하는지에 대한 정보 get
            System.out.println("locale = " + locale);
        });
        System.out.println("request.getLocale() = "  + request.getLocale()); //request.getLocale() 하면 가장 값이 높은 locale 값만 꺼냄
        System.out.println();

        System.out.println("[cookie]");
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content]");
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("requesst.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println();
        System.out.println("--- Header 편의 조회 ---");
    }

    private void printEtc(HttpServletRequest request) {
        System.out.println(" --- 기타 조회 --- ");
        System.out.println("[Remote]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());

        System.out.println("[Local]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());
        System.out.println(" --- 기타 조회 --- ");
    }


}
