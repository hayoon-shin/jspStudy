/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.97
 * Generated at: 2024-12-24 09:45:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.join.view.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.kh.dev.join.model.BoardTwoDAO;
import com.kh.dev.join.model.BoardTwoVO;

public final class updateForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(3);
    _jspx_imports_classes.add("com.kh.dev.join.model.BoardTwoDAO");
    _jspx_imports_classes.add("com.kh.dev.join.model.BoardTwoVO");
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    // 전달받은 글 번호와 페이지 번호
    String num = request.getParameter("num");
    String pageNum = request.getParameter("pageNum");
    String writer = request.getParameter("writer");
    String subject = request.getParameter("subject");
    String content = request.getParameter("content");
    String email = request.getParameter("email");

    // 글 번호가 없으면 오류 처리
    if (num == null || num.trim().isEmpty()) {
        out.println("<script>alert('글 번호가 전달되지 않았습니다.'); history.back();</script>");
        return;
    }

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"ko\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"style.css\">\r\n");
      out.write("    <title>글 수정</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <main>\r\n");
      out.write("        <h1>글 수정</h1>\r\n");
      out.write("        <form action=\"updateProc.jsp\" method=\"post\">\r\n");
      out.write("            <!-- 글 번호와 페이지 번호 전달 -->\r\n");
      out.write("            <input type=\"hidden\" name=\"num\" value=\"");
      out.print( num );
      out.write("\">\r\n");
      out.write("            <input type=\"hidden\" name=\"pageNum\" value=\"");
      out.print( pageNum );
      out.write("\">\r\n");
      out.write("\r\n");
      out.write("            <label for=\"writer\">작성자:</label>\r\n");
      out.write("            <input type=\"text\" id=\"writer\" name=\"writer\" value=\"");
      out.print( writer != null ? writer : "" );
      out.write("\" required>\r\n");
      out.write("\r\n");
      out.write("            <label for=\"subject\">제목:</label>\r\n");
      out.write("            <input type=\"text\" id=\"subject\" name=\"subject\" value=\"");
      out.print( subject != null ? subject : "" );
      out.write("\" required>\r\n");
      out.write("\r\n");
      out.write("            <label for=\"content\">내용:</label>\r\n");
      out.write("            <textarea id=\"content\" name=\"content\" required>");
      out.print( content != null ? content : "" );
      out.write("</textarea>\r\n");
      out.write("\r\n");
      out.write("            <label for=\"email\">이메일:</label>\r\n");
      out.write("            <input type=\"email\" id=\"email\" name=\"email\" value=\"");
      out.print( email != null ? email : "" );
      out.write("\" required>\r\n");
      out.write("\r\n");
      out.write("            <label for=\"pass\">비밀번호:</label>\r\n");
      out.write("            <input type=\"password\" id=\"pass\" name=\"pass\" required>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"button-group\">\r\n");
      out.write("                <button type=\"submit\">수정</button>\r\n");
      out.write("                <button type=\"button\" onclick=\"history.back();\">취소</button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </form>\r\n");
      out.write("    </main>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
