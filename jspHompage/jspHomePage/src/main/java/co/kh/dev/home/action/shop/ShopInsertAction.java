package co.kh.dev.home.action.shop;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import co.kh.dev.home.action.Action;
import co.kh.dev.home.control.ActionForward;
import co.kh.dev.home.model.ShopDAO;
import co.kh.dev.home.model.ShopImgDAO;
import co.kh.dev.home.model.ShopImgVO;
import co.kh.dev.home.model.ShopVO;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50   // 50MB
	)
public class ShopInsertAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 업로드 경로 설정
		String title = null;
		String content = null;
		int productNo = 0;
		ShopVO svo = new ShopVO();
		ShopDAO sDAO = ShopDAO.getInstance();
		ShopImgDAO siDAO = ShopImgDAO.getInstance();
		try {
			for (Part part : request.getParts()) {
				if (part.getName().equals("title")) { // 텍스트 데이터
					title = new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
				} else if (part.getName().equals("content")) { // 텍스트 데이터
					content = new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
				} else if (part.getName().equals("productNo")) { // 파일 데이터
					productNo = Integer.parseInt(new String(part.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
				}
			}
		} catch (NumberFormatException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			svo = new ShopVO(Integer.parseInt(request.getParameter("type")), productNo, title, content);
			System.out.println(request.getParameter("type"));
			sDAO.insertDB(svo);
			svo = sDAO.selectLastDB();
			String uploadPath = request.getServletContext().getRealPath("home/uploads");
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
			    uploadDir.mkdirs(); // 경로에 디렉토리가 없으면 생성
			}
			// 파일 저장 처리
			int contentFileIndex = 1; // CONTENT 파일 순서
			int titleFileIndex = 1; // TITLE 파일 순서
			try {
				for (Part part : request.getParts()) {
				if (part.getName().equals("contentImgs")) { // 다중 파일 (CONTENT)
					String fileName = svo.getNo() + "_content_" + contentFileIndex;
					String filePath = uploadPath + File.separator + fileName;
					String httpUrl = "/home/uploads/" + fileName;
					ShopImgVO sivo = new ShopImgVO("CONTENT", svo.getNo(), httpUrl);
					siDAO.insertDB(sivo); // DB에 삽입
					part.write(filePath); // 파일 저장
					contentFileIndex++;
				} else if (part.getName().equals("titleImg")) { // 단일 파일 (TITLE)
					String fileName = svo.getNo() + "_title_" + titleFileIndex;
					String filePath = uploadPath + File.separator + fileName;
					String httpUrl = "/home/uploads/" + fileName;
					ShopImgVO sivo = new ShopImgVO("TITLE", svo.getNo(), httpUrl);
					siDAO.insertDB(sivo); // DB에 삽입
					part.write(filePath); // 파일 저장
					titleFileIndex++;
				}
}
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		ActionForward forward = new ActionForward(request.getContextPath()+"/shopPageAlert.do?type=" + svo.getType()+"&status=2", true);
		return forward;
	}

}
