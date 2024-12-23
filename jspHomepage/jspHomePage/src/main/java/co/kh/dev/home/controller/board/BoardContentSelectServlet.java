package co.kh.dev.home.controller.board;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.model.BoardDAO;
import co.kh.dev.home.model.BoardVO;
import co.kh.dev.home.model.CommentDAO;
import co.kh.dev.home.model.CommentVO;

@WebServlet({ "/boardContentSelectServlet.do" })
public class BoardContentSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardContentSelectServlet() {
        super();
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
    	BoardDAO bDAO = BoardDAO.getInstance();
    	CommentDAO cmDAO = CommentDAO.getInstance();
    	ArrayList<CommentVO> cmList = new ArrayList<CommentVO>();
    	BoardVO bvo = new BoardVO();
    	try {
			request.setCharacterEncoding("UTF-8");
    	bvo.setNo(Integer.parseInt(request.getParameter("no")));
    	bvo = bDAO.selectByNoDB(bvo);
    	bvo.setCount(bvo.getCount()+1);
    	bDAO.updateDB(bvo);
    	cmList = cmDAO.selectByBoardNoDB(bvo);
    	request.setAttribute("bvo",bvo);
		request.setAttribute("cmList", cmList);
		request.getRequestDispatcher("/home/boardContentPage.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
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
