package co.kh.dev.home.action.notice;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.NoticeDAO;
import co.kh.dev.home.model.NoticeVO;

public class NoticeListUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		NoticeDAO nDAO = NoticeDAO.getInstance();
		NoticeVO nvo = new NoticeVO(Integer.parseInt(request.getParameter("no")), request.getParameter("title"),
				request.getParameter("content"));
		nDAO.updateTCDB(nvo);
		ActionForward forward = new ActionForward("/noticePageAlert.do?status=2",
				false);
		return forward;
	}

}
