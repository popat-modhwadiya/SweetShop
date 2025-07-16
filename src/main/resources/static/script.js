const apiBase = "http://localhost:8080/sweets";

// Add Sweet
async function addSweet() {
  const sweet = {
    name: document.getElementById("addName").value,
    category: document.getElementById("addCategory").value,
    price: parseFloat(document.getElementById("addPrice").value),
    quantityInStock: parseInt(document.getElementById("addQuantity").value)
  };

  const res = await fetch(apiBase, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(sweet)
  });

  if (res.ok) {
    alert("Sweet added!");
    loadSweets();
  } else {
    alert("Error adding sweet!");
  }
}

// Load All Sweets
async function loadSweets() {
  const res = await fetch(apiBase);
  const sweets = await res.json();
  renderTable(sweets);
}

// Search Sweets
function clearAllSearchInputs() {
  document.getElementById("searchName").value = "";
  document.getElementById("searchCategory").value = "";
  document.getElementById("minPrice").value = "";
  document.getElementById("maxPrice").value = "";
}

async function searchByName() {
  const name = document.getElementById("searchName").value.trim();
  if (!name) {
    alert("Please enter name.");
    return;
  }
  clearAllSearchInputs();
  document.getElementById("searchName").value = name; // keep this

  const url = `${apiBase}/search?name=${encodeURIComponent(name)}`;
  const res = await fetch(url);
  const sweets = await res.json();
  renderTable(sweets);
}

async function searchByCategory() {
  const category = document.getElementById("searchCategory").value.trim();
  if (!category) {
    alert("Please enter category.");
    return;
  }
  clearAllSearchInputs();
  document.getElementById("searchCategory").value = category;

  const url = `${apiBase}/search?category=${encodeURIComponent(category)}`;
  const res = await fetch(url);
  const sweets = await res.json();
  renderTable(sweets);
}

async function searchByPriceRange() {
  const minPrice = document.getElementById("minPrice").value.trim();
  const maxPrice = document.getElementById("maxPrice").value.trim();

  if (!minPrice || !maxPrice) {
    alert("Please enter min and max price.");
    return;
  }

  clearAllSearchInputs();
  document.getElementById("minPrice").value = minPrice;
  document.getElementById("maxPrice").value = maxPrice;

  const url = `${apiBase}/search?minPrice=${minPrice}&maxPrice=${maxPrice}`;
  const res = await fetch(url);
  const sweets = await res.json();
  renderTable(sweets);
}




// Delete Sweet
async function deleteSweet(id) {
  const res = await fetch(`${apiBase}/${id}`, { method: "DELETE" });
  if (res.ok) {
    alert("Sweet deleted");
    loadSweets();
  } else {
    alert("Sweet not found");
  }
}

// Purchase Sweet
async function purchaseSweet(id) {
  const qty = prompt("Enter quantity to purchase:");
  if (!qty) return;

  const res = await fetch(`${apiBase}/${id}/purchase`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ quantity: parseInt(qty) })
  });

  const text = await res.text();
  if (res.ok) {
    alert("Purchased sweet!");
    loadSweets();
  } else {
    alert(text);
  }
}

// Restock Sweet
async function restockSweet(id) {
  const qty = prompt("Enter quantity to restock:");
  if (!qty) return;

  const res = await fetch(`${apiBase}/${id}/restock`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ quantity: parseInt(qty) })
  });

  if (res.ok) {
    alert("Sweet restocked!");
    loadSweets();
  } else {
    alert("Sweet not found");
  }
}

// Render table rows
function renderTable(sweets) {
  const tbody = document.getElementById("sweetTable");
  tbody.innerHTML = "";
  sweets.forEach(sweet => {
    const row = `<tr>
      <td>${sweet.id}</td>
      <td>${sweet.name}</td>
      <td>${sweet.category}</td>
      <td>${sweet.price}</td>
      <td>${sweet.quantityInStock}</td>
      <td>
        <button onclick="deleteSweet(${sweet.id})">Delete</button>
        <button onclick="purchaseSweet(${sweet.id})">Purchase</button>
        <button onclick="restockSweet(${sweet.id})">Restock</button>
      </td>
    </tr>`;
    tbody.innerHTML += row;
  });
}

// Load sweets on page load
loadSweets();
