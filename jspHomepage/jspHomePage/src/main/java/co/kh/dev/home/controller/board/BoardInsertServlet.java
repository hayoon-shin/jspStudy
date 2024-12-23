package co.kh.dev.home.controller.board;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import co.kh.dev.home.model.BoardDAO;
import co.kh.dev.home.model.BoardVO;


@WebServlet({ "/boardInsertServlet.do" })
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardInsertServlet() {
        super();
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
    BoardDAO bDAO = new BoardDAO();
    try {
		request.setCharacterEncoding("UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
    HttpSession session=request.getSession();
    String customerId = (String) session.getAttribute("id");
    BoardVO bvo= new BoardVO(customerId, request.getParameter("title"), request.getParameter("content"));
    bDAO.insertDB(bvo);
    try {
		response.sendRedirect(request.getContextPath()+"/home/boardPage.jsp");
	} catch (IOException e) {
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
