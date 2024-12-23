package co.kh.dev.home.controller.mypage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/myPageAlertServlet.do" })
public class MyPageAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyPageAlertServlet() {
        super();
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String status = request.getParameter("status");
        String msg = "";
        try {
        switch (status) {
            case "1":
                msg = "개인정보 수정이 완료되었습니다.";
                break;
            case "2":
                msg = "개인정보 수정에 실패하였습니다.";
                break;
            case "3":
                msg = "회원 탈퇴에 실패하였습니다. 비밀번호를 확인해주세요.";
                break;
            default:
                response.sendRedirect("myPage.jsp");
                return;
        }
        
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/home/myPage.jsp").forward(request, response);
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
