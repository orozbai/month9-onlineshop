'use strict';

const basic = 'http://localhost:8089/';

if (window.location.href === basic + 'orders') {
    getOrders();
}

function getOrders() {
    const listJson = sessionStorage.getItem('products');
    createCartList(listJson);
}

function createCartList(listJson) {
    const elem = document.getElementById('user-orders');
    while (elem.firstChild) {
        elem.removeChild(elem.firstChild);
    }
    const dataArray = JSON.parse(listJson);
    if (dataArray) {
        for (const p of dataArray) {
            const form = new FormData();
            form.append('name', p.name);
            form.append('description', p.description);
            form.append('count', p.count);
            form.append('price', p.price);
            form.append('brand', p.brand);
            form.append('category', p.category);
            form.append('image', p.image);
            form.append('id', p.id);
            insertCart(form);
        }
    }
}

function insertCart(form) {
    const divElem = document.createElement('div');
    divElem.classList.add('products');
    divElem.id = 'cart-' + form.get('id');

    const image = document.createElement('img');
    image.classList.add('products-images')
    image.src = '../images/' + form.get('image');
    image.alt = form.get('image');
    divElem.appendChild(image);

    const button = document.createElement('button');
    button.type = 'button';
    button.classList.add('cart-button');
    button.textContent = 'Удалить из корзины';
    button.setAttribute('onclick', 'deleteBasket("cart-' + form.get('id') + '", event)');
    divElem.appendChild(button);

    const div = document.createElement('div');
    div.classList.add('products-div');
    const name = document.createElement('h3');
    name.textContent = "name: " + form.get('name');
    div.appendChild(name);

    const description = document.createElement('p');
    description.textContent = "description: " + form.get('description')
    div.appendChild(description);

    const label = document.createElement('label');
    label.setAttribute('for', 'count-' + form.get('id'));
    label.innerHTML = 'Count:'
    const count = document.createElement('input');
    count.setAttribute('type', 'number');
    count.setAttribute('id', 'count-' + form.get('id'));
    count.setAttribute('name', 'count-' + form.get('id'));
    count.setAttribute('value', '1');
    count.setAttribute('min', '1');
    count.setAttribute('max', form.get('count'));
    count.setAttribute('oninput', 'checkValue(this,' + form.get('count') + ' )');
    div.appendChild(label);
    div.appendChild(count);

    const price = document.createElement('p')
    price.textContent = "price: " + form.get('price');
    div.appendChild(price);

    const brand = document.createElement('p')
    brand.textContent = "brand: " + form.get('brand');
    div.appendChild(brand);

    const category = document.createElement('p')
    category.textContent = "category: " + form.get('category');
    div.appendChild(category);

    const element = document.getElementById('user-orders');

    element.appendChild(divElem);
    element.appendChild(div);
}

function deleteBasket(id, event) {
    event.preventDefault();
    const listJson = sessionStorage.getItem('products');
    let data = JSON.parse(listJson);
    const digits = id.match(/\d/g);
    const newId = digits.join('');
    const num = parseInt(newId);
    data.forEach((obj, index) => {
        if (obj.id === num) {
            data.splice(index, 1);
        }
    });
    const newJsonList = JSON.stringify(data);
    sessionStorage.setItem('products', newJsonList);
    createCartList(newJsonList);
}

const cartDetails = document.getElementById('cart-details');
if (cartDetails) {
    cartDetails.addEventListener('click', showModal);
}

function showModal() {
    const deleteElements = document.getElementById('modal-content-text');
    while (deleteElements.firstChild) {
        deleteElements.removeChild(deleteElements.firstChild);
    }
    const pTotal = document.createElement('p');
    pTotal.textContent = 'Итоговая сумма';
    deleteElements.appendChild(pTotal);

    const pPrice = document.createElement('p');
    pPrice.id = 'total-price';
    pPrice.innerHTML = '0';
    deleteElements.appendChild(pPrice);

    const modal = document.getElementById('myModal');
    const closeBtn = document.getElementsByClassName("close")[0];

    if (modal.style.display === 'none') {
        modal.style.display = 'block';
    }
    closeBtn.onclick = function () {
        modal.style.display = 'none';
    }

    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    }

    const jsonArray = sessionStorage.getItem('products');
    if (jsonArray) {
        const objectArray = JSON.parse(jsonArray);

        for (const p of objectArray) {
            const form = new FormData();
            form.append('name', p.name);
            form.append('count', p.count);
            form.append('price', p.price);
            form.append('id', 'count-' + p.id);
            insertModal(form);
        }
    }
}

function insertModal(form) {
    const div = document.createElement('div');
    const p = document.createElement('p');
    p.textContent = 'name: ' + form.get('name');
    div.appendChild(p);

    const price = document.createElement('p');
    price.textContent = 'price: ' + form.get('price');
    div.appendChild(price);

    const inputCount = document.getElementById(form.get('id')).value;
    const count = document.createElement('p');
    count.textContent = 'count: ' + inputCount;
    div.appendChild(count);

    const totalPrice = document.getElementById('total-price');
    const number = parseInt(totalPrice.textContent);
    const intPrice = parseInt(form.get('price'));
    const intCount = parseInt(inputCount);
    const priceNumber = number + (intPrice * intCount);
    totalPrice.innerHTML = priceNumber;
    const modalWindowInput = document.getElementById('modal-window-hidden-input');
    localStorage.setItem('totalPrice', priceNumber + '');
    modalWindowInput.textContent = priceNumber;

    const divElem = document.getElementById('modal-content-text');
    divElem.appendChild(div);
}

function checkValue(input, num) {
    if (input.value > num && input.value > 0) {
        input.value = '';
    }
}

const deliverButton = document.getElementById('deliver');
if (deliverButton) {
    deliverButton.addEventListener('click', sendDeliver);
}

async function sendDeliver(e) {
    e.preventDefault()
    const address = document.getElementById('modal-address').value;
    const email = document.getElementById('modal-email').value;
    const products = sessionStorage.getItem('products');
    const number = localStorage.getItem('totalPrice');
    localStorage.removeItem('totalPrice');
    const randomString = generateRandomString(10);
    const uni = document.getElementById('uni');
    uni.textContent = 'Ваш идентификатор заказа: ' + randomString;
    const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
    await fetch(basic + `user/delivery?totalPrice=${number}&address=${address}&email=${email}&uni=${randomString}`, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(products)
    });
    const modal = document.getElementById('myModal');
    if (modal.style.display === 'block') {
        modal.style.display = 'none';
    }
    sessionStorage.removeItem('products');
}

function generateRandomString(length) {
    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;

    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * charactersLength);
        result += characters.charAt(randomIndex);
    }

    return result;
}

const findButtonIdenty = document.getElementById('cart-button-find');
if (findButtonIdenty) {
    findButtonIdenty.addEventListener('click', async function (e) {
        e.preventDefault()
        const uni = document.getElementById('input-identify').value;
        await fetch(basic + `user/cart/find?uni=${uni}`).then(response => response.json())
            .then(data => {
                createCartList(data);
            });
    })
}