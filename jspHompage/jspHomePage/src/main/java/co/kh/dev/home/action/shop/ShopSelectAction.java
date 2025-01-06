package co.kh.dev.home.action.shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.ShopDAO;
import co.kh.dev.home.model.ShopVO;

public class ShopSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ShopDAO pDAO = ShopDAO.getInstance();
		ShopVO svo = new ShopVO();
		svo.setType(Integer.parseInt( request.getParameter("type")));
    	//객체들의 기본값 요소 설정
    	//항상 get 방식으로 전달되어야 하는 값들
    	int viewTime = 9; 						 // 한페이지에 보여줄 리스트 개수
    	int pageNum = 1;						 //페이지 넘버						
    	//get 방식으로 값을 받아서 입력
    	if (request.getParameter("viewTime") != null) {//viewTime request 값이 있으면 받아온다
    		viewTime = Integer.parseInt(request.getParameter("viewTime"));
    	}
    	if (request.getParameter("pageNum") != null) {//pageNum request 값이 있으면 받아온다
    		pageNum = Integer.parseInt(request.getParameter("pageNum"));
    	}
    	//데이터베이스 에서 가져오는 값
    	int recordCount = pDAO.selectRecordDB(svo); //전체 리스트 개수 findText값이 있을시 해당 항목으로 변경
    	//get 방식으로 받은 값으로 설정하는값들
    	int pagePlus=(recordCount%viewTime==0)?0:1;
    	int pageCount = recordCount/viewTime + pagePlus;	 //전체 페이지 개수
    	int pageStartNum = 1;					 //페이지 첫번째번호
		int pageEndNum = 10;					 //페이지 끝번호
		int startListNum =(recordCount-viewTime<0)?1:recordCount-viewTime;	 //출력할 리스트번호 시작
		int endListNum =recordCount;			 //출력할 리스트번호 마지막
		if (pageCount > 10) {						 //페이지개수가 10개를 넘어갈때 페이지 끝번호를 10으로 고정, 아니면 페이지수에 맞게 값 할당
			pageEndNum = 10;
		}else{
			pageEndNum = pageCount;
		}
		startListNum =(recordCount-viewTime*pageNum+1<0)?1:recordCount-viewTime*pageNum+1;
		endListNum =recordCount-viewTime*(pageNum-1);
		ArrayList<ShopVO> sList = pDAO.selectDB(startListNum, endListNum,svo);//원하는 구간의 db를 출력
		request.setAttribute("sList", sList);
		request.setAttribute("viewTime", viewTime);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageStartNum", pageStartNum);
		request.setAttribute("pageEndNum", pageEndNum);
		ActionForward forward = new ActionForward("/home/shop/shopPage.jsp", false);
		return forward;
	}

}
