package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@WebServlet(urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private final Map<String, ControllerV2> controllerMap = new HashMap<>();

    //요청을 보고 컨트롤러 매핑하는 기능
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form",new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save",new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members",new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       log.info("front Controller call");

       //요청이 들어오면 처리할 컨트롤러 매핑하기
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);//맵에서 일치하는 URI 꺼내기
        log.info("controller={}", controller);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //return 하면 호출한곳으로 돌아감(요청 처리가 끝나고 서블릿 컨테이너로 제어 넘어감)
        }

        //컨트롤러부터 view받아서 대신 view처리
        MyView myView = controller.process(request, response);
        myView.render(request,response);
    }
}
