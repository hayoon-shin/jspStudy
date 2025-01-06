<%@ page contentType="text/html; charset=UTF-8"%>
<%
CustomerVO cvo = MyUtility.returnCvoBySession(session);
if (cvo == null) {
	response.sendRedirect(request.getContextPath() + "/mainPageAlert.do?status=8");//로그인 안되있을시 메인으로가서 8번 경고문 출력
	return;
}
String id = cvo.getId();
String pwd = cvo.getPwd();
String name = cvo.getName();
String nickName = cvo.getNickName();
String email = cvo.getEmail();
String tel = cvo.getTel();
String phone = cvo.getPhone();
String birth = cvo.getBirth(); // 생년월일이 Date 형식이라고 가정
String zipcode = cvo.getZipcode();
String address1 = cvo.getAddress1();
String address2 = cvo.getAddress2();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인 화면</title>
<script src="https://kit.fontawesome.com/6ff644124c.js"
	crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/css/registPage.css" />

</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNavSection.jsp"%>
		</nav>
	</header>
	<main class="registPage">
		<h4>정 보 수 정</h4>
		<form action="/jspHomePage/customerUpdate.do" method="post"
			id="div-main" class="myForm" name="regForm">
			<div class="title-table">사이트 이용정보 수정</div>
			<table>
				<tr>
					<td class="col-key">아이디</td>
					<td><input id="input-id" type="text" name="id" value="<%=id%>"
						required readonly="readonly" /><span></span></td>
				</tr>
				<tr>
					<td class="col-key">비밀번호</td>
					<td><input id="input-pw1" type="password"
						title="비밀번호는 영문자와 숫자 6~10자리입니다" name="pwd" value="<%=pwd%>"
						required /><span></span></td>
				</tr>
				<tr>
					<td class="col-key">비밀번호 확인</td>
					<td><input id="input-pw2" type="password" name=""
						value="<%=pwd%>" required /><span></span></td>
				</tr>
			</table>

			<div class="title-table">개인정보 수정</div>
			<table>
				<tr>
					<td class="col-key">이름</td>
					<td><input id="input-name" type="text" name="name"
						value="<%=name%>" required /><span></span></td>
				</tr>
				<tr>
					<td class="col-key">닉네임</td>
					<td>공백없이 한글,영문,숫자만 입력 가능(4글자 이상)<br />닉네임을 바꾸시면 앞으로 1일 이내에는
						변경 할 수 없습니다.<br /> <input id="input-nickname" type="text"
						name="nickName" value="<%=nickName%>" required /><span></span>
					</td>
				</tr>
				<tr>
					<td class="col-key">E-mail</td>
					<td><input id="input-email" type="email" name="email"
						value="<%=email%>" required /><span></span></td>
				</tr>
				<tr>
					<td class="col-key">가입경로</td>
					<td><input type="radio" name="joinRoute" value="" />인터넷검색 <input
						type="radio" name="joinRoute" value="" />배너광고 <input type="radio"
						name="joinRoute" value="" />관련기사를 보고 <input type="radio"
						name="joinRoute" value="" />미라지 홈페이지 보고 <input type="radio"
						name="joinRoute" value="" />주변사람 소개 <input type="radio"
						name="joinRoute" value="" />기타</td>
				</tr>
				<tr>
					<td class="col-key">전화번호</td>
					<td><input id="input-tel" type="tel" name="tel"
						value="<%=tel%>" required /><span></span></td>
				</tr>
				<tr>
					<td class="col-key">휴대폰번호</td>
					<td><input id="input-mobile" type="tel" name="phone"
						value="<%=phone%>" required /><span></span></td>
				</tr>
				<tr>
					<td class="col-key">생년월일</td>
					<td><input type="date" name="birth" value="<%=birth%>" /><span></span></td>
				</tr>
				<tr>
					<td class="col-key">주소</td>
					<td id="input-address"><input type="text" id="zipcode"
						value="<%=zipcode%>" name="zipCode" size="4px" required /><span></span>
						<input id="btn-searchAddr" type="button" value="주소 검색"
						onClick="zipCheck()" /><br /> <input type="text" id="addr1"
						value="<%=address1%>" size="35px" required readonly="readonly"
						name="address1" /> 기본주소<br /> <input type="text"
						id="addr2" value="" size="35px" name="address2"
						value="<%=address2%>" /> 상세주소<br /> <input type="text" name=""
						value="" size="35px" name="address3" /> 참고항목</td>
				</tr>
			</table>

			<div id="btns-submit">
				<input id="btn-join" type="submit" value="정보수정" /> <input
					id="btn-cancel" type="button" value="취소"
					onclick="location.href='${pageContext.request.contextPath}/home/myPage/myPage.jsp'" />
			</div>
		</form>
	</main>
	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
	<script src="${pageContext.request.contextPath}/home/js/registPage.js"></script>
</body>

</html>

