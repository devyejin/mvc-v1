package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name ="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper(); // jackson 에서 제공하는 클래스
    // readValue 메서드를 이용하면 JSON -> Java Objeect


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream();//HTTP API 방식이므로 요청할 때 데이터를 요청 메시지 body에 담아서 보냄. 이걸 꺼내기 위해 getInputStream 메서드 이용
        //바이트코드로 읽어오므로 String으로 변환해야하는데, 유용한 메서드를 스프링에서 제공
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody); //messageBody = {"username":"hello", "age":20}
        //JSON 데이터도 결국은 그냥 String임, 그래서 이걸 받아서 객체로 변환해주어야 함
        //스프링은 jackson 라이브러리를 통해 쉽게 변환할 수 있음 ( 다른 방법도 존재 )
        //readValue 메서드를 이용하면 JSON -> Java Objeect
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("helloData.getUsername() = " + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());



    }
}
