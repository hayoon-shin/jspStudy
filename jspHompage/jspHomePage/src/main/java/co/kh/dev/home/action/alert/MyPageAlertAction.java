package co.kh.dev.home.action.alert;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;

public class MyPageAlertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String status = request.getParameter("status") == null ? "" : request.getParameter("status");
		String msg = "";
		boolean alertFlag = true;
		switch (status) {
		case "1":
			msg = "개인정보 수정이 완료되었습니다.";
			break;
		case "2":
			msg = "개인정보 수정에 실패하였습니다.";
			break;
		case "3":
			msg = "회원 탈퇴에 실패하였습니다. 비밀번호를 확인해주세요.";
			break;
		default:
			alertFlag = false;
			ActionForward forward = new ActionForward("/home/myPage/myPage.jsp", false);
			return forward;
		}

		request.setAttribute("alertFlag", alertFlag);
		request.setAttribute("msg", msg);
		ActionForward forward = new ActionForward("/home/myPage/myPage.jsp", false);
		return forward;
	}

}
