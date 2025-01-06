package co.kh.dev.home.action.customer;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CustomerDAO;
import co.kh.dev.home.model.CustomerVO;

public class CustomerLoginCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CustomerDAO cDAO = CustomerDAO.getInstance();
		HttpSession session = request.getSession(false);
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String maint = request.getParameter("maint");
		CustomerVO cvo = new CustomerVO();
		cvo.setId(id);
		cvo.setPwd(pwd);
		if (cDAO.selectByIdDB(cvo) == null) {
			ActionForward forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=4", true);
			return forward;
		} else {
			if (cDAO.selectLoginCheckDB(cvo) == null) {
				ActionForward forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=5", true);
				return forward;
			} else {
				if (maint != null) {
					Cookie ck = new Cookie("id", id);
					response.addCookie(ck);
				}else {
					Cookie[] cookies = request.getCookies();
				    if (cookies != null) {
				        for (Cookie cookie : cookies) {
				            if ("id".equals(cookie.getName())) {
				                Cookie deleteCookie = new Cookie("id", null); // 값은 null로 설정
				                deleteCookie.setMaxAge(0); // 유효기간을 0으로 설정
				                response.addCookie(deleteCookie); // 삭제된 쿠키를 응답에 추가
				                break;
				            }
				        }
				    }
				}
				CustomerVO cvo2=cDAO.selectLoginCheckDB(cvo);
				session.setAttribute("cvo", cvo2);
				session.setAttribute("id", id);
				session.setAttribute("pwd", pwd);
			}
		}
		ActionForward forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=3", true);
		return forward;
	}

}
