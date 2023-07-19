package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {
    /**
     *
     * @param paramMap
     * @param model
     * @return viewName (논리적 이름만)
     */
    String process(Map<String,String> paramMap, Map<String, Object> model);
}
