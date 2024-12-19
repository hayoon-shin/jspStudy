<%@page import="java.util.HashMap"%>
<%@page import="com.kh.dev.actiontag.model.Customer"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	//2. curd 데이터베이스에서 조회를 한 다음에 request 입력한다.
	Customer customer = new Customer();
	customer.setName("shy");
	customer.setEmail("ssh3324@gmail.com");
	customer.setPhone("010-7288-1439");
	request.setAttribute("customer",customer);
	
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("name", "shy");
	map.put("email", "ssh3324@gmail.com");
	map.put("phone", "010-7288-1439");
	request.setAttribute("map", map);	
	
	//3. curd request받아서 진행한다.
	Customer ct = (Customer)request.getAttribute("customer");
	HashMap<String, String> map2 = request.getAttribute("map");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Example</title>
</head>
<body>
	<ul>
		<li><%= ct.getName() %></li>
		<li><%= ct.getEmail() %></li>
		<li><%= ct.getPhone() %></li>
	</ul>
	<ul>
		<li>${customer.name} </li>
		<li>${customer.email} </li>
		<li>${customer.phone} </li>
	</ul>
	<ul>
		<li><%= map2.get("name") %> </li>
		<li><%= map2.get("email") %>  </li>
		<li><%= map2.get("phone") %> </li>
	</ul>
	<ul>
		<li>${map.name} </li>
		<li>${map.email} </li>
		<li>${map.phone} </li>
	</ul>
	<ul>
		<li>${map["name"]} </li>
		<li>${map["email"]} </li>
		<li>${map["phone"]} </li>
	</ul>
</body>
</html>