<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../main.css">
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
            <div class="switch-start" style="display: flex">
                <button class="prev" type="button" style="display: flex">Предыдущая страница</button>
                <button class="next" type="button" style="display: flex">Следующая страница</button>
            </div>
            <div class="switch-start-brand" style="display: none">
                <button id="prev" type="button" style="display: flex">Предыдущая страница</button>
                <button id="next" type="button" style="display: flex">Следующая страница</button>
            </div>
            <div id="content-products">

            </div>
        </div>
        <div id="right-column">
            <div class="search-right-col">
                <form id="search-form-page">
                    <label>
                        <input name="s" placeholder="Поиск" type="search" id="search-input">
                        <input name="h" value="0" hidden id="search-input-hidden">
                    </label>
                    <button type="submit" id="search-button">Поиск</button>
                </form>
            </div>
            <div class="dropdown">
                <button class="dropbtn">Расширенный поиск</button>
                <div class="dropdown-content">
                    <form id="search-form-hidden">
                        <label>
                            <p>Поиск по бренду</p>
                            <input name="s" placeholder="Поиск по бренду" type="search" id="search-input-hidden">
                            <input name="h" value="0" hidden id="hidden-search-input">
                            <p>Поиск по описанию</p>
                            <input name="d" placeholder="Поиск по описанию" type="search" id="search-input-description">
                            <p>Диапазон цен</p>
                            <input name="min" placeholder="Минимальная цена" type="search" id="min-search-input">
                            <input name="max" placeholder="Максимальная цена" type="search" id="max-search-input">
                        </label>
                        <button type="submit" id="search-button-brand">Поиск</button>
                    </form>
                </div>
            </div>
            <div class="orders">
                <h3 id="orders-title">Ваша корзина</h3>
                <div id="orders-list">
                    <ol id="orders-ol">
                        <li id="clear-li-text">Корзина пуста</li>
                    </ol>
                </div>
                <a href="#">
                    <button type="button" id="orders-button">Перейти в корзину</button>
                </a>
                <button type="button" id="orders-button-clear">Очистить корзину</button>
            </div>
        </div>
    </div>
</div>
<script src="../script.js"></script>
</body>
</html>