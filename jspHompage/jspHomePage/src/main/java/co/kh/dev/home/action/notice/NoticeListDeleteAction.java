package co.kh.dev.home.action.notice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.NoticeDAO;
import co.kh.dev.home.model.NoticeVO;

public class NoticeListDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		NoticeDAO nDAO = NoticeDAO.getInstance();
		NoticeVO nvo = new NoticeVO();
		request.setCharacterEncoding("UTF-8");
		nvo.setNo(Integer.parseInt(request.getParameter("no")));
		nDAO.deleteDB(nvo);
		ActionForward forward = new ActionForward(request.getContextPath()+"/noticePageAlert.do?status=3", true);
		return forward;
	}

}
