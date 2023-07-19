package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
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
@WebServlet(urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    //요청을 보고 컨트롤러 매핑하는 기능
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form",new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save",new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members",new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       log.info("front Controller call");


        //요청이 들어오면 처리할 컨트롤러 매핑하기
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);//맵에서 일치하는 URI 꺼내기
        log.info("controller={}", controller);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return; //return 하면 호출한곳으로 돌아감(요청 처리가 끝나고 서블릿 컨테이너로 제어 넘어감)
        }

        Map<String, String> paramMap = createParamMap(request);

        //컨트롤러부터 ModelView 위임 받아서 처리하기
        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName(); //논리이름

        //mv로부터 받은건 논리이름이라, 이제 물리이름을 얻어내는 ViewResolver를 만들어내야함
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request,response);

    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        //요청으로부터 param 꺼내서 넘겨주기
        Map<String,String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
