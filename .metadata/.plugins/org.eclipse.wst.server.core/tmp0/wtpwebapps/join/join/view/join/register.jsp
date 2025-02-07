<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%
    // 전달된 인증번호를 받음
    String verifiedCode = (String) session.getAttribute("verifiedCode");
	// css를 강제 갱신하기 위한 timestamp
	long timestamp = System.currentTimeMillis(); 
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="register.css">
    <link rel="stylesheet" href="../../headerNav.css?<%= timestamp %>">
    
    <script src="register.js"></script>
    <script src="https://kit.fontawesome.com/70bcde4f7a.js" crossorigin="anonymous"></script>
</head>
<body onload="onLoad();" >
<%@ include file="/join/headerNav.jsp"%>
    <main>
        <div class="main_wrap">
            <div class="title_main"><div>stack overflow</div>
                <form action="registerProc.jsp" method="post" id="myform" name="regForm">
                    <label for="id">&nbsp;&nbsp;아이디</label><br>
                    <input type="text" name="id" id="id"><span></span><br>
                    <label for="pwd">&nbsp;&nbsp;비밀번호</label><br>
                    <input type="password" name="pwd" id="pwd"><span></span><br>
                    <label for="pwdconfirm">&nbsp;&nbsp;비밀번호 재확인</label><br>
                    <input type="password" name="pwdconfirm" id="pwdconfirm"><span>&nbsp;&nbsp;</span><br>
                    <label for="name">&nbsp;&nbsp;이름</label><br>
                    <input type="text" name="name" id="name"><span></span><br>
                    <label for="birthday">&nbsp;&nbsp;생년월일</label><br>
                    <input type="text" name="year" id="year" placeholder="년(4자)"><span></span>
                    <select name="month" id="month">
                        <option value="월">월</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>
                    <input type="text" name="day" id="day" placeholder="일"><span></span><br>
                    <label for="gender">&nbsp;&nbsp;성별</label><br>
                    <select name="gender" id="gender">
                        <option value="성별">성별</option>
                        <option value="남자">남자</option>
                        <option value="여자">여자</option>
                    </select><br>
                    <label for="email">&nbsp;&nbsp;본인 확인 이메일</label><br>
                    <input type="email" name="email" id="email" placeholder="필수입력"><span></span><br>
                    <label for="phone">&nbsp;&nbsp;휴대전화</label><br>
                    <select name="phone1" id="phone1">
                        <option value="대한민국">대한민국 +82</option>
                        <option value="일본">일본 +81</option>
                        <option value="베트남">베트남 +84</option>
                        <option value="중국">중국 +86</option>
                        <option value="영국">영국 +44</option>
                        <option value="오스트레일리아">오스트레일리아 +61</option>
                        <option value="필리핀">필리핀 +63</option>
                        <option value="태국">태국 +66</option>
                        <option value="미국">미국 +1</option>
                        <option value="페루">페루 +51</option>
                        <option value="멕시코">멕시코 +52</option>
                        <option value="브라질">브라질 +55</option>
                        <option value="칠레">칠레 +56</option>
                    </select><br>
                    <input type="text" name="phone2" id="phone2" placeholder="전화번호 입력"><span></span><button type="button" id="confirmnumber" onclick="confirmNumber()">인증번호 받기</button><br>
                    <input type="text" name="phone3" id="phone3" placeholder="인증번호를 입력하세요" value="<%= verifiedCode != null ? verifiedCode : "" %>"><br>
                    <button type="submit" id="join" onclick="inputCheck()">가입하기</button>
                </form>
            </div>
            
        </div>
        <nav>NAV</nav>
        <aside>ASIDE</aside>
    </main>
    <footer>FOOTER</footer>
</body>
</html>