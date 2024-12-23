package co.kh.dev.home.controller.board;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.model.BoardDAO;
import co.kh.dev.home.model.BoardVO;

@WebServlet({ "/boardContentDeleteServlet.do" })
public class BoardContentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardContentDeleteServlet() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO bDAO = BoardDAO.getInstance();
		BoardVO bvo = new BoardVO();
		try {
			request.setCharacterEncoding("UTF-8");
			bvo.setNo(Integer.parseInt(request.getParameter("no")));
			bDAO.deleteDB(bvo);
			response.sendRedirect("/jspHomePage/boardPageAlertServlet.do?status=3");
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
