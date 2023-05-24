'use strict'

const sendForgottenEmail = document.getElementById('send-forgotten-email');
if (sendForgottenEmail) {
    sendForgottenEmail.addEventListener('click', sendEmailCode);
}

function sendEmailCode() {
    const forgottenEmailDiv = document.getElementById('forgotten-email-div');
    if (forgottenEmailDiv.style.display === 'block') {
        forgottenEmailDiv.style.display = 'none';
    }
    const recoveryCodeDiv = document.getElementById('recovery-code-div');
    if (recoveryCodeDiv.style.display === 'none') {
        recoveryCodeDiv.style.display = 'block';
    }
    const email = document.getElementById('forgotten-email-input').value;
    const code = generateRandomCode(10);
    localStorage.setItem('pswEmail', email);
    localStorage.setItem('code', code);
    const p = document.getElementById('recovery-code');
    p.innerHTML = code;
}

const sendForgottenCode = document.getElementById('send-forgotten-code');
if (sendForgottenCode) {
    sendForgottenCode.addEventListener('click', sendCode);
}

function sendCode() {
    const code = localStorage.getItem('code');
    const inputCode = document.getElementById('forgotten-code-input').value;
    if (code === inputCode) {
        const changePswDiv = document.getElementById('change-password-div');
        const recoveryCodeDiv = document.getElementById('recovery-code-div');
        if (recoveryCodeDiv.style.display === 'block') {
            recoveryCodeDiv.style.display = 'none';
        }
        if (changePswDiv.style.display === 'none') {
            changePswDiv.style.display = 'block';
        }
        const currentEmail = document.getElementById('current-email');
        currentEmail.innerHTML = localStorage.getItem('pswEmail');
        localStorage.removeItem('code');
    } else {
        alert('Код подтверждения не правильный');
    }
}

const changePassword = document.getElementById('change-password');
if (changePassword) {
    changePassword.addEventListener('click', sendNewPassword);
}

async function sendNewPassword(e) {
    e.preventDefault();
    const email = localStorage.getItem('pswEmail');
    const password = document.getElementById('forgotten-password-input').value;
    const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
    await fetch(`http://localhost:8089/user/change/password?email=${email}`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(password)
    });
    localStorage.removeItem('pswEmail');
    const forgottenEmailDiv = document.getElementById('forgotten-email-div');
    if (forgottenEmailDiv.style.display === 'none') {
        forgottenEmailDiv.style.display = 'block';
    }
    const recoveryCodeDiv = document.getElementById('recovery-code-div');
    if (recoveryCodeDiv.style.display === 'block') {
        recoveryCodeDiv.style.display = 'none';
    }
    const changePswDiv = document.getElementById('change-password-div');
    if (changePswDiv.style.display === 'block') {
        changePswDiv.style.display = 'none';
    }
}

function generateRandomCode(length) {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;

    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * charactersLength);
        result += characters.charAt(randomIndex);
    }

    return result;
}