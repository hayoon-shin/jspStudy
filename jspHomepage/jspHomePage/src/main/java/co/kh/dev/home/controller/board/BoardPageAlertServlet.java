package co.kh.dev.home.controller.board;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/boardPageAlertServlet.do" })
public class BoardPageAlertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardPageAlertServlet() {
        super();
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String status = request.getParameter("status");
        String msg = "";
        try {
        switch (status) {
            case "1":
                msg = "잘못된 접근입니다.";
                break;
            case "2":
                msg = "글 수정이 완료되었습니다.";
                break;
            case "3":
                msg = "글삭제가 완료되었습니다.";
                break;
            default:
                response.sendRedirect("boardPage.jsp");
                return;
        }
        request.setAttribute("msg", msg);
        request.getRequestDispatcher("/boardListServlet.do").forward(request, response);
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
