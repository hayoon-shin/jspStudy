package com.kh.dev.join.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kh.dev.join.model.CartDAO;

@WebServlet("/CartServlet.do")
public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // 세션에서 사용자 이름 가져오기
        String username = (String) session.getAttribute("username");
        if (username == null) {
            response.getWriter().println("로그인이 필요합니다.");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 상태 코드
            return;
        }

        CartDAO cartDAO = new CartDAO();

        // 데이터베이스에서 사용자 ID 가져오기
        int userId;
        try {
            userId = cartDAO.getUserIdByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("사용자 정보를 가져오는 중 오류가 발생했습니다.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 상태 코드
            return;
        }

        // 파라미터에서 액션, 상품 ID 및 수량 가져오기
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        try {
            if ("add".equals(action)) {
                cartDAO.addToCart(userId, productId, quantity);
            } else if ("remove".equals(action)) {
                cartDAO.removeFromCart(userId, productId);
            } else if ("update".equals(action)) {
                cartDAO.updateCartQuantity(userId, productId, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("장바구니 처리 중 오류 발생: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 상태 코드
            return;
        }

        // 처리 완료 후 장바구니 페이지로 리다이렉트
        response.sendRedirect("cart.jsp");
    }
}
