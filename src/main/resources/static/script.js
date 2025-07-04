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
    const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
    await fetch(basicUrl + `product/search?name=${name}&page=${page}`, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
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
    const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
    await fetch(basicUrl + `product/search?name=${name}&page=${page}`, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
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
    const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
    await fetch(basicUrl + `product/search?name=${name}&page=${page}`, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
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
    const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
    await fetch(basicUrl + `product/search?name=${name}&page=${page}`, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
        .then(response => response.json())
        .then(data => {
            const element = document.getElementById('content-products');
            while (element.firstChild) {
                element.removeChild(element.firstChild);
            }
            createProducts(data);
        })
}

window.addEventListener('load', async function (e) {
    e.preventDefault();
    await fetch(basicUrl + 'user/current-user')
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка получения данных');
            }
            return response.json();
        })
        .then(data => {
            const p = document.getElementById('link-profile-name');
            const elem = document.getElementById('link-login');
            const button = document.getElementById('logout-link');
            if (elem) {
                if (!data.error) {
                    p.style.display = 'block';
                    p.innerText = data.user;
                    button.style.display = 'block';
                    elem.style.display = 'none';
                } else {
                    elem.style.display = 'block';
                    p.style.display = 'none';
                    button.style.display = 'none';
                }
            }
        });
})

const logoutButtonIf = document.getElementById('logout-button');
if (logoutButtonIf) {
    document.getElementById('logout-button').addEventListener('click', async function (e) {
        e.preventDefault();
        const csrfToken = document.querySelector('meta[name="_csrf_token"]').getAttribute('content');
        await fetch(basicUrl + 'logout', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            }
        })
        window.location.href = basicUrl + 'login?logout';
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
        form.append('id', p.id);
        insertProductsElem(form);
    }
}

function insertProductsElem(form) {
    const divElem = document.createElement('div');
    divElem.classList.add('products');
    divElem.id = 'product-' + form.get('id');

    const image = document.createElement('img');
    image.classList.add('products-images')
    image.src = '../images/' + form.get('image');
    image.alt = form.get('image');
    divElem.appendChild(image);

    const button = document.createElement('button');
    button.type = 'button';
    button.classList.add('cart-button');
    button.textContent = 'Добавить в корзину';
    button.setAttribute('onclick', 'addBasket("product-' + form.get('id') + '", event)');
    divElem.appendChild(button);

    const reviews = document.createElement('button');
    reviews.textContent = 'Показать комментарии';
    reviews.classList.add('product-reviews');
    reviews.id = 'reviews-' + form.get('id');
    reviews.setAttribute('onclick', 'showReviews("reviews-' + form.get('id') + '", event)');
    divElem.appendChild(reviews);

    const divComments = document.createElement('div');
    divComments.id = 'comments-' + form.get('id');
    divComments.style.overflow = 'auto';
    divComments.style.marginTop = '5px';
    divComments.style.height = '200px';
    divComments.style.display = 'none';
    divElem.appendChild(divComments);

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

const ordersButtonClear = document.getElementById('orders-button-clear');
if (ordersButtonClear) {
    document.getElementById('orders-button-clear').addEventListener('click', function (e) {
        e.preventDefault();
        let list = document.getElementById('orders-ol');
        while (list.firstChild) {
            list.removeChild(list.firstChild);
        }
        let emptyListItem = document.createElement('li');
        emptyListItem.id = 'clear-li-text';
        emptyListItem.textContent = 'Корзина пуста';
        sessionStorage.removeItem('products');
        list.appendChild(emptyListItem);
    });
}

const dropdown = document.querySelector('.dropdown');
if (dropdown) {
    const dropdownContent = dropdown.querySelector('.dropdown-content');

    dropdown.querySelector('.dropbtn').addEventListener('click', function () {
        dropdownContent.classList.toggle('show');
    });

    window.addEventListener('click', function (event) {
        if (!event.target.matches('.dropbtn')) {
            dropdownContent.classList.remove('show');
        }
    });
}

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
    if (name && description && min && max) {
        url = `product/brand?name=${name}&page=${page}&description=${description}&min=${min}&max=${max}`;
    } else if (min && max) {
        url = `product/brand?min=${min}&max=${max}&page=${page}`;
    } else if (name && description) {
        url = `product/brand?name=${name}&page=${page}&description=${description}`;
    } else if (name) {
        url = `product/brand?name=${name}&page=${page}`;
    } else {
        url = `product/brand?description=${description}&page=${page}`;
    }
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    await fetch(basicUrl + url, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
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
        url = `product/brand?min=${min}&max=${max}&page=${page}`;
    } else if (name && description) {
        url = `product/brand?name=${name}&page=${page}&description=${description}`;
    } else if (name) {
        url = `product/brand?name=${name}&page=${page}`;
    } else {
        url = `product/brand?description=${description}&page=${page}`;
    }
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    await fetch(basicUrl + url, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
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
        urls = `product/brand?min=${min}&max=${max}&page=${page}`;
    } else if (name && description) {
        urls = `product/brand?name=${name}&page=${page}&description=${description}`;
    } else if (name) {
        urls = `product/brand?name=${name}&page=${page}`;
    } else {
        urls = `product/brand?description=${description}&page=${page}`;
    }
    const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');
    await fetch(basicUrl + urls, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        }
    })
        .then(response => response.json())
        .then(data => {
            const element = document.getElementById('content-products');
            while (element.firstChild) {
                element.removeChild(element.firstChild);
            }
            createProducts(data);
        })
}

