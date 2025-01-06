package co.kh.dev.home.action.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.ShopDAO;
import co.kh.dev.home.model.ShopImgDAO;
import co.kh.dev.home.model.ShopImgVO;
import co.kh.dev.home.model.ShopVO;

public class ShopListSelectAction implements Action {
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		Map<String, ShopVO> sMap = new LinkedHashMap<String, ShopVO>();
		if(session.getAttribute("sMap")!=null) {
			sMap=(Map<String, ShopVO>) session.getAttribute("sMap");
		}
		ShopDAO sDAO = ShopDAO.getInstance();
    	ShopImgDAO siDAO = ShopImgDAO.getInstance();
    	ShopVO svo = new ShopVO();
    	ShopImgVO sivo = new ShopImgVO();
		ArrayList<ShopImgVO> siList= new ArrayList<ShopImgVO>();
    	request.setCharacterEncoding("UTF-8");
    	svo.setNo(Integer.parseInt(request.getParameter("no")));
    	svo.setType(Integer.parseInt(request.getParameter("type")));
    	svo = sDAO.selectListDB(svo);
    	sivo.setShopNo(svo.getNo());
    	siList= siDAO.selectContentDB(sivo);	
    	request.setAttribute("svo",svo);
    	request.setAttribute("siList",siList);
    	ShopVO key = sMap.put(request.getParameter("no"), svo);
    	if(key!=null) {
    		sMap.remove(request.getParameter("no"));
    		sMap.put(request.getParameter("no"), svo);
    	}
    	session.setAttribute("sMap", sMap);
		ActionForward forward = new ActionForward("/home/shop/shopListPage.jsp", false);
		return forward;
	}

}
