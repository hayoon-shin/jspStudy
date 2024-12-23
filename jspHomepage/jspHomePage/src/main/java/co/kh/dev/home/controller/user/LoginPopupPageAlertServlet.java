package co.kh.dev.home.controller.user;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/loginPopupPageAlertServlet.do" })
public class LoginPopupPageAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginPopupPageAlertServlet() {
        super();
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String status = request.getParameter("status");
        String msg = "";
        try {
        switch (status) {
            case "1":
                msg = "회원 탈퇴가 완료되었습니다. 감사합니다.";
                break;
            case "2":
                msg = "로그아웃 되었습니다. 감사합니다.";
                break;
            case "3":
                msg = "로그인 되었습니다. 환영합니다.";
                break;
            case "4":
            	msg = "아이디 또는 비밀번호가 맞지 않습니다.";
            	break;
            case "5":
            	msg = "비밀번호가 맞지 않습니다.";
            	break;
            default:
                response.sendRedirect("loginPopup.jsp");
                return;
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("home/loginPopup.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		processRequest(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		processRequest(request, response);
	}

}
