package co.kh.dev.common;

import javax.servlet.http.HttpSession;

import co.kh.dev.home.model.CustomerDAO;
import co.kh.dev.home.model.CustomerVO;

public class MyUtility {
	// 비어있거나 NULL인 문자열 확인
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	// 세션을 넣으면 정보가 들어있는 cvo 세션에 id값이 없으면 null인 cvo를 반환해주는 메소드
	public static CustomerVO returnCvoBySession(HttpSession session) {
		CustomerDAO cDAO = CustomerDAO.getInstance();
		CustomerVO cvo = new CustomerVO();
		String id = (String) session.getAttribute("id");
		if (isNullOrEmpty(id)) {
			cvo = null;
		} else {
			cvo.setId(id);
			cvo = cDAO.selectByIdDB(cvo);
		}
		return cvo;
	}
	//session과 스트링을 넣어서 다르면 dispNone반환
	public static String sessionIdCheck(String id, HttpSession session) {
		if (session.getAttribute("id") != null && id.equals(session.getAttribute("id"))) {
			return "";
		} else {
			return "dispNone";
		}
	}
}
