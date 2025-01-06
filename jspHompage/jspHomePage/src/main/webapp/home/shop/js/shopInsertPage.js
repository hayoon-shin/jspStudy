const productSelect = document.getElementById("productSelect");

    productSelect.addEventListener("change", () => {
        const selectedOption = productSelect.options[productSelect.selectedIndex];
        const price = selectedOption.getAttribute("data-price");
        document.getElementById("price").value = price || "";
    });

    function validateAndSubmit() {
        // 폼 요소 가져오기
        const form = document.querySelector('.productForm');
        const productSelect = document.getElementById("productSelect");
        const price = document.getElementById("price");
        const title = document.getElementById("title");
        const titleImg = document.getElementById("titleImg");
        const contentImgs = document.getElementById("contentImgs");
        const content = document.getElementById("content");

        // 유효성 검사
        if (!productSelect.value) {
            alert("상품을 선택하세요.");
            productSelect.focus();
            return;
        }

        if (!price.value) {
            alert("가격이 자동으로 입력되지 않았습니다. 상품을 다시 선택해주세요.");
            productSelect.focus();
            return;
        }

        if (!title.value.trim()) {
            alert("제목을 입력하세요.");
            title.focus();
            return;
        }

        if (!titleImg.value) {
            alert("타이틀 이미지를 업로드하세요.");
            titleImg.focus();
            return;
        }

        if (!contentImgs.value) {
            alert("내용 이미지를 최소 1개 이상 업로드하세요.");
            contentImgs.focus();
            return;
        }

        if (!content.value.trim()) {
            alert("내용을 입력하세요.");
            content.focus();
            return;
        }
        // 모든 값이 올바르게 입력되었으면 폼 제출
        form.submit(); // 폼 제출
    }

