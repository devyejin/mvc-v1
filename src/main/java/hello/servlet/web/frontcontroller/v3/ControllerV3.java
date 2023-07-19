package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * 이제, 데이터 전송, 뷰 업무를 프론트에게 넘길거야!
 */
public interface ControllerV3 {

    //이제 POJO, 서블릿으로부터 독립
    ModelView process(Map<String, String> paramMap);
}
