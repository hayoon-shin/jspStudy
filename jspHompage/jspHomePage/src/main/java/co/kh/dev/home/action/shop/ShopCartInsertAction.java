package co.kh.dev.home.action.shop;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CartDAO;
import co.kh.dev.home.model.CartVO;

public class ShopCartInsertAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        CartDAO ctDAO = CartDAO.getInstance();
        CartVO ctvo = new CartVO(); 
        String id = (String) session.getAttribute("id");
        if (id == null) {
            ActionForward forward = new ActionForward(request.getContextPath() + "/loginPage.do", true);
            return forward;
        }

        String shopNo = request.getParameter("no");
        if (shopNo == null || shopNo.isEmpty()) {
            ActionForward forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=8", true);
            return forward;
        }
        String qt = request.getParameter("qt");
        if (qt == null || qt.isEmpty()) {
        	ActionForward forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=8", true);
        	return forward;
        }
        ctvo.setCustomerId(id);
        ctvo.setShopNo(Integer.parseInt(shopNo));
        ctvo.setQt(Integer.parseInt(qt));
        // 장바구니 추가 성공 후 리다이렉트
        boolean flag = ctDAO.insertCart(ctvo);
        String status= (flag)?"4":"5";
        ActionForward forward = new ActionForward(request.getContextPath() + "/shopPageAlert.do?type=" + request.getParameter("type") + "&status="+status, true);
        return forward;
    }
}
