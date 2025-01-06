package co.kh.dev.home.action.alert;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;

public class NoticePageAlertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String status = request.getParameter("status") == null ? "" : request.getParameter("status");
		String msg = "";
		boolean alertFlag = true;
		switch (status) {
		case "1":
			msg = "잘못된 접근입니다.";
			break;
		case "2":
			msg = "공지사항 수정이 완료되었습니다.";
			break;
		case "3":
			msg = "공지사항 삭제가 완료되었습니다.";
			break;
		case "4":
			msg = "공지사항 작성이 완료되었습니다.";
			break;
		default:
			alertFlag = false;
			ActionForward forward = new ActionForward("/noticeSelect.do", false);
			return forward;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("alertFlag", alertFlag);
		ActionForward forward = new ActionForward("/noticeSelect.do", false);
		return forward;
	}

}
