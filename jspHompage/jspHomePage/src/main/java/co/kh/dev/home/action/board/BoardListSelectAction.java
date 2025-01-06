package co.kh.dev.home.action.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.BoardDAO;
import co.kh.dev.home.model.BoardVO;
import co.kh.dev.home.model.CommentDAO;
import co.kh.dev.home.model.CommentVO;

public class BoardListSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	BoardDAO bDAO = BoardDAO.getInstance();
    	CommentDAO cmDAO = CommentDAO.getInstance();
    	ArrayList<CommentVO> cmList = new ArrayList<CommentVO>();
    	BoardVO bvo = new BoardVO();
		request.setCharacterEncoding("UTF-8");
    	bvo.setNo(Integer.parseInt(request.getParameter("no")));
    	bvo = bDAO.selectByNoDB(bvo);
    	bvo.setCount(bvo.getCount() + ((request.getParameter("count")==null)?0:1));
    	bDAO.updateDB(bvo);
    	cmList = cmDAO.selectByBoardNoDB(bvo);
    	request.setAttribute("bvo",bvo);
		request.setAttribute("cmList", cmList);
		ActionForward forward = new ActionForward("/home/board/boardListPage.jsp", false);
		return forward;
	}

}
