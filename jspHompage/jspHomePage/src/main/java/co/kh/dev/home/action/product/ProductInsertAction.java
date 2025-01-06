package co.kh.dev.home.action.product;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.ProductDAO;
import co.kh.dev.home.model.ProductVO;

public class ProductInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    ProductDAO nDAO = ProductDAO.getInstance();
	    try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    ProductVO nvo= new ProductVO( request.getParameter("name"),Integer.parseInt(request.getParameter("price")) , Integer.parseInt(request.getParameter("amount")) );
	    nDAO.insertDB(nvo);
		ActionForward forward = new ActionForward(request.getContextPath()+"/productPageAlert.do?status=4", true);
		return forward;
	}

}
