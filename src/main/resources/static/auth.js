'use strict'

const registerForm = document.getElementById('register-post-form');
if (registerForm != null) {
    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        let formData = new FormData(event.target);
        let loginFormData = new FormData();
        if (checkRegisterCaptcha() !== true) {
            alert("Капча введена не правильно");
            window.location.href = 'http://localhost:8089/register';
        } else {
            loginFormData.append('password', document.getElementById('register-password').value);
            const email = document.getElementById('register-email').value;
            loginFormData.append('email', email);
            const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
            await fetch('http://localhost:8089/register', {
                method: 'POST',
                headers: {
                    'Content-type': 'application/json',
                    'X-CSRF-TOKEN': csrfToken
                },
                body: JSON.stringify(Object.fromEntries(formData))
            });
            window.location.href = 'http://localhost:8089/login';
        }
    })
}

const firstNum = generateRandomNumber();
const secondNum = generateRandomNumber();
const captcha = document.getElementById('captcha-register');

if (window.location.href === 'http://localhost:8089/register') {
    captcha.setAttribute('placeholder', '' + firstNum + "+" + secondNum + '');
}

function checkRegisterCaptcha() {
    const answer = firstNum + secondNum;
    const number = parseInt(captcha.value);
    return answer === number;
}

function generateRandomNumber() {
    return Math.floor(Math.random() * 10);
}