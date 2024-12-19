<%@page import="java.io.IOException"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.DiskFileUpload"%>
<%@page import="org.apache.commons.fileupload.FileUpload"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%

request.setCharacterEncoding("UTF-8");
// 1.사용자가 전송할때 form에 enctype="multipart/form-data" 점검
if (FileUpload.isMultipartContent(request)) {
	// 2. 임시파일지정
	String temporaryDir = "C:\\temp";
	// 3. MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy()); 객체와 같은 기능
	DiskFileUpload fileUpload = new DiskFileUpload();
	// 3-1. 위치
	fileUpload.setRepositoryPath(temporaryDir);
	// 3-2. 사이즈. 최대 1메가까지 업로드 가능
	fileUpload.setSizeMax(1024 * 1024);
	// 3-3. 내부메모리에 들어올 사이즈를 결정. 한번에 100K 까지는 메모리에 저장. (얘가 멀티파트리퀘스트보다 속도가 빠름. 메모리로 직접 넣기때문에.)
	fileUpload.setSizeThreshold(1024 * 100);
	/* 4. Enumeration files = multi.getFileNames();와 같음   FileItem의 리스트 */
	// 4. (String) files. nextElement();와 비슷함
	java.util.List fileItemList = fileUpload.parseRequest(request);
	for (int i = 0; i < fileItemList.size(); i++) {
		// 4-1. 해당되는 파일정보를 가져온다
		FileItem fileItem = (FileItem) fileItemList.get(i);
		// 4-2 폼 파라미터에서 보내온 <input type="text", "password", "checkbox"...> 인가? (input type="file"아닌 경우만 찾음)
		if (fileItem.isFormField()) {// 파일 이외의 파라미터
		  out.println("폼 파라미터:"); 	
		// fileItem.getFieldName() - 이름을 가져오고, fileItem.getString() - 내용을 찍어주는 것
		  out.println(fileItem.getFieldName() +" = "+ fileItem.getString()+"<br>");
		} else { // 파일인 경우
			out.println("파일:"); 
			out.println(fileItem.getFieldName() 
					+" = "+ fileItem.getString()+"(" +fileItem.getSize() +"bytes) <br>");
			//메모리에 저장되어있는 파일이냐? 디스크에 저장되어있는 파일이냐? (C:\\temp)
			if (fileItem.isInMemory()) {
				out.println("메모리에 저장 <br>"); 
			} else {
				out.println("디스크에 저장 <br>"); 
			}
			// upload 위치를 알려준다.
			String filepath = application.getRealPath("upload");
			// 업로드한 파일이 사이즈가 있는지 체크, 존재하는 경우
			if (fileItem.getSize() > 0) {
				// C:\\temp\\afdfas\\safskfs.png \\ 없으면 위치는 -1
				int idx = fileItem.getName().lastIndexOf("\\");
				if (idx == -1) {
					//C:/temp/afdfas/safskfs.png // 없으면 위치는 -1
					idx = fileItem.getName().lastIndexOf("/");
				}
				// 썰어. safskfs.png
						
				String fileName = fileItem.getName().substring(idx + 1);
				
				try {
					// filepath String filepath = application.getRealPath("upload");
					// new File(upload/safskfs.png);
					File uploadedFile = new File(filepath, fileName);
					// C:\\temp\\safskfs.png => C:\\jspStudy\\upload\\safskfs.png
					fileItem.write(uploadedFile);
				} catch (IOException ex) {
				// 예외 처리
				}
			}// 업로드한 파일이 존재하는 경우
		}// 파일인 경우
	}//end of for
} else { //1.사용자가 전송할때 form에 enctype="multipart/form-data"
	out.println("인코딩 타입이 multipart/form-data가 아님.");
}
%>
