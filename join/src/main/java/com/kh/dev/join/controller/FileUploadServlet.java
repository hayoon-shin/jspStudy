package com.kh.dev.join.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kh.dev.join.model.FileUploadDAO;
import com.kh.dev.join.model.FileUploadVO;

@WebServlet("/fileuploadWriteProc.do") // URL 매핑
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class FileUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 한글 처리
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String title = null;
        String author = null;
        String content = null;
        String password = null;
        String fileName = null;

        try {
            for (Part part : request.getParts()) {
                String fieldName = part.getName();

                if (fieldName.equals("title")) {
                    title = request.getParameter("title");
                } else if (fieldName.equals("author")) {
                    author = request.getParameter("author");
                } else if (fieldName.equals("content")) {
                    content = request.getParameter("content");
                } else if (fieldName.equals("password")) {
                    password = request.getParameter("password");
                } else if (fieldName.equals("file")) {
                    fileName = extractFileName(part);
                    if (fileName != null && !fileName.isEmpty()) {
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

            // DB 저장 로직 (FileUploadDAO 사용)
            if (title != null && author != null && content != null && password != null) {
                FileUploadVO vo = new FileUploadVO();
                vo.setTitle(title);
                vo.setAuthor(author);
                vo.setContent(content);
                vo.setPassword(password);

                FileUploadDAO dao = new FileUploadDAO();
                dao.insertFileUpload(vo);

                response.sendRedirect(request.getContextPath() + "/join/view/fileupload/fileuploadList.jsp"); // 목록 페이지로 리다이렉트
            } else {
                out.println("모든 필드를 입력하세요.");
            }
        } catch (Exception e) {
            out.println("오류 발생: " + e.getMessage());
        }
    }

    // 파일 이름 추출 메서드
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }
}



