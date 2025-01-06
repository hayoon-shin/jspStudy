package co.kh.dev.home.action.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CustomerDAO;
import co.kh.dev.home.model.CustomerVO;

public class CustomerIdDupCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		CustomerVO cvo = new CustomerVO();
		String userId = request.getParameter("userId");
		CustomerDAO cDAO = CustomerDAO.getInstance();
		cvo.setId(userId);
        // 데이터베이스 중복 검사 호출
        boolean dFlag = cDAO.selectCountByIdDB(cvo);  // DB 중복 확인
        if (dFlag) {
            session.setAttribute("checkedId", userId);
            response.getWriter().write("SUCCESS");
        } else {
        	if(session.getAttribute("checkedId")!=null) {
        		session.removeAttribute("checkedId");
        	}
            response.getWriter().write("FAIL");
        }
		return null;
	}

}
