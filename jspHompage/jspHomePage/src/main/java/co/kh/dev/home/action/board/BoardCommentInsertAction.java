package co.kh.dev.home.action.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CommentDAO;
import co.kh.dev.home.model.CommentVO;

public class BoardCommentInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
				request.setCharacterEncoding("UTF-8");
				CommentDAO cmDAO = CommentDAO.getInstance();
				HttpSession session=request.getSession();
				String customerId = (String) session.getAttribute("id");
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				int parentNo = ((request.getParameter("parentNo")==null))?0:Integer.parseInt(request.getParameter("parentNo"));
				int depth = ((request.getParameter("depth")==null))?0:Integer.parseInt(request.getParameter("depth"))+1;
				String content = request.getParameter("content");
				CommentVO cvo = new CommentVO(customerId, boardNo, parentNo, depth, content);
				cmDAO.insertDB(cvo);
				request.setAttribute("status", "2");
		 		ActionForward forward = new ActionForward("/boardListAlert.do?no="+Integer.parseInt(request.getParameter("boardNo")), false);
		 		return forward;
	}

}
