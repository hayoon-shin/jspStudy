package co.kh.dev.home.action.shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.CartDAO;
import co.kh.dev.home.model.CartVO;

public class ShopCartSelectAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        CartDAO ctDAO = CartDAO.getInstance();
        if (session == null || session.getAttribute("id") == null) {
            // 로그인 정보가 없으면 메인 페이지로 리다이렉트
            ActionForward forward = new ActionForward(request.getContextPath() + "/mainPageAlert.do?status=8", true);
            return forward;
        }

        String userId = (String) session.getAttribute("id");
        CartVO ctvo = new CartVO();
        ctvo.setCustomerId(userId);
        ArrayList<CartVO> ctList = ctDAO.selectCartByCustomerId(ctvo);
        // 가져온 상품 리스트를 request에 설정
        request.setAttribute("ctList",ctList);
        // JSP로 포워딩
        ActionForward forward = new ActionForward("/home/shop/shopCartPage.jsp", false);
        return forward;
    }
}
