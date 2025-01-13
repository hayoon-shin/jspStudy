/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.97
 * Generated at: 2025-01-12 04:59:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.join.view.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import com.kh.dev.join.model.BoardTwoVO;
import com.kh.dev.join.model.BoardTwoDAO;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/join/headerNav.jsp", Long.valueOf(1736656262404L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(4);
    _jspx_imports_classes.add("com.kh.dev.join.model.BoardTwoDAO");
    _jspx_imports_classes.add("com.kh.dev.join.model.BoardTwoVO");
    _jspx_imports_classes.add("java.util.ArrayList");
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
      out.write("\r\n");

    // 페이징 변수
    String pageNum = request.getParameter("pageNum");
    int currentPage;
    try {
        currentPage = (pageNum != null && !pageNum.isEmpty()) ? Integer.parseInt(pageNum) : 1;
    } catch (NumberFormatException e) {
        currentPage = 1; // 잘못된 값이 들어오면 기본값 설정
    }

    int pageSize = 10;
    int startRow = (currentPage - 1) * pageSize + 1;
    int endRow = startRow + pageSize - 1;

    // DAO 호출
    BoardTwoDAO boardDAO = BoardTwoDAO.getInstance();
    int count = boardDAO.selectCountDB();
    ArrayList<BoardTwoVO> articleList = boardDAO.selectStartEndDB(startRow, endRow);

    // 페이징 계산
    int pageCount = (int) Math.ceil((double) count / pageSize);
    int startPage = ((currentPage - 1) / pageSize) * pageSize + 1;
    int endPage = startPage + pageSize - 1;
    if (endPage > pageCount) {
        endPage = pageCount;
    }

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>게시판</title>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"../../headerNav.css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
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
      out.write("    <h1>게시판</h1>\r\n");
      out.write("    <table border=\"1\">\r\n");
      out.write("        <tr>\r\n");
      out.write("            <th>번호</th>\r\n");
      out.write("            <th>제목</th>\r\n");
      out.write("            <th>작성자</th>\r\n");
      out.write("            <th>작성일</th>\r\n");
      out.write("            <th>조회수</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        ");
 if (count == 0) { 
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td colspan=\"5\">게시판에 저장된 글이 없습니다.</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        ");
 } else {
            int number = count - startRow + 1;
            for (BoardTwoVO article : articleList) { 
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>");
      out.print( number-- );
      out.write("</td>\r\n");
      out.write("                <td><a href=\"content.jsp?num=");
      out.print( article.getNum() );
      out.write('"');
      out.write('>');
      out.print( article.getSubject() );
      out.write("</a></td>\r\n");
      out.write("                <td>");
      out.print( article.getWriter() );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( article.getRegdate() );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print( article.getReadcount() );
      out.write("</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        ");
 } } 
      out.write("\r\n");
      out.write("    </table>\r\n");
      out.write("\r\n");
      out.write("    <!-- 페이징 -->\r\n");
      out.write("    <div class=\"pagination\">\r\n");
      out.write("        ");
 if (startPage > 1) { 
      out.write("\r\n");
      out.write("            <a href=\"list.jsp?pageNum=");
      out.print( startPage - 1 );
      out.write("\">[이전]</a>\r\n");
      out.write("        ");
 }
        for (int i = startPage; i <= endPage; i++) { 
      out.write("\r\n");
      out.write("            <a href=\"list.jsp?pageNum=");
      out.print( i );
      out.write('"');
      out.write('>');
      out.print( i );
      out.write("</a>\r\n");
      out.write("        ");
 }
        if (endPage < pageCount) { 
      out.write("\r\n");
      out.write("            <a href=\"list.jsp?pageNum=");
      out.print( startPage + 1 );
      out.write("\">[다음]</a>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("    </div>\r\n");
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
