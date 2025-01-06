package co.kh.dev.home.action.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CustomerDAO;
import co.kh.dev.home.model.CustomerVO;

public class CustomerInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		  request.setCharacterEncoding("UTF-8");
		  	System.out.println("qwe");
	        // 세션 확인 및 중복 체크된 ID 확인
	        HttpSession session = request.getSession(false);
	        // 사용자 입력 값 설정
	        CustomerVO cvo = new CustomerVO();
	        cvo.setId(request.getParameter("id"));
	        cvo.setPwd(request.getParameter("pwd"));
	        cvo.setName(request.getParameter("name"));
	        cvo.setNickName(request.getParameter("nickName"));
	        cvo.setEmail(request.getParameter("email"));
	        cvo.setTel(request.getParameter("tel"));
	        cvo.setPhone(request.getParameter("phone"));
	        cvo.setBirth(request.getParameter("birth"));
	        cvo.setZipCode(request.getParameter("zipCode"));
	        cvo.setAddress1(request.getParameter("address1"));
	        cvo.setAddress2(request.getParameter("address2"));
	        System.out.println(request.getParameter("pwd"));
	        // 중복 체크 확인
	        // DAO 인스턴스 가져오기
	        CustomerDAO cdao = CustomerDAO.getInstance();
	        ActionForward forward;
	        // 데이터베이스 삽입
	        boolean flag = cdao.insertDB(cvo);
	        // 회원가입 성공 여부에 따라 리다이렉트 또는 포워딩
	        if (flag) {
	            forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=6", true);
	        } else {
	            forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=7", true);
	        }
	        // 세션 속성 제거
	        if (session.getAttribute("checkedId") != null) {
	            session.removeAttribute("checkedId");
	        }
	        return forward;
	    }
	}

