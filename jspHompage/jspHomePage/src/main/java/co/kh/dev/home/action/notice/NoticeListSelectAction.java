package co.kh.dev.home.action.notice;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.NoticeDAO;
import co.kh.dev.home.model.NoticeVO;

public class NoticeListSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	NoticeDAO nDAO = NoticeDAO.getInstance();
    	NoticeVO nvo = new NoticeVO();
		request.setCharacterEncoding("UTF-8");
    	nvo.setNo(Integer.parseInt(request.getParameter("no")));
    	nvo = nDAO.selectByNoDB(nvo);
    	nvo.setCount(nvo.getCount() + ((request.getParameter("count")==null)?0:1));
    	nDAO.updateDB(nvo);
    	request.setAttribute("nvo",nvo);
		ActionForward forward = new ActionForward("/home/notice/noticeListPage.jsp", false);
		return forward;
	}

}
