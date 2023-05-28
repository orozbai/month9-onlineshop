<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../register.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
    <#import "/spring.ftl" as sp>
    <title>Register</title>
</head>
<body>
<div class="container">
    <div class="register">
        <form id="register-post-form" enctype="multipart/form-data" method="post" action="/login">
            <label for="register-accountName"></label>
            <input type="text" id="register-accountName" name="username" required placeholder="<@sp.message "username" />" maxlength="25">
            <br>
            <label for="register-name"></label>
            <input type="text" id="register-name" name="name" required placeholder="<@sp.message "codeToEmail" />" maxlength="25">
            <br>
            <label for="register-email"></label>
            <input type="email" id="register-email" name="email" required placeholder="<@sp.message "email" />" maxlength="25">
            <div id="emailError" class="error"></div>
            <br>
            <label for="register-password"></label>
            <input type="password" id="register-password" name="password" required placeholder="<@sp.message "password" />"
                   maxlength="25" minlength="8">
            <br>
            <label for="repeatPassword"></label>
            <input type="password" id="repeatPassword" name="repeatPassword" required placeholder="<@sp.message "repeatPassword" />"
                   maxlength="25">
            <p>CAPTCHA</p>
            <label>
                <input type="number" maxlength="2" minlength="1" placeholder="" id="captcha-register" name="captchaReg">
            </label>
            <div id="passwordError" class="error"></div>
            <br>
            <a href="/login"><input type="submit" value="register"></a>
            <a href="/login" class="login-button-link">
                <@sp.message "alreadyRegistered" />
            </a>
            <#if _csrf??>
                <meta name="_csrf_header" content="${_csrf.headerName}"/>
                <meta name="_csrf_token" content="${_csrf.token}"/>
            </#if>
        </form>
        <script src="../auth.js"></script>
        <script>

            const emailInput = document.getElementById("register-email");
            const passwordInput = document.getElementById("register-password");
            const repeatPasswordInput = document.getElementById("repeatPassword");

            const emailError = document.getElementById("emailError");
            const passwordError = document.getElementById("passwordError");

            function validateEmail() {
                const email = emailInput.value;
                const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailRegex.test(email)) {
                    emailError.innerHTML = "Enter correct email";
                    emailInput.classList.add("error");
                    return false;
                } else {
                    emailError.innerHTML = "";
                    emailInput.classList.remove("error");
                    return true;
                }
            }

            function validatePassword() {
                const password = passwordInput.value;
                const repeatPassword = repeatPasswordInput.value;
                if (password !== repeatPassword) {
                    passwordError.innerHTML = "Password mismatch";
                    passwordInput.classList.add("error");
                    repeatPasswordInput.classList.add("error");
                    return false;
                } else {
                    passwordError.innerHTML = "";
                    passwordInput.classList.remove("error");
                    repeatPasswordInput.classList.remove("error");
                    return true;
                }
            }

            emailInput.addEventListener("input", validateEmail);
            passwordInput.addEventListener("input", validatePassword);
            repeatPasswordInput.addEventListener("input", validatePassword);

        </script>
    </div>
</div>
</body>
</html>