function openLoginPopup() {
    const popup = window.open(
        "/jspHomePage/home/loginPopupPage.jsp",
        "로그인",
        "resizable=no,scrollbars=no"
    );

    // 팝업 창 로드 후 크기 조정
    popup.onload = function () {
        const doc = popup.document;
        
        // 정확한 콘텐츠 크기 계산
        const width = doc.documentElement.scrollWidth;
        const height = doc.documentElement.scrollHeight;

        // 창 크기 조정
        popup.resizeTo(600, height);

        // 창 위치 조정 (중앙 배치)
        const left = (window.screen.width - width) / 2;
        const top = (window.screen.height - height) / 2;
        popup.moveTo(left, top);
    };
}
