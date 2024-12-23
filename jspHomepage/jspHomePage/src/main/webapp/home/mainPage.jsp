<%@ page contentType="text/html; charset=UTF-8"%>
<%
String msg=(String)request.getAttribute("msg");
boolean alertFlag=(msg==null||msg.equals(""))?false:true;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 화면</title>
	<script src="https://kit.fontawesome.com/6ff644124c.js"	crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/home/css/MainPage.css" />
<%
if(alertFlag){
%>	
<script>
alert("<%=msg%>"); 
window.location.replace("<%=request.getContextPath()%>/home/mainPage.jsp");
</script>
<%
}
%>

</head>
<body>
<header>
 <nav class="headerNav">
<%@ include file="/home/headerNav.jsp"%>
</nav>
 <section class="carouselSection">
<%@ include file="/home/carouselSection.jsp"%>
</section>
</header>
    <main class="mainPage">
        <section class="sideSection">Side Menu</section>
        <section class="mainSection">
            <!-- 게시판 요약 정보 -->
            <article class="boardArticle">
                <h3>게시판 요약</h3>
                <table>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>게시판 테스트 제목</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>다른 게시판 제목</td>
                        </tr>
                    </tbody>
                </table>
            </article>
            <!-- 상품 요약 정보 -->
            <article class="productArticle">
                <h3>상품 요약</h3>
                <table>
                    <thead>
                        <tr>
                            <th>상품명</th>
                            <th>가격</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>상품 1</td>
                            <td>10,000원</td>
                        </tr>
                        <tr>
                            <td>상품 2</td>
                            <td>20,000원</td>
                        </tr>
                    </tbody>
                </table>
            </article>
        </section>
        <section class="loginSection">
        <%@ include file="/home/loginSection.jsp" %>
        </section>
    </main>
        <hr>
    <footer>
     <%@ include file="/home/footer.jsp" %>
    </footer>
</body>

</html>

