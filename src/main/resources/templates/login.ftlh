<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
    <#import "/spring.ftl" as sp>
    <title>Login</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <form id="login-form" method="post">
                <div class="mb-3">
                    <#if error>
                        <div class="alert alert-danger" role="alert">
                            <@sp.message "errorEmailAndPassword" />
                        </div>
                    </#if>
                    <#if logout>
                        <div class="alert alert-success" role="alert">
                        <@sp.message "logoutSuccessfully" />
                        </div></#if>
                    <label for="exampleInputEmail1" class="form-label"><@sp.message "emailAddress" /></label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                           name="username">
                    <div id="emailHelp" class="form-text"><@sp.message "neverShareEmail" /></div>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label"><@sp.message "password" /></label>
                    <input type="password" class="form-control" id="exampleInputPassword1" name="password"
                           minlength="8">
                </div>
                <#if _csrf??>
                    <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                </#if>
                <button type="submit" class="btn btn-primary"><@sp.message "login" /></button>
            </form>
            <a href="/forgotten">
                <button type="button"><@sp.message "forgotPassword" /></button>
            </a>
        </div>
    </div>
</div>
</body>
</html>