
// 패턴검색 내용
  const idPattern = /^[\w]{3,}$/;
  const pwdPattern = /^[\w]{6,10}$/;
  const namePattern = /^[가-힣]{2,4}|[A-Z]{1}[a-zA-Z\x20]{1,9}$/;
  const nickNamePattern = /^[\w가-힣]{4,}$/;
  const emailPattern = /^[a-z0-9_+.-]+@([a-z0-9-]+.)+[a-z0-9]{2,4}$/;
  const telPattern = /^\d{2,3}-\d{3,4}-\d{4}$/;
  const mobilePattern = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;
  const datePattern = /^\d{4}-\d{2}-\d{2}$/;
  // 폼 객체찾기

  const myForm = document.querySelector(".myForm");
  //   객체찾기
  const inputId = document.querySelector("#input-id");
  const inputPwd = document.querySelector("#input-pw1");
  const inputPwd2 = document.querySelector("#input-pw2");
  const inputName = document.querySelector("#input-name");
  const inputNickName = document.querySelector("#input-nickname");
  const inputEmail = document.querySelector("#input-email");
  const inputTel = document.querySelector("#input-tel");
  const inputMobile = document.querySelector("#input-mobile");
  const btnSearchAddr = document.querySelector("#btn-searchAddr");
  const inputDate = document.querySelector(`[type="date"]`);
  //   주소 객체	 찾기
  const zipcode = document.querySelector("#zipcode");
  const addr1 = document.querySelector("#addr1");
  const addr2 = document.querySelector("#addr2");
  // 폼 이벤트 등록

  myForm.addEventListener("submit", (e) => {
    e.preventDefault(); //서버에 전송하는 기능 막기
    validate(inputId, idPattern, "아이디");
    validate(inputPwd, pwdPattern, "비밀번호");
    validate(inputName, namePattern, "이름");
    validate(inputNickName, nickNamePattern, "닉네임");
    validate(inputEmail, emailPattern, "이메일");
    validate(inputTel, telPattern, "전화번호");
    validate(inputMobile, mobilePattern, "전화번호");
    validate(inputDate, datePattern, "날짜");
    if (
      document.getElementById("zipcode").value === "" ||
      document.getElementById("addr1").value === ""
    ) {
      zipcode.nextSibling.textContent = "주소를 입력해주세요.";
      zipcode.focus();
      return;
    }
    myForm.submit();
  });
  
  function validate(userInput, myPattern, string) {
    if (userInput.value.match(myPattern)) {
      userInput.nextSibling.textContent = `사용가능한 ${string}입니다.`;
      userInput.nextSibling.style.color = "green";
    } else {
      userInput.nextSibling.innerHTML = `&nbsp;사용 불가능한 ${string}입니다.`;
      userInput.nextSibling.style.color = "red";
      userInput.value = "";
      userInput.focus();
      return;
    }
  }
  


  inputId.addEventListener("blur", () => {
    validate(inputId, idPattern, "아이디");
  });
  inputPwd.addEventListener("blur", () => {
    validate(inputPwd, pwdPattern, "비밀번호");
  });
  inputPwd2.addEventListener("blur", () => {
    if (inputPwd.value !== inputPwd2.value) {
      inputPwd2.nextSibling.textContent = `비밀번호가 일치하지 않습니다`;
      inputPwd2.nextSibling.style.color = "red";
    } else {
      inputPwd2.nextSibling.textContent = `비밀번호가 일치합니다`;
      inputPwd2.nextSibling.style.color = "green";
    }
    
  });
  inputName.addEventListener("blur", () => {
    validate(inputName, namePattern, "이름");
  });
  inputNickName.addEventListener("blur", () => {
    validate(inputNickName, nickNamePattern, "닉네임");
  });
  inputEmail.addEventListener("blur", () => {
    validate(inputEmail, emailPattern, "이메일");
  });
  inputTel.addEventListener("blur", () => {
    validate(inputTel, telPattern, "전화번호");
  });
  inputMobile.addEventListener("blur", () => {
    validate(inputMobile, mobilePattern, "전화번호");
  });

  inputDate.addEventListener("blur", () => {
    validate(inputDate, datePattern, "날짜");
  });
  
  
  function zipCheck(){
  url="zipCheckPage.jsp?check=y";
  window.open(url,"post","toolbar=no ,width=500 ,height=300 ,directories=no,status=yes,scrollbars=yes,menubar=no");
  }
  
  function dongCheck(){
  let value = document.zipForm.dong.value;
  if(value === ""){
  alert("동이름을 입력해 주세요.");
  document.zipForm.dong.focus();
  return;
  }
  document.zipForm.submit();
  }
  
  
  function sendAddress(zipcode,sido,gugun,dong,bunji){
  var address =sido+" "+gugun+" "+dong+" "+bunji;
  opener.document.regForm.zipcode.value=zipcode;
  opener.document.regForm.address1.value=address;
  self.close();
  }
  
  document.getElementById("checkIdBtn").addEventListener("click", function () {
      const userId = document.getElementById("input-id").value;

      if (userId.trim() === "") {
          alert("아이디를 입력하세요.");
          return;
      }

      fetch("/jspHomePage/customerIdDupCheck.do", {
          method: "POST",
          headers: {
              "Content-Type": "application/x-www-form-urlencoded"
          },
          body: `userId=${encodeURIComponent(userId)}`
      })
          .then(response => response.text())
          .then(result => {
              if (result === "SUCCESS") {
                  alert("사용 가능한 아이디입니다.");
              } else {
                  alert("이미 사용 중인 아이디입니다.");
              }
          });
  });

  document.addEventListener("DOMContentLoaded", () => {
      document.getElementById("btn-join").addEventListener("click", function (e) {
          e.preventDefault();  // 기본 제출 방지

          const form = document.getElementById("regForm");
          const userId = document.getElementById("input-id").value;
          fetch("/jspHomePage/customerInsertCheck.do", {
              method: "POST",
              headers: {
                  "Content-Type": "application/x-www-form-urlencoded"
              },
              body: `userId=${encodeURIComponent(userId)}`
          })
          .then(response => response.text())
          .then(result => {
              console.log("서버 응답 결과:", result);  // 디버깅 로그 추가
              if (result === "FALSE") {
                  alert("아이디 중복체크가 필요합니다.");
              } else {
                  form.submit();  // 폼 직접 제출
              }
          })
      });
  });
