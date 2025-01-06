package co.kh.dev.home.action.alert;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;

public class NoticeListAlertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String status = request.getAttribute("status") == null ? "" : (String) request.getAttribute("status");
		String no = request.getParameter("no") == null ? "" : request.getParameter("no");
		String msg = "";
		boolean alertFlag = true;
		switch (status) {
		case "1":
			msg = "잘못된 접근입니다.";
			break;
		case "2":
			msg = "댓글 입력이 완료되었습니다.";
			break;
		case "3":
			msg = "댓글 삭제가 완료되었습니다.";
			break;
		case "4":
			msg = "댓글 수정이 완료되었습니다.";
			break;
		case "5":
			msg = "글쓰기가 완료되었습니다.";
			break;
		default:
			alertFlag = false;
			ActionForward forward = new ActionForward("/noticeListSelect.do?no="+no, false);
			return forward;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("alertFlag", alertFlag);
		ActionForward forward = new ActionForward("/noticeListSelect.do?no="+no, false);
		return forward;
	}

}
