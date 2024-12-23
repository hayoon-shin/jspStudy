package co.kh.dev.home.controller.board;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.model.BoardDAO;
import co.kh.dev.home.model.BoardVO;

@WebServlet({ "/boardModifyServlet.do" })
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardModifyServlet() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			request.setCharacterEncoding("UTF-8");
			BoardVO bvo = new BoardVO(Integer.parseInt(request.getParameter("no")),request.getParameter("title"),request.getParameter("content"));
			bDAO.updateDB(bvo);
			response.sendRedirect("/jspHomePage/boardPageAlertServlet.do?status=2");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

}
