package co.kh.dev.home.controller.board;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.model.CommentDAO;
import co.kh.dev.home.model.CommentVO;


@WebServlet({ "/boardCommentInsertServlet.do" })
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardCommentInsertServlet() {
        super();
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
    CommentDAO cmDAO = CommentDAO.getInstance();
    try {
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String customerId = (String) session.getAttribute("id");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int parentNo = ((request.getParameter("parentNo")==null))?0:Integer.parseInt(request.getParameter("parentNo"));
		int depth = ((request.getParameter("depth")==null))?0:Integer.parseInt(request.getParameter("depth"))+1;
		String content = request.getParameter("content");
		CommentVO cvo = new CommentVO(customerId, boardNo, parentNo, depth, content);
		cmDAO.insertDB(cvo);
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
    try {
		response.sendRedirect("/jspHomePage/boardContentSelectServlet.do?no="+Integer.parseInt(request.getParameter("boardNo")));
	} catch (IOException e) {
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
