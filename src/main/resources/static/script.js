'use strict'

async function getProducts(e) {
    e.preventDefault();
    await fetch('http://localhost:8089/products/').then(response => response.json().then(data => {
        console.log(data);
    }));
}