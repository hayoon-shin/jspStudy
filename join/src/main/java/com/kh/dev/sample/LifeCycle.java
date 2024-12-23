package com.kh.dev.sample;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "lifeCycle", urlPatterns = { "/lifeCycle.do" })
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//생성자
    public LifeCycle() {
    	System.out.println("LifeServlet의 생성자 호출됨...");
    }
    
    @Override
	//LifeServlet의 초기화작업을 담당한다.
    //Servlet객체 생성시 단 한번 수행됨
    public void init(ServletConfig config) throws ServletException {
    	System.out.println("init() 호출됨...");
    }
    
    @Override
    //LifeServlet 객체가 WAS에서 소멸될 때 수행됨.
	public void destroy() {
    	System.out.println("destroy() 호출됨...");
	}
    
    @Override
    //클라이언트의 연결요청이 있을 때마다 호출된다.
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("service() 호출됨...");
    }

}
