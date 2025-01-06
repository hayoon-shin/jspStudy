package co.kh.dev.home.action.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;

public class CustomerLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(false);
			ActionForward forward= null;
			if (session != null) {
				session.invalidate();
			}
		forward = new ActionForward(request.getContextPath()+"/mainPageAlert.do?status=2", true);
		return forward;
	}

}
