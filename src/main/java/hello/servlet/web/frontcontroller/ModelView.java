package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView { //스프링에서 쓰는이름은 ModelAndView
    private String viewName; //view 이름 저장
    private Map<String,Object> model = new HashMap<>(); //데이터 저장+전달(request역할)

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
