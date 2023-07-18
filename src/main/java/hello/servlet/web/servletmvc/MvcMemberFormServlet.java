package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Controller(서블릿)로 모든 요청이 들어간 후 view(jsp)를 보여줌!!
        //처음엔 회원가입 Form 을 보여주는거니까 url요청만 받고 view로 넘겨줘야겠지?!
        // Dispatcher : 뷰로 넘겨주는 기능을 함
        String viewPath = "/WEB-INF/views/new-form.jsp"; // WEB-INF <- 이 경로안에 JSP가 있으면 외부에서 직접 JSP 호출 불가, 컨트롤러를 통해 JSP를 호출 가능
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response); // dispatcher.forward() <- 다른 서블릿이나 JSP로 이동할 수 있는 기능. 서버 내부에서 다시 호출이 발생한다.

    }
}
