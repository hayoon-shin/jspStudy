<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.dev.join.model.FileUploadDAO, com.kh.dev.join.model.FileUploadVO" %>
<%@ page import="java.util.*, java.io.*" %>

<%
    // 데이터 받기
    String title = null;
    String author = null;
    String content = null;
    String password = null;
    String fileName = null;
    long fileSize = 0;

    String uploadPath = application.getRealPath("/uploads");
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) uploadDir.mkdirs();

    try {
        // request.getParts()를 사용해 모든 폼 필드 처리
        for (Part part : request.getParts()) {
            String fieldName = part.getName();

            if (fieldName.equals("title")) {
                title = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8")).readLine();
            } else if (fieldName.equals("author")) {
                author = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8")).readLine();
            } else if (fieldName.equals("content")) {
                content = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8")).readLine();
            } else if (fieldName.equals("password")) {
                password = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8")).readLine();
            } else if (fieldName.equals("file")) {
                if (part.getSize() > 0) {
                    fileName = part.getSubmittedFileName();
                    fileSize = part.getSize();
                    String filePath = uploadPath + File.separator + fileName;
                    part.write(filePath); // 파일 저장
                }
            }
        }

        // 디버깅 출력
        out.println("Title: " + title + "<br>");
        out.println("Author: " + author + "<br>");
        out.println("Content: " + content + "<br>");
        out.println("Password: " + password + "<br>");
        out.println("FileName: " + fileName + "<br>");
        out.println("FileSize: " + fileSize + "<br>");

        // 값 확인 후 DB 저장
        if (title != null && author != null && content != null && password != null) {
            FileUploadVO vo = new FileUploadVO();
            vo.setTitle(title);
            vo.setAuthor(author);
            vo.setContent(content);
            vo.setPassword(password);

            FileUploadDAO dao = new FileUploadDAO();
            dao.insertFileUpload(vo);

            response.sendRedirect("fileuploadList.jsp");
        } else {
            out.println("모든 필드를 입력하세요.");
        }
    } catch (Exception e) {
        out.println("오류 발생: " + e.getMessage());
    }
%>
