package co.kh.dev.home.action.product;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.ProductDAO;
import co.kh.dev.home.model.ProductVO;

public class ProductListUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		ProductDAO pDAO = ProductDAO.getInstance();
		ProductVO pvo = new ProductVO(Integer.parseInt(request.getParameter("no")),request.getParameter("name"),Integer.parseInt(request.getParameter("price")),Integer.parseInt(request.getParameter("amount")));
		pDAO.updateDB(pvo);
		ActionForward forward = new ActionForward("/productPageAlert.do?status=2",
				false);
		return forward;
	}

}
