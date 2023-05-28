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
    <title>Orders</title>
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
            <form id="basket-form">
                <p id="uni"></p>
                <button type="button" id="cart-details"><@sp.message "checkout" /></button>
                <div id="user-orders">

                </div>
            </form>
            <div id="myModal" class="modal" style="display: none">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="modal-content-text">
                        <p><@sp.message "totalSum" /></p>
                        <p id="total-price">0</p>
                    </div>
                    <form id="modal-window-form">
                        <p><@sp.message "addressDelivery" /></p>
                        <#if _csrf??>
                            <meta name="_csrf_header" content="${_csrf.headerName}"/>
                            <meta name="_csrf_token" content="${_csrf.token}"/>
                        </#if>
                        <label>
                            <input type="hidden" value="" id="modal-window-hidden-input" name="totalPrice">
                            <input type="text" id="modal-address" name="address" placeholder="<@sp.message "addressDelivery" />"
                                   minlength="1">
                            <input type="email" id="modal-email" name="email" placeholder="<@sp.message "email" />" maxlength="30"
                                   minlength="1">
                        </label>
                        <button type="submit" id="deliver"><@sp.message "order" /></button>
                    </form>
                </div>
            </div>
            <div id="reviews-modal" class="modal" style="display: none">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <p><@sp.message "review" /></p>
                    <form id="reviews-window-form">
                        <#if _csrf??>
                            <meta name="reviews_csrf_token" content="${_csrf.token}"/>
                        </#if>
                        <label for="reviews-text"></label>
                        <input type="text" minlength="5" name="reviews" id="reviews-text">
                        <button type="submit" id="send-reviews"><@sp.message "send" /></button>
                    </form>
                </div>
            </div>
        </div>
        <div id="right-column">
            <div>
                <a href="/">
                    <button type="button" class="link-button"><@sp.message "continueBuy" /></button>
                </a>
            </div>
            <div id="cart-identify">
                <form id="find-user-cart">
                    <p><@sp.message "haveIdentification" /></p>
                    <label>
                        <input type="text" maxlength="10" name="<@sp.message "identificator" />" id="input-identify" minlength="1">
                    </label>
                    <button id="cart-button-find" type="submit"><@sp.message "search" /></button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="../orders.js"></script>
<script src="../script.js"></script>
</body>
</html>