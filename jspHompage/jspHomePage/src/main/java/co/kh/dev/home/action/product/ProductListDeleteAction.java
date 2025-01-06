package co.kh.dev.home.action.product;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.ProductDAO;
import co.kh.dev.home.model.ProductVO;

public class ProductListDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ProductDAO nDAO = ProductDAO.getInstance();
		ProductVO nvo = new ProductVO();
		request.setCharacterEncoding("UTF-8");
		nvo.setNo(Integer.parseInt(request.getParameter("no")));
		nDAO.deleteDB(nvo);
		ActionForward forward = new ActionForward(request.getContextPath()+"/productPageAlert.do?status=3", true);
		return forward;
	}

}
