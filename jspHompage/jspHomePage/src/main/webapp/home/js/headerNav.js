  const menuItems = document.querySelectorAll(".mainMenu > li");
  const subMenuContainer = document.querySelector(".subMenuContainer");
  const subMenus = document.querySelectorAll(".subMenu");
  const authButton = document.querySelector(".authButton");

  // 메뉴 항목 마우스 이벤트
  menuItems.forEach((menuItem) => {
    menuItem.addEventListener("mouseenter", () => {
      const targetMenu = menuItem.getAttribute("data-menu");

      // 모든 하위 메뉴 숨김
      subMenus.forEach((subMenu) => {
        subMenu.classList.remove("active");
      });

      // 활성화된 하위 메뉴 보이기
      const activeSubMenu = document.querySelector(
        `.subMenu[data-submenu="${targetMenu}"]`
      );
      if (activeSubMenu) {
        subMenus.forEach((subMenu) => {
          subMenu.style.opacity = "0";
        });
        activeSubMenu.classList.add("active");
        subMenuContainer.style.height = "80px"; // 하위 메뉴 틀 높이
        setTimeout(() => {
          activeSubMenu.style.opacity = "1";
        }, 150);
      }
    });
  });

  // 하위 메뉴 숨기기
  subMenuContainer.addEventListener("mouseleave", () => {
    subMenuContainer.style.height = "0";
    subMenus.forEach((subMenu) => {
      subMenu.classList.remove("active");
    });
  });

  // 로그인/로그아웃 버튼 클릭 이벤트


