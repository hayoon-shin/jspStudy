/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.97
 * Generated at: 2025-01-12 04:59:45 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.join.view.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class writeForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/join/headerNav.jsp", Long.valueOf(1736656262404L));
    _jspx_dependants.put("/join/view/board/color.jsp", Long.valueOf(1734049864000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');

String bodyback_c = "#e0ffff";
String back_c = "#8fbc8f";
String title_c = "#5f9ea0";
String value_c = "#b0e0e6";
String bar = "#778899";

      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');

	//새로운글로 입력하던지(num=0, ref=0, step=0, depth=0) ,
	//부모글을 누르고 입력한다(num=부모값, ref=부모값, step=부모값, depth=부모값).
  int num=0, ref=0, step=0, depth=0;
  try{  
    if(request.getParameter("num")!=null){
			num = Integer.parseInt(request.getParameter("num"));
			ref = Integer.parseInt(request.getParameter("ref"));
			step = Integer.parseInt(request.getParameter("step"));
			depth = Integer.parseInt(request.getParameter("depth"));
	  }

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>My Board</title>\r\n");
      out.write("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link rel=\"stylesheet\" href=\"../../headerNav.css\">\r\n");
      out.write("<script language=\"javascript\"\r\n");
      out.write("	src=\"script.js?timestamp=");
      out.print( System.currentTimeMillis() );
      out.write("\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<!--// 나중에 새글 답별글 구분하는 코드 추가 <1> -->\r\n");
      out.write("<body bgcolor=\"");
      out.print(bodyback_c);
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<header>\r\n");
      out.write("        <div class=\"logo_set\">\r\n");
      out.write("            <a href=\"../../main.jsp\"><i class=\"fa-brands fa-stack-overflow\"></i><span style=\"line-height: 90px; padding-left: 20px;\">stack overflow</span></a>\r\n");
      out.write("        </div>\r\n");
      out.write("        <ul class=\"dropdown_list\">\r\n");
      out.write("            <li><a href=\"/join/join/view/join/register.jsp\" class=\"dropbtn\">회원가입</a>\r\n");
      out.write("                <div class=\"dropdown-content\">\r\n");
      out.write("                    <a href=\"/join/join/view/login/login.jsp\">로그인</a>\r\n");
      out.write("                    <a href=\"/join/join/view/login/mypage.jsp\">마이페이지</a>\r\n");
      out.write("                    <a href=\"#\">menu3</a>\r\n");
      out.write("                </div></li>\r\n");
      out.write("            <li><a href=\"/join/join/view/board/writeForm.jsp\" class=\"dropbtn\">게시판</a>\r\n");
      out.write("                <div class=\"dropdown-content\">\r\n");
      out.write("                    <a href=\"/join/join/view/notice/list.jsp\">공지사항</a>\r\n");
      out.write("                    <a href=\"/join/join/view/fileupload/fileuploadList.jsp\">자료실</a>\r\n");
      out.write("                    <a href=\"#\">menu3</a>\r\n");
      out.write("                </div></li>\r\n");
      out.write("            <li><a href=\"#\" class=\"dropbtn\">menu</a>\r\n");
      out.write("                <div class=\"dropdown-content\">\r\n");
      out.write("                    <a href=\"#\">menu1</a>\r\n");
      out.write("                    <a href=\"#\">menu2</a>\r\n");
      out.write("                    <a href=\"#\">menu3</a>\r\n");
      out.write("                </div></li>\r\n");
      out.write("            <li><a href=\"#\" class=\"dropbtn\">menu</a>\r\n");
      out.write("                <div class=\"dropdown-content\">\r\n");
      out.write("                    <a href=\"#\">menu1</a>\r\n");
      out.write("                    <a href=\"#\">menu2</a>\r\n");
      out.write("                    <a href=\"#\">menu3</a>\r\n");
      out.write("                </div></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <div class=\"icon_set\">\r\n");
      out.write("            <a href=\"#\"><i class=\"fa-brands fa-facebook\"></i></a>\r\n");
      out.write("            <a href=\"#\"><i class=\"fa-brands fa-instagram\"></i></a>\r\n");
      out.write("        </div>\r\n");
      out.write("    </header>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("	<main>\r\n");
      out.write("		<h1>글쓰기</h1>\r\n");
      out.write("	<br></br>\r\n");
      out.write("	<form method=\"post\" name=\"writeForm\" action=\"writeProc.jsp\"\r\n");
      out.write("		onsubmit=\"return writeSave()\">\r\n");
      out.write("		<input type=\"hidden\" name=\"num\" value=\"");
      out.print(num);
      out.write("\">\r\n");
      out.write("		<input type=\"hidden\" name=\"ref\" value=\"");
      out.print(ref);
      out.write("\">\r\n");
      out.write("		<input type=\"hidden\" name=\"step\" value=\"");
      out.print(step);
      out.write("\">\r\n");
      out.write("		<input type=\"hidden\" name=\"depth\" value=\"");
      out.print(depth);
      out.write("\">  \r\n");
      out.write("		<label for=\"writer\">작성자</label>\r\n");
      out.write("            <input type=\"text\" id=\"writer\" name=\"writer\" required>\r\n");
      out.write("\r\n");
      out.write("            <label for=\"subject\">제목</label>\r\n");
      out.write("            <td width=\"330\">\r\n");
      out.write("                ");

                    if (request.getParameter("num") == null) {
                
      out.write("\r\n");
      out.write("                    <input type=\"text\" id=\"subject\" size=\"50\" maxlength=\"50\" name=\"subject\" required>\r\n");
      out.write("                ");

                    } else {
                
      out.write("\r\n");
      out.write("                    <input type=\"text\" id=\"subject\" size=\"50\" maxlength=\"50\" name=\"subject\" value=\"[답변]\" required>\r\n");
      out.write("                ");

                    }
                
      out.write("\r\n");
      out.write("            </td>\r\n");
      out.write("\r\n");
      out.write("            <label for=\"content\">내용</label>\r\n");
      out.write("            <textarea id=\"content\" name=\"content\" rows=\"10\" required></textarea>\r\n");
      out.write("			<label for=\"email\">이메일</label>\r\n");
      out.write("			<input type=\"email\" id=\"email\" name=\"email\" required>\r\n");
      out.write("			<label for=\"pass\">비밀번호</label>\r\n");
      out.write("            <input type=\"password\" id=\"pass\" name=\"pass\" required>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"button-group\">\r\n");
      out.write("                <button type=\"submit\">작성</button>\r\n");
      out.write("                <button type=\"button\" onclick=\"location.href='list.jsp'\">목록</button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </form>\r\n");
      out.write("    </main>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");

	}catch(Exception e){} 

    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
