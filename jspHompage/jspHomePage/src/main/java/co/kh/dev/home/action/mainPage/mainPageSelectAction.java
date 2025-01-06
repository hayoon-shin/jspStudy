package co.kh.dev.home.action.mainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.BoardDAO;
import co.kh.dev.home.model.BoardVO;
import co.kh.dev.home.model.NoticeDAO;
import co.kh.dev.home.model.NoticeVO;
import co.kh.dev.home.model.ShopDAO;
import co.kh.dev.home.model.ShopVO;

public class mainPageSelectAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Map<String, ShopVO> sMap= null;
		if(session.getAttribute("sMap")!=null) {
			sMap=(Map<String, ShopVO>) session.getAttribute("sMap");
		}
        ArrayList<ShopVO> sortList = new ArrayList<>();
        if (sMap != null) {
            sortList.addAll(sMap.values());
            Collections.reverse(sortList);
        }if(sortList.size()>4){
        	sortList.subList(4, sortList.size()).clear();
        }
		request.setAttribute("selectSList", sortList);
		
		ShopDAO sDAO = ShopDAO.getInstance();
		ShopVO svo = new ShopVO();
		svo.setType(1);
    	int srecordCount = sDAO.selectRecordDB(svo);
		int startListNum =(srecordCount-3<0)?1:srecordCount-3;
		int endListNum =srecordCount;
		ArrayList<ShopVO> sList =null;
		sList = sDAO.selectDB(startListNum, endListNum,svo);//원하는 구간의 db를 출력
		request.setAttribute("sList", sList);
		
		BoardDAO bDAO = BoardDAO.getInstance();
		int bviewTime = 8; // 한페이지에 보여줄 리스트 개수
		int brecordCount = bDAO.selectRecordDB(); // 전체 리스트 개수 findText값이 있을시 해당 항목으로 변경
		int bstartListNum = (brecordCount - bviewTime < 0) ? 1 : brecordCount - bviewTime; // 출력할 리스트번호 시작
		int bendListNum = brecordCount; // 출력할 리스트번호 마지막
		ArrayList<BoardVO> bList = bDAO.selectDB(bstartListNum, bendListNum);// 원하는 구간의 db를 출력
		request.setAttribute("bList", bList);
		// 공지사항
		NoticeDAO nDAO = NoticeDAO.getInstance();
		int nviewTime = 3; // 한페이지에 보여줄 리스트 개수
		int nrecordCount = nDAO.selectRecordDB(); // 전체 리스트 개수 findText값이 있을시 해당 항목으로 변경
		int nstartListNum = (nrecordCount - nviewTime < 0) ? 1 : nrecordCount - nviewTime; // 출력할 리스트번호 시작
		int nendListNum = nrecordCount; // 출력할 리스트번호 마지막
		ArrayList<NoticeVO> nList = nDAO.selectDB(nstartListNum, nendListNum);// 원하는 구간의 db를 출력
		request.setAttribute("nList", nList);

		ActionForward forward = new ActionForward("/home/mainPage.jsp", false);
		return forward;
	}

}
