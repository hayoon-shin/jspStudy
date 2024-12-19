<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="java.util.*"%>
<%
//1. 서버에서 업로드 진행해야할 순서(업로드경로를 적는다)
String uploadPath = request.getRealPath("upload"); // 업로드 경로
//2. 업로드할 최대사이즈를 설정한다
int size = 10 * 1024 * 1024; // 10M
String name = "";
String subject = "";
String filename1 = "";
String filename2 = "";
String origfilename1 = "";
String origfilename2 = "";
try {
	//3. MultipartRequest 객체생성을 시키고 매개변수(request, 업로드위치, 최대사이즈, encoding, 이름 재정의 정책)
	MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
	name = multi.getParameter("name");
	subject = multi.getParameter("subject");
	//4. Enumeration files = multiRequest.getFileNames();
	Enumeration files = multi.getFileNames();
	String file1 = (String) files.nextElement(); // String file1 = fileName2
	//5. 업로드된 서버에서 저장된 이름 가져온다. (테이블 저장되어야됨)
	filename1 = multi.getFilesystemName(file1);
	//6. 업로드된 사용자가 사용된 진짜 이름을 가져온다.(테이블 저장되어야됨)
	origfilename1 = multi.getOriginalFileName(file1);
	String file2 = (String) files.nextElement(); 
	filename2 = multi.getFilesystemName(file2);	 // String file2 = fileName1
	origfilename2 = multi.getOriginalFileName(file2);
} catch (Exception e) {
	e.printStackTrace();
}
%>
<html>
<body>
	<form name="filecheck" action="fileCheck.jsp" method="post">
		<input type="hidden" name="name" value="<%=name%>"> <input
			type="hidden" name="subject" value="<%=subject%>"> <input
			type="hidden" name="filename1" value="<%=filename1%>"> <input
			type="hidden" name="filename2" value="<%=filename2%>"> <input
			type="hidden" name="origfilename1" value="<%=origfilename1%>">
		<input type="hidden" name="origfilename2" value="<%=origfilename2%>">
		<input type="submit" values="전송">
	</form>
	<a href="#" onclick="javascript:filecheck.submit()"> 업로드 확인 및 다운로드
		페이지 이동</a>
</body>
</html>
