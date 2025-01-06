package co.kh.dev.home.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50   // 50MB
	)
@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "*.do" })
public class ControllerServlet extends HttpServlet {

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String cmd = request.getServletPath().substring(1).replace(".do", "");
			ActionFactory factory = ActionFactory.getInstance();
			Action action = factory.getAction(cmd);
			System.out.println(cmd);
			if (action != null) {
				ActionForward forward = action.execute(request, response);
				if(forward==null) {
				return;
				}
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getUrl());
				}else {
					request.getRequestDispatcher(forward.getUrl()).forward(request, response);
				}
			}else {
				response.sendRedirect("/jspHomePage/mainPageAlert.do?status=8");
			}
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
