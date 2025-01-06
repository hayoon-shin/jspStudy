package co.kh.dev.home.action.shop;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.ProductDAO;
import co.kh.dev.home.model.ProductVO;

public class ShopInsertPageSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProductDAO pDAO = ProductDAO.getInstance();
		ArrayList<ProductVO> pList = pDAO.selectAllDB();
		request.setAttribute("pList", pList);
		ActionForward forward = new ActionForward("/home/shop/shopInsertPage.jsp", false);
		return forward;
	}

}
