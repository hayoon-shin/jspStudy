<%@page import="co.kh.dev.common.MyUtility"%>
<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="headerNav">
      <i class="fa-solid fa-cat" onClick="location.href='<%=request.getContextPath()%>/home/mainPage.jsp'"></i>
	<ul class="mainMenu">
		<li data-menu="one">Item One</li>
		<li data-menu="two">Item Two</li>
		<li data-menu="three">Item Three</li>
		<li data-menu="four">고객 지원</li>
	</ul>
	<%
  CustomerVO cvo2 = MyUtility.returnCvoBySession(session);
  if(cvo2==null){
  %>
	<button class="Button" class="authButton" onClick="openLoginPopup();">로그인</button>
	<%
  } else{
  %>
	<button class="Button" class="authButton" onClick="location.href='<%=request.getContextPath()%>/home/logout.jsp'">로그아웃</button>
	<%
  }
  %>
</div>
<div class="subMenuContainer">
	<ul class="subMenu" data-submenu="one">
		<li><a href="#">Submenu 1</a></li>
		<li><a href="#">Submenu 2</a></li>
		<li><a href="#">Submenu 3</a></li>
	</ul>
	<ul class="subMenu" data-submenu="two">
		<li><a href="#">Submenu A</a></li>
		<li><a href="#">Submenu B</a></li>
		<li><a href="#">Submenu C</a></li>
	</ul>
	<ul class="subMenu" data-submenu="three">
		<li><a href="#">Submenu X</a></li>
		<li><a href="#">Submenu Y</a></li>
		<li><a href="#">Submenu Z</a></li>
	</ul>
	<ul class="subMenu" data-submenu="four">
		<li><a href="<%=request.getContextPath()%>/home/boardPage.jsp">게시판</a></li>
		<li><a href="#">Submenu Beta</a></li>
		<li><a href="#">Submenu Gamma</a></li>
	</ul>
</div>
<script src="<%=request.getContextPath()%>/home/js/headerNav.js"></script>
<script src="<%=request.getContextPath()%>/home/js/common.js"></script>