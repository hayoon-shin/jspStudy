package co.kh.dev.home.action.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CommentDAO;
import co.kh.dev.home.model.CommentVO;

public class BoardCommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommentDAO cDAO = CommentDAO.getInstance();
		CommentVO cvo = new CommentVO();
		request.setCharacterEncoding("UTF-8");
		cvo.setNo(Integer.parseInt(request.getParameter("no")));
		cDAO.deleteDB(cvo);
		request.setAttribute("status", "3");
 		ActionForward forward = new ActionForward("/boardListAlert.do?no="+Integer.parseInt(request.getParameter("boardNo")), false);
		return forward;
	}

}
