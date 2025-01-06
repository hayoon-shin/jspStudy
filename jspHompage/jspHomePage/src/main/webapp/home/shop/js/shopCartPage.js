function toggleAll(checkbox) {
	const checkboxes = document.querySelectorAll('input[name="cartNo"]');
	const eachPricesVal = document.querySelectorAll(`[class^="eachPrice"]`);
	let currnentPrice= 0;
	checkboxes.forEach(cb => cb.checked = checkbox.checked);
	eachPricesVal.forEach(data=>(currnentPrice += parseFloat(data.value)));
	if(checkbox.checked){
		document.querySelector(`#totalPrice`).value=currnentPrice;
	}else{
	document.querySelector(`#totalPrice`).value=0;
	}
}

function deleteCartItem(cartNo) {
	if (confirm("정말 삭제하시겠습니까?")) {
		// Ajax 요청으로 삭제 처리
		fetch(`/jspHomePage/shopCartDelete.do?no=${cartNo}`, { method: 'POST' }).then(response => response.text()).then(data => {
			if (data === "SUCCESS") {
				alert("삭제되었습니다.")
				window.location.reload();
			} else {
				alert("잘못된 접근입니다.")
			}
		}
		)
	}
}

function changeQt(cartNo, cartPrice) {
	const qt = document.querySelector(`.quantity-input${cartNo}`);
	const ecPrice = document.querySelector(`.eachPrice${cartNo}`);
	ecPrice.value = qt.value * cartPrice;

}

function setPrice(checkbox) {
	const nowPrice = document.querySelector(`#totalPrice`);
	const ecPrice = document.querySelector(`.eachPrice${checkbox.value}`);
	const qtInput = document.querySelector(`.quantity-input${checkbox.value}`);
	const totalPriceSpan = document.getElementById("totalPrice");
	const currentTotal = parseFloat(nowPrice.value) || 0;
	const eachPrice = parseFloat(ecPrice.value) || 0;
	if (checkbox.checked) {
		nowPrice.value = currentTotal + eachPrice;
		qtInput.readOnly=true;
	} else {
		nowPrice.value = currentTotal - eachPrice;
		qtInput.readOnly=false;
	}
	
}
 