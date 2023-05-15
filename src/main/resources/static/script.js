'use strict'

const basicUrl = 'http://localhost:8089/';

const shopCopy = document.getElementById('search-form');
const page = document.getElementById('search-form-page');

if (shopCopy) {
    shopCopy.addEventListener("submit", search);
}
if (page) {
    page.addEventListener('submit', searchPage);
}

async function searchPage(e) {
    e.preventDefault()
    let formData = new FormData(e.target);
    let name = formData.get('s');
    let page = formData.get('h');
    const switchStart = document.querySelector('.switch-start');
    const switchStartBrand = document.querySelector('.switch-start-brand');
    if (switchStart) {
        switchStartBrand.style.display = 'none';
        switchStart.style.display = 'flex';
    }
    await fetch(basicUrl + `product/search?name=${name}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            const element = document.getElementById('content-products');
            while (element.firstChild) {
                element.removeChild(element.firstChild);
            }
            const urlParams = new URLSearchParams({
                s: formData.get('s'),
                h: formData.get('h')
            });
            const newUrl = window.location.pathname + '?' + urlParams.toString();
            window.history.pushState({}, '', newUrl);
            createProducts(data);
        })
        .catch(error => console.error(error));
}

async function search(e) {
    e.preventDefault();
    let formData = new FormData(e.target);
    let name = formData.get('s');
    let page = formData.get('h');
    await fetch(basicUrl + `product/search?name=${name}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            const productAsJson = JSON.stringify(data);
            localStorage.setItem('data-script', productAsJson);
            const urlParams = new URLSearchParams({
                s: formData.get('s'),
                h: formData.get('h')
            });
            localStorage.setItem('data-url', urlParams.toString());
            window.location.href = basicUrl + 'products';
        })
        .catch(error => console.error(error));
}

const next = document.querySelector('.next');
if (next) {
    next.addEventListener('click', nextPage);
}

