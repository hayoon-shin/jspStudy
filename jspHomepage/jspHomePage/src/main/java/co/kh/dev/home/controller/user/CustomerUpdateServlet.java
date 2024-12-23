package co.kh.dev.home.controller.user;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import co.kh.dev.common.MyUtility;
import co.kh.dev.home.model.CustomerDAO;
import co.kh.dev.home.model.CustomerVO;

@WebServlet({ "/customerUpdateServlet.do" })
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerUpdateServlet() {
		super();
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(false);
			CustomerVO cvo = MyUtility.returnCvoBySession(session);
			CustomerDAO cDAO = CustomerDAO.getInstance();
			String status = null;
			CustomerVO cvo2 = new CustomerVO();
			cvo2.setId(request.getParameter("id"));
			cvo2.setPwd(request.getParameter("pwd"));
			cvo2.setName(request.getParameter("name"));
			cvo2.setNickName(request.getParameter("nickName"));
			cvo2.setEmail(request.getParameter("email"));
			cvo2.setTel(request.getParameter("tel"));
			cvo2.setPhone(request.getParameter("phone"));
			cvo2.setBirth(request.getParameter("birth"));
			cvo2.setZipCode(request.getParameter("zipCode"));
			cvo2.setAddress1(request.getParameter("address1"));
			cvo2.setAddress2(request.getParameter("address2"));
			if (cvo == null) {
				response.sendRedirect(request.getContextPath() + "/mainPageAlertServlet.jsp?status=8");
				return;
			}
			boolean flag = cDAO.updateDB(cvo2);
			if (flag) {
				status = "1";
			} else {
				status = "2";
			}
			response.sendRedirect(request.getContextPath() + "/myPageAlertServlet.do?status=" + status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

}
