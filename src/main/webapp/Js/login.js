const containerlogin = document.querySelector('.container-login');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');

registerLink.addEventListener('click', ()=>{
    containerlogin.classList.add('active');
});

loginLink.addEventListener('click', ()=>{
    containerlogin.classList.remove('active');
});
