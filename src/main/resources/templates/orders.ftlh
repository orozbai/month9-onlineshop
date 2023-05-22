<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="../main.css">
    <link rel="stylesheet" type="text/css" href="../orders.css">
    <title>Orders</title>
</head>
<body>
<div class="container">
    <div id="main">
        <div id="left-column">
            <form id="list-form">
                <label for="list1"></label>
                <div id="dropdown1">
                    <button id="drop-btn1" class="btn-dropdown">Процессоры</button>
                    <div id="list1">
                        <a href="#">Intel</a>
                        <a href="#">AMD</a>
                    </div>
                </div>
                <br>
                <label for="list2"></label>
                <div id="dropdown2">
                    <button id="drop-btn2" class="btn-dropdown">Материнские платы</button>
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
                    <button id="drop-btn3" class="btn-dropdown">Системы охлождения</button>
                    <div id="list3">
                        <a href="#">DEEPCOOL</a>
                        <a href="#">Asus</a>
                        <a href="#">Arctic</a>
                    </div>
                </div>
                <br>
                <label for="list4"></label>
                <div id="dropdown4">
                    <button id="drop-btn4" class="btn-dropdown">Оперативная память</button>
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
                    <button id="drop-btn5" class="btn-dropdown">Видеокарты</button>
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
                    <button id="drop-btn6" class="btn-dropdown">Жесткие диски</button>
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
                    <button id="drop-btn7" class="btn-dropdown">Блоки питания</button>
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
                    <button id="drop-btn8" class="btn-dropdown">Корпус</button>
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
                <button type="button" id="cart-details">Оформить заказ</button>
                <div id="user-orders">

                </div>
            </form>
            <div id="myModal" class="modal" style="display: none">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <div id="modal-content-text">
                        <p>Итоговая сумма</p>
                        <p id="total-price">0</p>
                    </div>
                    <form id="modal-window-form">
                        <p>Адрес доставки</p>
                        <#if _csrf??>
                            <meta name="_csrf_header" content="${_csrf.headerName}"/>
                            <meta name="_csrf_token" content="${_csrf.token}"/>
                        </#if>
                        <label>
                            <input type="hidden" value="" id="modal-window-hidden-input" name="totalPrice">
                            <input type="text" id="modal-address" name="address" placeholder="Адресс доставки"
                                   minlength="1">
                            <input type="email" id="modal-email" name="email" placeholder="Email" maxlength="30"
                                   minlength="1">
                        </label>
                        <button type="submit" id="deliver">Заказать</button>
                    </form>
                </div>
            </div>

        </div>
        <div id="right-column">
            <div>
                <a href="/">
                    <button type="button" class="link-button">Продолжить покупки</button>
                </a>
            </div>
            <div id="cart-identify">
                <form id="find-user-cart">
                    <p>Есть идентификатор?</p>
                    <label>
                        <input type="text" maxlength="10" name="identification" id="input-identify" minlength="1">
                    </label>
                    <button id="cart-button-find" type="submit">Найти</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="../orders.js"></script>
<script src="../script.js"></script>
</body>
</html>