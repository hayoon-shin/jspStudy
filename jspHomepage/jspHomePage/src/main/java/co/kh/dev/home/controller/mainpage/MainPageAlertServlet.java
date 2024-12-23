package co.kh.dev.home.controller.mainpage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/mainPageAlertServlet.do" })
public class MainPageAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MainPageAlertServlet() {
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
            case "6":
            	msg = "회원가입이 완료되었습니다!";
            	break;
            case "7":
            	msg = "회원가입에 실패하였습니다.";
            	break;
            case "8":
            	msg = "잘못된 접근입니다.";
            	break;
            default:
                response.sendRedirect("mainPage.jsp");
                return;
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("home/mainPage.jsp").forward(request, response);
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
