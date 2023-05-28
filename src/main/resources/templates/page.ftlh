<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../main.css">
    <#import "/spring.ftl" as sp>
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
            <div class="switch-start" style="display: flex">
                <button class="prev" type="button" style="display: flex"><@sp.message "prevPage" /></button>
                <button class="next" type="button" style="display: flex"><@sp.message "nextPage" /></button>
            </div>
            <div class="switch-start-brand" style="display: none">
                <button id="prev" type="button" style="display: flex"><@sp.message "prevPage" /></button>
                <button id="next" type="button" style="display: flex"><@sp.message "nextPage" /></button>
            </div>
            <div id="content-products">

            </div>
        </div>
        <div id="right-column">
            <div>
                <p id="link-profile-name" style="display: block"></p>
                <a href="/login" style="display: block" id="link-login">
                    <button class="link-button" type="button"><@sp.message "login" /></button>
                </a>
                <a href="/logout" style="display: block" id="logout-link">
                    <button class="link-button" type="button" id="logout-button"><@sp.message "logout" /></button>
                </a>
                <a href="/register">
                    <button type="button" class="link-button"><@sp.message "register" /></button>
                </a>
            </div>
            <div class="search-right-col">
                <form id="search-form-page">
                    <label>
                        <input name="s" placeholder="<@sp.message "search" />" type="search" id="search-input">
                        <input name="h" value="0" hidden id="search-input-hidden">
                        <#if _csrf??>
                            <meta name="_csrf_header" content="${_csrf.headerName}"/>
                            <meta name="_csrf_token" content="${_csrf.token}"/>
                        </#if>
                    </label>
                    <button type="submit" id="search-button"><@sp.message "search" /></button>
                </form>
            </div>
            <div class="dropdown">
                <button class="dropbtn"><@sp.message "advancedSearch" /></button>
                <div class="dropdown-content">
                    <form id="search-form-hidden">
                        <label>
                            <p><@sp.message "searchByBrand" /></p>
                            <input name="s" placeholder="<@sp.message "searchByBrand" />" type="search" id="search-input-hidden">
                            <input name="h" value="0" hidden id="hidden-search-input">
                            <p><@sp.message "searchByDescription" /></p>
                            <input name="d" placeholder="<@sp.message "searchByDescription" />" type="search" id="search-input-description">
                            <p><@sp.message "priceBetween" /></p>
                            <input name="min" placeholder="<@sp.message "minimumPrice" />" type="search" id="min-search-input">
                            <input name="max" placeholder="<@sp.message "maximumPrice" />" type="search" id="max-search-input">
                            <#if _csrf??>
                                <meta name="_csrf_header" content="${_csrf.headerName}"/>
                                <meta name="csrf-token" content="${_csrf.token}"/>
                            </#if>
                        </label>
                        <button type="submit" id="search-button-brand"><@sp.message "search" /></button>
                    </form>
                </div>
            </div>
            <div class="orders">
                <h3 id="orders-title"><@sp.message "yourBasket" /></h3>
                <div id="orders-list">
                    <ol id="orders-ol">
                        <li id="clear-li-text"><@sp.message "emptyBasket" /></li>
                    </ol>
                </div>
                <a href="http://localhost:8089/orders">
                    <button type="button" id="orders-button"><@sp.message "goToBasket" /></button>
                </a>
                <button type="button" id="orders-button-clear"><@sp.message "clearBasket" /></button>
            </div>
        </div>
    </div>
</div>
<script src="../script.js"></script>
</body>
</html>