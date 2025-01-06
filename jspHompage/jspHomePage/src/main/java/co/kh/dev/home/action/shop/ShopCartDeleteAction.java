package co.kh.dev.home.action.shop;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CartDAO;
import co.kh.dev.home.model.CartVO;

public class ShopCartDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		CartDAO cDAO = CartDAO.getInstance();
		String no = request.getParameter("no");
		CartVO cvo = new CartVO();
		cvo.setNo(Integer.parseInt(no));
		boolean dFlag = cDAO.deleteDB(cvo);
		if (dFlag) {
			response.getWriter().write("SUCCESS");
		} else {
			response.getWriter().write("FAIL");
		}
		return null;
	}
}
