//login登入頁面
const containerlogin = document.querySelector('.container-login');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');

registerLink.addEventListener('click', ()=>{
    containerlogin.classList.add('active');
});

loginLink.addEventListener('click', ()=>{
    containerlogin.classList.remove('active');
});

// 密碼固定英文+數字
const passwordInput = document.getElementById("password");

    passwordInput.addEventListener("input", (event) => {
        const input = event.target.value;
        const regex = /^[a-zA-Z0-9]*$/; // 正則表達式，只允許英文和數字
        if (!regex.test(input)) {
            // 如果輸入內容不符合正則表達式，則移除非英文和數字的字元
            event.target.value = input.replace(/[^a-zA-Z0-9]/g, "");
        }
    });
  