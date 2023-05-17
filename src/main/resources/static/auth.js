'use strict'

const registerForm = document.getElementById('register-post-form');
if (registerForm != null) {
    registerForm.addEventListener('submit', async (event) => {
        event.preventDefault();
        let formData = new FormData(event.target);
        let loginFormData = new FormData();
        loginFormData.append('password', document.getElementById('register-password').value);
        const email = document.getElementById('register-email').value;
        loginFormData.append('email', email);
        await fetch('http://localhost:8089/register', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData))
        });
        window.location.href = 'http://localhost:8089/login';
    })
}