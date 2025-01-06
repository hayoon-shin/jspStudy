package co.kh.dev.home.action.notice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.NoticeDAO;
import co.kh.dev.home.model.NoticeVO;

public class NoticeInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    NoticeDAO nDAO = NoticeDAO.getInstance();
	    try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    NoticeVO nvo= new NoticeVO( request.getParameter("title"), request.getParameter("content"));
	    nDAO.insertDB(nvo);
		ActionForward forward = new ActionForward(request.getContextPath()+"/noticePageAlert.do?status=4", true);
		return forward;
	}

}
