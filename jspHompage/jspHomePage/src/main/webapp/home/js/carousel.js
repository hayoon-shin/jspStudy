const all = document.querySelector(".carousel"); //전체문단 객체
const slideObj = document.querySelectorAll(".slide"); //슬라이드 이미지 객체 배열[]
const navLeft = document.getElementById("navgateleft"); //왼쪽으로 이동
const navRight = document.getElementById("navgateright"); //오른쪽으로 이동
const indicateA = document.querySelectorAll(`#indicator>a`); //하단에 이동용
// 현재 이미지 인덱스

// zIndex를 기록할 리스트를 제작
// 1이면 최상단 0이면 최하단으로
let list2 = [0, 100, 200, -100];

//초기 위치를 배치
for (let i = 0; i < list2.length; i++) {
  slideObj[i].style.left = `${list2[i]}%`;
}
// 자리 재배치 함수
function listArray() {
  for (let i = 0; i < list2.length; i++) {
    slideObj[i].style.left = `${list2[i]}%`;
  }
  for (let i = 0; i < indicateA.length; i++) {
    indicateA[i].classList.remove("active");
  }
  indicateA[list2.indexOf(0)].classList.add("active");
}
// 0,1,2,3 배열중 0-1을 하면 3이나오고 3+1을 했을때 0이 나오게만드는 식 num은 0+1, num2는 배열의수
function calcul(num, num2) {
  return (num + num2) % num2;
}
// 위치가 0인 배열을 기준으로 양옆의 배열을 변수값만큼 이동
function moveImg(move) {
  slideObj[list2.indexOf(0)].style.transform = `translate(${move})`;
  slideObj[
    calcul(list2.indexOf(0) + 1, 4)
  ].style.transform = `translate(${move})`;
  slideObj[
    calcul(list2.indexOf(0) - 1, 4)
  ].style.transform = `translate(${move})`;
}
// 위치가 0인 배열을 기준으로 양옆의 배열의 transition을 변경
function transitionImg(second) {
  slideObj[list2.indexOf(0)].style.transition = `${second}`;
  slideObj[
    calcul(list2.indexOf(0) + 1, list2.length)
  ].style.transition = `${second}`;
  slideObj[
    calcul(list2.indexOf(0) - 1, list2.length)
  ].style.transition = `${second}`;
}
// 왼쪽 버튼에 이벤트 리스너 추가
navLeft.addEventListener("click", () => {
  transitionImg("1s");
  moveImg("100%");
  setTimeout(() => {
    transitionImg("0s"); // transition 초기화
    moveImg("0");
    list2.push(list2.shift()); // 배열 업데이트
    listArray(); // 슬라이드 재배치
  }, 1000); // 애니메이션 시간(1초)에 맞춰 대기
});
// 누르면 오른쪽으로 가는 기능
function moveRight() {
  transitionImg("1s");
  moveImg("-100%");
  setTimeout(() => {
    transitionImg("0s"); // transition 초기화
    moveImg("0");
    list2.unshift(list2.pop()); // 배열 업데이트
    listArray(); // 슬라이드 재배치
  }, 1000); // 애니메이션 시간(1초)에 맞춰 대기
}
// 오른쪽 핸들에 기능 추가
navRight.addEventListener("click", moveRight);

// 인터벌을 5초로
function startTimer() {
  timerID = setInterval(moveRight, 3000);
}
startTimer();
all.addEventListener("mouseenter", () => {
  clearInterval(timerID);
});
all.addEventListener("mouseleave", () => {
  startTimer();
});

for (let i = 0; i < indicateA.length; i++) {
  indicateA[i].addEventListener("click", () => {
    list2 = [0, 0, 0, 0];
    list2[calcul(i + 1, list2.length)] = 100;
    list2[calcul(i - 1, list2.length)] = -100;
    list2[calcul(i + 2, list2.length)] = 200;
    listArray();
  });
}