async function addBasket(id, e) {
    e.preventDefault();
    const clear = document.getElementById('clear-li-text');
    if (clear) {
        clear.remove();
    }
    const digits = id.match(/\d/g);
    const newId = digits.join('');
    const olElem = document.getElementById('orders-ol');
    await fetch(basicUrl + `product/id?id=${newId}`)
        .then(response => response.json())
        .then(data => {
            const p = data;
            const form = new FormData();
            form.append('name', p.name);
            form.append('description', p.description);
            form.append('count', '1');
            form.append('price', p.price);
            form.append('brand', p.brand);
            form.append('category', p.category);
            form.append('image', p.image);
            form.append('id', p.id);
            const existingData = sessionStorage.getItem('products');
            let dataArray = [];
            if (existingData) {
                dataArray = JSON.parse(existingData);
            }
            const objForm = {
                brand: form.get('brand'),
                category: form.get('category'),
                count: parseInt(form.get('count')),
                description: form.get('description'),
                id: parseInt(form.get('id')),
                image: form.get('image'),
                name: form.get('name'),
                price: parseInt(form.get('price'))
            };
            const isObjectExists = dataArray.some(obj => JSON.stringify(obj) === JSON.stringify(objForm))
            if (!isObjectExists) {
                dataArray.push(objForm);
            }
            const updatedData = JSON.stringify(dataArray);
            sessionStorage.setItem('products', updatedData);
            let getLiId = document.getElementById(form.get('id') + '-product');
            if (!getLiId) {
                const li = document.createElement('li');
                li.textContent = form.get('name');
                li.id = form.get('id') + '-product';
                olElem.appendChild(li);
            }
        });
}

async function showReviews(id, event) {
    event.preventDefault();
    const digits = id.match(/\d/g);
    const newId = digits.join('');
    await fetch(basicUrl + `product/comment?productId=${newId}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Ошибка пустой комментарий');
            }
        })
        .then(data => {
            const div = document.getElementById('comments-' + newId);
            if (div.style.display === 'none') {
                div.style.display = 'block';
            } else {
                div.style.display = 'none';
            }
            while (div.firstChild) {
                div.removeChild(div.firstChild);
            }
            createComments(data, newId);
        })
        .catch(error => {
            console.log(error);
        });
}

function createComments(data, id) {
    for (const c of data) {
        const form = new FormData();
        form.append('name', c.name);
        form.append('description', c.description);
        form.append('descriptionTime', c.descriptionTime);
        insertComments(form, id);
    }
}

function insertComments(form, id) {
    const div = document.getElementById('comments-' + id);
    const p = document.createElement('p');
    p.textContent = form.get('descriptionTime') + ' name ' + form.get('name') + ': ' + form.get('description');
    div.appendChild(p);
}

function changeLanguage(lang) {
    let url = window.location.href;
    if (url.indexOf('?') !== -1) {
        if (url.indexOf('lang=') !== -1) {
            url = url.replace(/lang=[^&]+/, lang);
        } else {
            url += '&' + lang;
        }
    } else {
        url += '?' + lang;
    }
    window.history.replaceState({}, '', url);
    window.location.reload();
}