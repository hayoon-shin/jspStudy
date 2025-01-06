package co.kh.dev.home.action.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.common.MyUtility;
import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CustomerDAO;
import co.kh.dev.home.model.CustomerVO;

public class CustomerDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		ActionForward forward = null;
		CustomerVO cvo = MyUtility.returnCvoBySession(session);
		CustomerDAO cDAO = CustomerDAO.getInstance();
		if (cvo == null) {
			response.sendRedirect(request.getContextPath() + "/home/mainPage.jsp");
		}
		if (cvo.getPwd().equals(request.getParameter("pwd"))) {
			cDAO.deleteDB(cvo);
			session.invalidate();
			forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=1", true);
		} else {
			forward = new ActionForward(request.getContextPath() + "/myPageAlert.do?status=3", true);
		}
		return forward;
	}

}
