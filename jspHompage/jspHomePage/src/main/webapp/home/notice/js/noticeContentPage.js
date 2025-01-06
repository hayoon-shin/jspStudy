function toggleDisp(togle1) {
    const element = document.getElementById(togle1);
    
    // dispNone 클래스 토글
    if (element.classList.contains("dispNone")) {
        element.classList.remove("dispNone"); // 클래스 제거
    } else {
        element.classList.add("dispNone");   // 클래스 추가
    }
}
 
 function deleteNotice(no) {
     if (confirm("공지사항을 삭제하시겠습니까?")) {
         // 확인을 눌렀을 때 실행
         window.location.href = "/jspHomePage/noticeListDelete.do?no="+no;
     } else {
         // 취소를 눌렀을 때 실행
         alert("삭제가 취소되었습니다.");
     }
 }