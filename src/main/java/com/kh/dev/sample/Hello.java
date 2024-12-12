package com.kh.dev.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "hello", urlPatterns = { "/hello.do" })
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String tel=null;
	private String email=null;
	
    public Hello() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("초기화 메소드 수행됨");
		// ServletConfig의 초기 파라미터 값 읽기
		if(tel != null && email != null) {
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>My First Servlet Program shy</h1>");
		out.println("<br>");
		out.println(date.toString());
		out.println("<br>");
		out.println("<li>전화번호 : "+tel+" </li>");
		out.println("<li>이메일 : "+email+" </li>");
		out.println("</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
