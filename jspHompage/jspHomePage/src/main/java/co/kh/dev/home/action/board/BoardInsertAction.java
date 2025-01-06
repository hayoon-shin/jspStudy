package co.kh.dev.home.action.board;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.BoardDAO;
import co.kh.dev.home.model.BoardVO;

public class BoardInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		ActionForward forward = new ActionForward(request.getContextPath()+"/boardPageAlert.do", true);
		return forward;
	}

}
