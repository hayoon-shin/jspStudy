package co.kh.dev.home.action.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.BoardDAO;
import co.kh.dev.home.model.BoardVO;

public class BoardListDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BoardDAO bDAO = BoardDAO.getInstance();
		BoardVO bvo = new BoardVO();
		request.setCharacterEncoding("UTF-8");
		bvo.setNo(Integer.parseInt(request.getParameter("no")));
		bDAO.deleteDB(bvo);
		ActionForward forward = new ActionForward(request.getContextPath()+"/boardPageAlert.do?status=3", true);
		return forward;
	}

}
