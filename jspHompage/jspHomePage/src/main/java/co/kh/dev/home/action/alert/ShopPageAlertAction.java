package co.kh.dev.home.action.alert;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;

public class ShopPageAlertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String status = request.getParameter("status") == null ? "" : (String) request.getParameter("status");
		String type = request.getParameter("type") == null ? "" : request.getParameter("type");
		String msg = "";
		boolean alertFlag = true;
		switch (status) {
		case "1":
			msg = "잘못된 접근입니다.";
			break;
		case "2":
			msg = "상품 글 등록이 완료되었습니다..";
			break;
		case "3":
			msg = "상품 삭제가 완료되었습니다.";
			break;
		case "4":
			msg = "장바구니에 등록되었습니다.";
			break;
		case "5":
			msg = "이미 추가된 상품이거나, 장바구니에 추가할수 없는 상품입니다.";
			break;
		default:
			alertFlag = false;
			ActionForward forward = new ActionForward("/shopSelect.do?type="+type, false);
			return forward;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("alertFlag", alertFlag);
		ActionForward forward = new ActionForward("/shopSelect.do?type="+type, false);
		return forward;
	}

}
