<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="../main.css">
    <link rel="stylesheet" type="text/css" href="../orders.css">
    <#import "/spring.ftl" as sp>
    <title>forgotten</title>
</head>
<body>
<div class="container">
    <div id="main">
        <div id="left-column">
            <form id="list-form">
                <label for="list1"></label>
                <div id="dropdown1">
                    <button id="drop-btn1" class="btn-dropdown"><@sp.message "cpu" /></button>
                    <div id="list1">
                        <a href="#">Intel</a>
                        <a href="#">AMD</a>
                    </div>
                </div>
                <br>
                <label for="list2"></label>
                <div id="dropdown2">
                    <button id="drop-btn2" class="btn-dropdown"><@sp.message "motherboard" /></button>
                    <div id="list2">
                        <a href="#">Gigabyte</a>
                        <a href="#">Asus</a>
                        <a href="#">Intel</a>
                        <a href="#">MSI</a>
                        <a href="#">ASRock</a>
                    </div>
                </div>
                <br>
                <label for="list3"></label>
                <div id="dropdown3">
                    <button id="drop-btn3" class="btn-dropdown"><@sp.message "cpuCooler" /></button>
                    <div id="list3">
                        <a href="#">DEEPCOOL</a>
                        <a href="#">Asus</a>
                        <a href="#">Arctic</a>
                    </div>
                </div>
                <br>
                <label for="list4"></label>
                <div id="dropdown4">
                    <button id="drop-btn4" class="btn-dropdown"><@sp.message "ram" /></button>
                    <div id="list4">
                        <a href="#">Kingston</a>
                        <a href="#">Gigabyte</a>
                        <a href="#">AMD</a>
                        <a href="#">HyperX</a>
                    </div>
                </div>
                <br>
                <label for="list5"></label>
                <div id="dropdown5">
                    <button id="drop-btn5" class="btn-dropdown"><@sp.message "videoCard" /></button>
                    <div id="list5">
                        <a href="#">Asus</a>
                        <a href="#">Gigabyte</a>
                        <a href="#">MSI</a>
                        <a href="#">HP</a>
                    </div>
                </div>
                <br>
                <label for="list6"></label>
                <div id="dropdown6">
                    <button id="drop-btn6" class="btn-dropdown"><@sp.message "hdd" /></button>
                    <div id="list6">
                        <a href="#">Toshiba</a>
                        <a href="#">WD</a>
                        <a href="#">Seagate</a>
                        <a href="#">Samsung</a>
                    </div>
                </div>
                <br>
                <label for="list7"></label>
                <div id="dropdown7">
                    <button id="drop-btn7" class="btn-dropdown"><@sp.message "powerUnit" /></button>
                    <div id="list7">
                        <a href="#">Vinga</a>
                        <a href="#">AeroCool</a>
                        <a href="#">Deepcool</a>
                        <a href="#">Zalman</a>
                    </div>
                </div>
                <br>
                <label for="list8"></label>
                <div id="dropdown8">
                    <button id="drop-btn8" class="btn-dropdown"><@sp.message "frame" /></button>
                    <div id="list8">
                        <a href="#">Zalman</a>
                        <a href="#">Corsair</a>
                        <a href="#">Cooler Master</a>
                        <a href="#">Chieftec</a>
                    </div>
                </div>
            </form>
        </div>
        <div id="content">
            <div id="forgotten">
                <form id="forgotten-form">
                    <div id="forgotten-email-div" style="display: block">
                        <label for="forgotten-email-input"><@sp.message "labelEmailChange" /></label>
                        <input type="email" name="email" minlength="1" id="forgotten-email-input"
                               placeholder="<@sp.message "email" />">
                        <button type="button" id="send-forgotten-email"><@sp.message "send" /></button>
                    </div>
                    <div id="recovery-code-div" style="display: none">
                        <p><@sp.message "codeToEmail" /></p>
                        <p id="recovery-code"></p>
                        <input type="text" name="code" minlength="1" id="forgotten-code-input"
                               placeholder="<@sp.message "code" />">
                        <button type="button" id="send-forgotten-code"><@sp.message "confirm" /></button>
                    </div>
                    <#if _csrf??>
                        <meta name="_csrf_header" content="${_csrf.headerName}"/>
                        <meta name="_csrf_token" content="${_csrf.token}"/>
                    </#if>
                    <div id="change-password-div" style="display: none">
                        <p><@sp.message "inputPassTo" /></p>
                        <p id="current-email"></p>
                        <label for="forgotten-password-input"><@sp.message "minimumLength" /></label>
                        <input type="password" name="password" minlength="8" maxlength="25"
                               id="forgotten-password-input"
                               placeholder="<@sp.message "password" />">
                        <button type="submit" id="change-password"><@sp.message "change" /></button>
                    </div>
                </form>
            </div>
        </div>
        <div id="right-column">
            <div>
                <a href="/">
                    <button type="button" class="link-button"><@sp.message "continueBuy" /></button>
                </a>
            </div>
        </div>
    </div>
</div>
<script src="../change.js"></script>
</body>
</html>