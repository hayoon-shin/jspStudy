<%@page import="co.kh.dev.home.model.ZipCodeDAO"%>
<%@page import="co.kh.dev.home.model.ZipCodeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- ① -->

<%
request.setCharacterEncoding("UTF-8");
String dong = request.getParameter("dong");
String check = request.getParameter("check");
ZipCodeVO zvo = new ZipCodeVO();
ZipCodeDAO zdao = new ZipCodeDAO();
zvo.setDong(dong);
ArrayList<ZipCodeVO> zipcodeList = zdao.selectByDongDB(zvo);
%>
<!DOCTYPE html>
<html>
<head>
<title>우편번호검색</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/css/zipCode.css" />
<!-- ② -->
</head>
<body bgcolor="#FFFFCC">
	<main>
		<b>우편번호 찾기</b>
		<form name="zipForm" method="post" action="zipCheckPage.jsp">
			<table>
				<tr>
					<td>동이름 입력 : <input name="dong" type="text"> <input
						type="button" value="검색" onClick="dongCheck();" />
					</td>
				</tr>
			</table>
			<input type="hidden" name="check" value="n">
		</form>
		<table>
			<%
			if (check.equals("n")) {
			%>
			<%
			if (zipcodeList.isEmpty()) {
			%>
			<tr>
				<td align="center"><br />검색된 결과가 없습니다.</td>
			</tr>
		</table>
		<%
		} else {
		%>
		<h3>※검색 후, 아래 우편번호를 클릭하면 자동으로 입력됩니다.</h3>

		<table class="zipTable">
			<tr>
				<th>우편번호</th>
				<th>시도</th>
				<th>구군</th>
				<th>동</th>
				<th>번지</th>
				<th>선택</th>
			</tr>
			<%
			for (ZipCodeVO data : zipcodeList) {
				String tempZipcode = data.getZipcode();
				String tempSido = data.getSido();
				String tempGugun = data.getGugun();
				String tempDong = data.getDong();
				String tempBunji = (data.getBunji() == null) ? "" : data.getBunji();
			%>
			<tr>
				<td class="zipcode-column"><%=tempZipcode%></td>
				<td class="sido-column"><%=tempSido%></td>
				<td class="gugun-column"><%=tempGugun%></td>
				<td class="dong-column"><%=tempDong%></td>
				<td class="bunji-column"><%=tempBunji%></td>
				<td class="action-column">
					<button class="select-button"
						onClick="sendAddress('<%=tempZipcode%>', '<%=tempSido%>', '<%=tempGugun%>', '<%=tempDong%>', '<%=tempBunji%>')">선택</button>
				</td>
			</tr>
			<%
			} // end for
			} // end for
			} // end for
			%>

			<tr>
				<td align="center"><a href="javascript:this.close();">닫기</a></td>
			</tr>
		</table>
	</main>
	<script src="${pageContext.request.contextPath}/home/js/registPage.js"></script>
</body>
</html>