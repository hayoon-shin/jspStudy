package co.kh.dev.home.controller.user;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.model.CustomerDAO;
import co.kh.dev.home.model.CustomerVO;

@WebServlet({ "/BoardLoginCheckServlet.do" })
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginCheckServlet() {
        super();
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
    	CustomerDAO cDAO = CustomerDAO.getInstance(); 
     	HttpSession session = request.getSession(false);
     	String id = request.getParameter("id");
     	String pwd = request.getParameter("pwd");
     	CustomerVO cvo = new CustomerVO();
     	cvo.setId(id);
     	cvo.setPwd(pwd);
     	try {
     		if (cDAO.selectByIdDB(cvo)==null) {
     	response.sendRedirect(request.getContextPath()+"/mainPageAlertServlet.do?status=4");//아이디일치 x
     		}else {
     	if (cDAO.selectLoginCheckDB(cvo)==null) {
     		response.sendRedirect(request.getContextPath()+"/mainPageAlertServlet.do?status=5");//비밀번호일치 x
     	}else {
     		session.setAttribute("id", id);
     		session.setAttribute("pwd", pwd);
     		response.sendRedirect(request.getContextPath()+"/mainPageAlertServlet.do?status=3");
     	}
     		}
     	} catch (Exception e) {
     		e.printStackTrace();
     	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		processRequest(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		processRequest(request, response);
	}

}
