package com.kh.dev.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet(name = "myFirstServlet", urlPatterns = { "/myFirstServlet.do" })
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyFirstServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서버에서 UTF-8로 보낸다.
		response.setCharacterEncoding("UTF-8");
		//웹브라우저에서는 html문서이고 UTF-8로 읽어라.
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		Date date = new Date();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1> My First Servlet Program ! </h1>");
		out.println("<br>");
		out.println(date.toString());
		out.println("</body>");
		out.println("</html>");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
