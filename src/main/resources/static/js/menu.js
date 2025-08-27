const foodItems = [
    { id: 1, name: "Cheese Burger", price: 5.99, image: "images/food_items/burger.jpg" },
    { id: 2, name: "Veggie Pizza", price: 7.49, image: "images/food_items/pizza.jpg" },
    { id: 3, name: "Checken biryani", price: 6.25, image: "images/food_items/checken_biryani.jpg" },
	{ id: 4, name: "Pasta", price: 5.99, image: "images/food_items/pasta.jpg" },
    { id: 5, name: "Fry Noodles", price: 7.49, image: "images/food_items/fry_noodles.jpg" },
	{ id: 6, name: "Fish Biryani", price: 6.25, image: "images/food_items/fish_biryani.jpg" },	
    { id: 7, name: "Vada", price: 5.99, image: "images/food_items/vada.jpg" },
	{ id: 8, name: "Masala Dosa", price: 7.49, image: "images/food_items/masala_dosa.jpg" },
	{ id: 9, name: "Rasam Rice", price: 6.25, image: "images/food_items/sambar_rice.jpg" }
];

const container = document.getElementById("food-container");
const selectedItemsContainer = document.getElementById("selected-items");
const totalPriceEl = document.getElementById("total-price");

let cart = [];

function renderFoodCards() {
    foodItems.forEach(item => {
        const card = document.createElement("div");
        card.className = "col-md-6 col-lg-4 mb-4";
        card.innerHTML = `
            <div class="card h-100 shadow-sm">
           
			<div class="card-body d-flex flex-column p-0">
			  <div 
			    class="p-3" 
			    style="
			      background-image: url('${item.image}');
			      background-size: cover;
			      background-position: center;
			      color: white;
			      border-radius: 10px;
			      min-height: 140px;
			    "
			  >
			    <div class="d-flex justify-content-between align-items-center mt-5">
				<div>
			    <h5 class="card-title ">${item.name}</h5>
			      <p class="card-text mb-0">$${item.price.toFixed(2)}</p>
				</div>
			      <button class="btn btn-success" onclick="addToMenu(${item.id})">Add</button>
			    </div>
			  </div>
			</div>


            </div>
        `;
        container.appendChild(card);
    });
}

function addToMenu(id) {
    const item = foodItems.find(f => f.id === id);
    const existing = cart.find(c => c.id === id);

    if (existing) {
        existing.quantity += 1;
    } else {
        cart.push({ ...item, quantity: 1 });
    }

    renderCart();
}

function renderCart() {
    selectedItemsContainer.innerHTML = "";

    let total = 0;
    cart.forEach(item => {
        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        const itemTotal = item.price * item.quantity;
        total += itemTotal;

        li.innerHTML = `
            <div>
                <strong>${item.name}</strong><br>
                <small>Qty: ${item.quantity}</small>
            </div>
            <span>$${itemTotal.toFixed(2)}</span>
        `;

        selectedItemsContainer.appendChild(li);
    });

    totalPriceEl.textContent = `$${total.toFixed(2)}`;
}

renderFoodCards();
