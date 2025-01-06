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

public class CustomerUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
			request.setCharacterEncoding("UTF-8");
			ActionForward forward= null;
			HttpSession session = request.getSession(false);
			CustomerVO cvo = MyUtility.returnCvoBySession(session);
			CustomerDAO cDAO = CustomerDAO.getInstance();
			String status = null;
			CustomerVO cvo2 = new CustomerVO();
			cvo2.setId(request.getParameter("id"));
			cvo2.setPwd(request.getParameter("pwd"));
			cvo2.setName(request.getParameter("name"));
			cvo2.setNickName(request.getParameter("nickName"));
			cvo2.setEmail(request.getParameter("email"));
			cvo2.setTel(request.getParameter("tel"));
			cvo2.setPhone(request.getParameter("phone"));
			cvo2.setBirth(request.getParameter("birth"));
			cvo2.setZipCode(request.getParameter("zipCode"));
			cvo2.setAddress1(request.getParameter("address1"));
			cvo2.setAddress2(request.getParameter("address2"));
			if (cvo == null) {
				forward = new ActionForward(request.getContextPath() + "/mainPageAlert.jsp?status=8", true);
				return forward;
			}
			boolean flag = cDAO.updateDB(cvo2);
			if (flag) {
				status = "1";
			} else {
				status = "2";
			}
		forward = new ActionForward(request.getContextPath() + "/myPageAlert.do?status=" + status, true);
		return forward;
	}

}
