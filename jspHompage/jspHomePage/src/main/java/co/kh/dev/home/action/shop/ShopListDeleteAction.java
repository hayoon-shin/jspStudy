package co.kh.dev.home.action.shop;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.ShopDAO;
import co.kh.dev.home.model.ShopVO;

public class ShopListDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ShopDAO nDAO = ShopDAO.getInstance();
		ShopVO nvo = new ShopVO();
		request.setCharacterEncoding("UTF-8");
		nvo.setNo(Integer.parseInt(request.getParameter("no")));
		nDAO.deleteDB(nvo);
		ActionForward forward = new ActionForward(request.getContextPath()+"/shopPageAlert.do?status=3&type="+request.getParameter("type"), true);
		return forward;
	}

}
