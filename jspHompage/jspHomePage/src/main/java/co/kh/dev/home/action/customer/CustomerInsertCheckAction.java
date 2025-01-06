package co.kh.dev.home.action.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;

public class CustomerInsertCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  request.setCharacterEncoding("UTF-8");

	        // 세션 확인 및 중복 체크된 ID 확인
	        HttpSession session = request.getSession(false);
	        String checkedId = (String) session.getAttribute("checkedId");
	        String userId = request.getParameter("userId");
	        // 중복 체크 확인
	        if (checkedId == null || !checkedId.equals(userId)) {
	            response.getWriter().write("FALSE");
	        }else {
	            response.getWriter().write("SUCCESS"); 
	        }
	        return null;
	    }
	}

