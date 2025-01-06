package co.kh.dev.home.action.alert;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;

public class MainPageAlertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String status = request.getParameter("status") == null ? "" : request.getParameter("status");
		String msg = "";
		boolean alertFlag = true;
		ActionForward forward=null;
		switch (status) {
		case "1":
			msg = "회원 탈퇴가 완료되었습니다. 감사합니다.";
			break;
		case "2":
			msg = "로그아웃 되었습니다. 감사합니다.";
			break;
		case "3":
			msg = "로그인 되었습니다. 환영합니다.";
			break;
		case "4":
			msg = "아이디 또는 비밀번호가 맞지 않습니다.";
			break;
		case "5":
			msg = "비밀번호가 맞지 않습니다.";
			break;
		case "6":
			msg = "회원가입이 완료되었습니다!";
			break;
		case "7":
			msg = "회원가입에 실패하였습니다.";
			break;
		case "8":
			msg = "잘못된 접근입니다.";
			break;
		default:
			alertFlag = false;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("alertFlag", alertFlag);
		forward  = new ActionForward("/mainPageSelect.do", false);
		return forward;
	}

}
