package com.kh.dev.join.controller;

import java.io.*;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kh.dev.join.model.FileUploadDAO;
import com.kh.dev.join.model.FileUploadVO;

// URL 매핑
@WebServlet("/join/controller/fileuploadWriteProc.do")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class FileUploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads"; // 업로드 디렉토리 이름

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // 한글 처리
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("FileUploadServlet invoked");
        
        PrintWriter out = response.getWriter();
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // 업로드 디렉토리 생성
        }

        String title = null;
        String author = null;
        String content = null;
        String password = null;
        String fileName = null;
        long fileSize = 0;

        int boardId = 0;

        // 게시글 데이터 및 파일 데이터 처리
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
                        fileSize = part.getSize();
                        part.write(filePath); // 파일 저장
                    }
                }
            }

            // 게시글 데이터 저장
            if (title != null && author != null && content != null && password != null) {
                FileUploadVO vo = new FileUploadVO();
                vo.setTitle(title);
                vo.setAuthor(author);
                vo.setContent(content);
                vo.setPassword(password);

                FileUploadDAO dao = new FileUploadDAO();
                try {
                    boardId = dao.insertFileUpload(vo); // 게시글 삽입 후 생성된 ID 반환

                    // 파일 정보 저장
                    if (boardId > 0 && fileName != null && !fileName.isEmpty()) {
                        dao.saveFileInfo(boardId, fileName, uploadPath + File.separator + fileName, fileSize);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ServletException("데이터 저장 중 오류 발생: " + e.getMessage());
                } finally {
                    dao.close();
                }

                // 성공적으로 처리되면 목록 페이지로 리다이렉트
                response.sendRedirect(request.getContextPath() + "/join/view/fileupload/fileuploadList.jsp");
            } else {
                throw new IllegalArgumentException("모든 필드를 올바르게 입력해야 합니다.");
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
