<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.dev.join.model.CartDAO, com.kh.dev.join.model.CartVO" %>
<!DOCTYPE html>
<html>
<head>
    <title>장바구니</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>장바구니</h1>

    <%
        HttpSession session2 = request.getSession();

        // 세션에서 사용자 이름 가져오기
        String username = (String) session.getAttribute("username");
        if (username == null) {
    %>
        <p>로그인이 필요합니다. <a href="/join/join/view/login/login.jsp">로그인</a></p>
    <%
            return;
        }

        // CartDAO를 사용해 장바구니 데이터 가져오기
        CartDAO cartDAO = new CartDAO();
        List<CartVO> cartItems = null;

        try {
            int userId = cartDAO.getUserIdByUsername(username); // 사용자 ID 조회
            cartItems = cartDAO.getCartItems(userId); // 장바구니 항목 가져오기
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>장바구니 데이터를 가져오는 중 오류가 발생했습니다.</p>");
            return;
        }

        if (cartItems == null || cartItems.isEmpty()) {
    %>
        <p>장바구니가 비어 있습니다.</p>
        <a href="productList.jsp"><button>상품 목록으로</button></a>
    <%
            return;
        }
    %>

    <table border="1">
        <tr>
            <th>상품명</th>
            <th>수량</th>
            <th>가격</th>
            <th>총 가격</th>
            <th>액션</th>
        </tr>
        <%
            double totalPrice = 0.0;
            for (CartVO cart : cartItems) {
                double itemTotalPrice = cart.getPrice() * cart.getQuantity();
                totalPrice += itemTotalPrice;
        %>
        <tr>
            <td><%= cart.getProductName() %></td>
            <td><%= cart.getQuantity() %></td>
            <td><%= cart.getPrice() %></td>
            <td><%= itemTotalPrice %></td>
            <td>
                <!-- 수량 수정 -->
                <form action="CartServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="productId" value="<%= cart.getProductId() %>">
                    <input type="number" name="quantity" value="<%= cart.getQuantity() %>" min="1" required>
                    <button type="submit">수정</button>
                </form>
                <!-- 삭제 -->
                <form action="CartServlet" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="productId" value="<%= cart.getProductId() %>">
                    <button type="submit">삭제</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <p><strong>총 금액: <%= totalPrice %></strong></p>

    <div>
        <a href="productList.jsp"><button>상품 목록으로</button></a>
    </div>
</body>
</html>
