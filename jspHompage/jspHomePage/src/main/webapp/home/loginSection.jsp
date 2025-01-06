<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<c:choose>
    <c:when test="${empty sessionScope.cvo}">
        <article class="loginInput">
            <form method="post" action="${pageContext.request.contextPath}/customerLoginCheck.do">
                <ul>
                    <li>
                        <input type="text" name="id" id="id" placeholder=" 아이디 또는 전화번호" value="${cookie.id.value}" />
                    </li>
                    <li>
                        <input type="password" name="pwd" id="psw" placeholder=" 비밀번호" />
                    </li>
                </ul>
                <!-- 상태유지 -->
                <div class="maint">
                    <input type="checkbox" name="maint" class="maint" <c:if test="${not empty cookie.id}">checked</c:if>>
                    <label for="maint">아이디 저장</label>
                </div>
                <!-- 로그인 버튼 -->
                <button type="submit">로그인</button>
            </form>
        </article>

        <nav class="loginNav">
            <ul style="display: flex">
                <li><a href="${pageContext.request.contextPath}/home/customerRegistPage.jsp">회원가입</a></li>
            </ul>
        </nav>
    </c:when>
    <c:otherwise>
        <!-- 로그인 성공 화면 -->
        <table class="afterLoginPage">
            <tr>
                <td width="300" align="center">${sessionScope.cvo.name} 님 로그인 되었습니다.</td>
            </tr>
        </table>
        <nav class="loginNav">
            <ul style="display: flex">
                <li><a href="${pageContext.request.contextPath}/myPageAlert.do">마이페이지</a></li>
                <li><a href="${pageContext.request.contextPath}/customerLogout.do">로그아웃</a></li>
            </ul>
        </nav>
    </c:otherwise>
</c:choose>
