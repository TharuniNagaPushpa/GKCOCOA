function addToCart(name, price) {
    const cart = JSON.parse(localStorage.getItem("gkcocoaCart") || "[]");

    const existing = cart.find(item => item.name === name);

    if (existing) {
        existing.quantity += 1;
    } else {
        cart.push({
            name: name,
            price: price,
            quantity: 1
        });
    }

    localStorage.setItem("gkcocoaCart", JSON.stringify(cart));

    window.location.href = "cart.html";
}


function loadCart() {
    const cart = JSON.parse(localStorage.getItem("gkcocoaCart") || "[]");

    const container = document.getElementById("cartItems");
    const totalElement = document.getElementById("cartTotal");

    if (!container || !totalElement) return;

    if (cart.length === 0) {
        container.innerHTML = "<p>Your cart is empty.</p>";
        totalElement.textContent = "₹0";
        return;
    }

    let total = 0;

    container.innerHTML = cart.map((item, index) => {

        const itemTotal = item.price * item.quantity;
        total += itemTotal;

        return `
            <div class="cart-item">
                <div>
                    <h3>${item.name}</h3>
                    <p>₹${item.price} × ${item.quantity}</p>
                </div>

                <div>
                    <button onclick="changeQuantity(${index}, -1)">−</button>
                    <button onclick="changeQuantity(${index}, 1)">+</button>
                    <button onclick="removeFromCart(${index})">Remove</button>
                </div>

                <strong>₹${itemTotal}</strong>
            </div>
        `;

    }).join("");

    totalElement.textContent = `₹${total}`;
}


function changeQuantity(index, amount) {

    const cart = JSON.parse(localStorage.getItem("gkcocoaCart") || "[]");

    if (!cart[index]) return;

    cart[index].quantity += amount;

    if (cart[index].quantity <= 0) {
        cart.splice(index, 1);
    }

    localStorage.setItem("gkcocoaCart", JSON.stringify(cart));

    loadCart();
}


function removeFromCart(index) {

    const cart = JSON.parse(localStorage.getItem("gkcocoaCart") || "[]");

    cart.splice(index, 1);

    localStorage.setItem("gkcocoaCart", JSON.stringify(cart));

    loadCart();
}