async function nextPage(e) {
    e.preventDefault();
    const urlParam = new URLSearchParams(window.location.search);
    const name = urlParam.get('s');
    let page = parseInt(urlParam.get('h')) || 0;
    page = page + 1;
    urlParam.set('h', page.toString());
    const newUrl = window.location.pathname + '?' + urlParam.toString();
    window.history.replaceState({}, '', newUrl)
    await fetch(basicUrl + `product/search?name=${name}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            const element = document.getElementById('content-products');
            while (element.firstChild) {
                element.removeChild(element.firstChild);
            }
            createProducts(data);
        })
}

const prev = document.querySelector('.prev');
if (prev) {
    prev.addEventListener('click', prevPage);
}

async function prevPage(e) {
    e.preventDefault();
    const urlParam = new URLSearchParams(window.location.search);
    const name = urlParam.get('s');
    let page = parseInt(urlParam.get('h')) || 0;
    page = Math.max(0, page - 1);
    urlParam.set('h', page.toString());
    const newUrl = window.location.pathname + '?' + urlParam.toString();
    window.history.replaceState({}, '', newUrl)
    await fetch(basicUrl + `product/search?name=${name}&page=${page}`)
        .then(response => response.json())
        .then(data => {
            const element = document.getElementById('content-products');
            while (element.firstChild) {
                element.removeChild(element.firstChild);
            }
            createProducts(data);
        })
}

if (window.location.href.indexOf(basicUrl + 'products') !== -1) {
    const productAsJson = localStorage.getItem('data-script');
    const url = localStorage.getItem('data-url');
    let newUrl;
    if (url) {
        newUrl = window.location.pathname + '?' + url.toString();
    }
    const switchStart = document.querySelector('.switch-start');
    if (switchStart) {
        switchStart.style.display = 'flex';
    }
    if (newUrl) {
        window.history.pushState({}, '', newUrl);
    }
    localStorage.removeItem('data-url');
    const data = JSON.parse(productAsJson);
    if (data) {
        createProducts(data)
    }
}

function createProducts(data) {
    if (data.length !== 4) {
        const div = document.querySelector('.next');
        if (div) {
            div.style.display = 'none';
        } else {
            const newDiv = document.getElementById('next');
            newDiv.style.display = 'none';
        }
    } else {
        const div = document.querySelector('.next');
        if (div) {
            div.style.display = 'flex';
        } else {
            const newDiv = document.getElementById('next');
            newDiv.style.display = 'flex';
        }
    }
    for (const p of data) {
        const form = new FormData();
        form.append('name', p.name);
        form.append('description', p.description);
        form.append('count', p.count);
        form.append('price', p.price);
        form.append('brand', p.brand);
        form.append('category', p.category);
        form.append('image', p.image);
        insertProductsElem(form);
    }
}

function insertProductsElem(form) {
    const divElem = document.createElement('div');
    divElem.classList.add('products');

    const image = document.createElement('img');
    image.classList.add('products-images')
    image.src = '../images/' + form.get('image');
    image.alt = form.get('image');
    divElem.appendChild(image);

    const div = document.createElement('div');
    div.classList.add('products-div');
    const name = document.createElement('h3');
    name.textContent = "name: " + form.get('name');
    div.appendChild(name);

    const description = document.createElement('p');
    description.textContent = "description: " + form.get('description')
    div.appendChild(description);

    const count = document.createElement('p');
    count.textContent = "count: " + form.get('count');
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

    const element = document.getElementById('content-products');

    element.appendChild(divElem);
    element.appendChild(div);
    localStorage.removeItem('data-script')
}

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

const dropdown = document.querySelector('.dropdown');
const dropdownContent = dropdown.querySelector('.dropdown-content');

dropdown.querySelector('.dropbtn').addEventListener('click', function () {
    dropdownContent.classList.toggle('show');
});

window.addEventListener('click', function (event) {
    if (!event.target.matches('.dropbtn')) {
        dropdownContent.classList.remove('show');
    }
});

const brand = document.getElementById('search-form-hidden');

if (brand) {
    brand.addEventListener("submit", brandSearch);
}

async function brandSearch(e) {
    e.preventDefault();
    let formData = new FormData(e.target);
    const switchStart = document.querySelector('.switch-start-brand');
    const switchStartHide = document.querySelector('.switch-start');
    if (switchStartHide) {
        switchStartHide.style.display = 'none';
    }
    if (switchStart.style.display === 'none') {
        switchStart.style.display = 'flex';
    }
    let page = formData.get('h');
    let name = formData.get('s');
    let description = formData.get('d');
    let min = formData.get('min');
    let max = formData.get('max');
    let url;
    console.log(name)
    console.log(description);
    if (name && description && min && max) {
        url = `product/brand?name=${name}&page=${page}&description=${description}&min=${min}&max=${max}`;
    } else if (min && max) {
        url = `product/brand?min=${min}&max=${max}`;
    } else if (name && description) {
        url = `product/brand?name=${name}&page=${page}&description=${description}`;
    } else if (name) {
        url = `product/brand?name=${name}&page=${page}`;
    } else {
        url = `product/brand?description=${description}&page=${page}`;
    }
    await fetch(basicUrl + url)
        .then(response => response.json())
        .then(data => {
            let div = document.querySelector('.card-col');
            if (div) {
                div.remove();
            }
            const element = document.getElementById('content-products');
            while (element.firstChild) {
                element.removeChild(element.firstChild);
            }
            const urlParams = new URLSearchParams({
                s: formData.get('s'),
                h: formData.get('h'),
                d: formData.get('d'),
                min: formData.get('min'),
                max: formData.get('max')
            });
            const newUrl = window.location.pathname + '?' + urlParams.toString();
            window.history.pushState({}, '', newUrl);
            createProducts(data);
        })
        .catch(error => console.error(error));
}

const nextShop = document.getElementById('next');
if (nextShop) {
    nextShop.addEventListener('click', nextShopFunc);
}

async function nextShopFunc(e) {
    e.preventDefault();
    const urlParam = new URLSearchParams(window.location.search);
    const name = urlParam.get('s');
    const description = urlParam.get('d');
    let min = urlParam.get('min');
    let max = urlParam.get('max');
    let page = parseInt(urlParam.get('h')) || 0;
    page = page + 1;
    urlParam.set('h', page.toString());
    const newUrl = window.location.pathname + '?' + urlParam.toString();
    window.history.replaceState({}, '', newUrl);
    let url;
    if (name && description && min && max) {
        url = `product/brand?name=${name}&page=${page}&description=${description}&min=${min}&max=${max}`;
    } else if (min && max) {
        url = `product/brand?min=${min}&max=${max}`;
    } else if (name && description) {
        url = `product/brand?name=${name}&page=${page}&description=${description}`;
    } else if (name) {
        url = `product/brand?name=${name}&page=${page}`;
    } else {
        url = `product/brand?description=${description}&page=${page}`;
    }
    await fetch(basicUrl + url)
        .then(response => response.json())
        .then(data => {
            const element = document.getElementById('content-products');
            while (element.firstChild) {
                element.removeChild(element.firstChild);
            }
            createProducts(data);
        })
}

const prevShop = document.getElementById('prev');
if (prevShop) {
    prevShop.addEventListener('click', prevShopFunc);
}

async function prevShopFunc(e) {
    e.preventDefault();
    const urlParam = new URLSearchParams(window.location.search);
    const name = urlParam.get('s');
    const description = urlParam.get('d');
    let min = urlParam.get('min');
    let max = urlParam.get('max');
    let page = parseInt(urlParam.get('h')) || 0;
    page = Math.max(0, page - 1);
    urlParam.set('h', page.toString());
    const newUrl = window.location.pathname + '?' + urlParam.toString();
    window.history.replaceState({}, '', newUrl)
    let urls;
    if (name && description && min && max) {
        urls = `product/brand?name=${name}&page=${page}&description=${description}&min=${min}&max=${max}`;
    } else if (min && max) {
        urls = `product/brand?min=${min}&max=${max}`;
    } else if (name && description) {
        urls = `product/brand?name=${name}&page=${page}&description=${description}`;
    } else if (name) {
        urls = `product/brand?name=${name}&page=${page}`;
    } else {
        urls = `product/brand?description=${description}&page=${page}`;
    }
    await fetch(basicUrl + urls)
        .then(response => response.json())
        .then(data => {
            const element = document.getElementById('content-products');
            while (element.firstChild) {
                element.removeChild(element.firstChild);
            }
            createProducts(data);
        })
}