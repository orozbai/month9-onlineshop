'use strict'

async function getProducts(e) {
    e.preventDefault();
    await fetch('http://localhost:8089/products/').then(response => response.json().then(data => {
        console.log(data);
    }));
}

document.getElementById('search-form').addEventListener('submit', function (e) {
    e.preventDefault();
    let formData = new FormData(e.target);
    const value = formData.get('s');
    console.log(value);
});

document.getElementById('orders-button-clear').addEventListener('click', function (e) {
    e.preventDefault();
    let list = document.getElementById('orders-ol');
    while (list.firstChild) {
        list.removeChild(list.firstChild);
    }
    let emptyListItem = document.createElement('li');
    emptyListItem.id = 'clear-li-text';
    emptyListItem.textContent = 'Корзина пуста';
    list.appendChild(emptyListItem);
});