function openLoginPopup() {
    const width = 500;
    const height = 400;
    const left = (window.innerWidth - width) / 2;
    const top = (window.innerHeight - height) / 2;
    window.open(
        "/jspHomePage/home/loginPopup.jsp",
        "로그인",
        `width=${width},height=${height},top=${top},left=${left},resizable=no,scrollbars=no`
    );
}