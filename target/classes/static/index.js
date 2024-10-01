// Función para obtener todas las entregas y mostrar en la tabla
function fetchDeliveries() {
    fetch("/api/deliveries")
        .then(response => response.json())
        .then(data => {
            const deliveryTableBody = document.getElementById("deliveryTableBody");
            deliveryTableBody.innerHTML = ""; // Limpiar la tabla existente

            data.forEach(delivery => {
                const row = document.createElement("tr");

                // Crear columnas para cada atributo del delivery
                const idCell = document.createElement("td");
                idCell.textContent = delivery.id;
                row.appendChild(idCell);

                const addressCell = document.createElement("td");
                addressCell.textContent = delivery.address;
                row.appendChild(addressCell);

                const priceCell = document.createElement("td");
                priceCell.textContent = delivery.price;
                row.appendChild(priceCell);

                const sizeCell = document.createElement("td");
                sizeCell.textContent = delivery.size;
                row.appendChild(sizeCell);

                const descriptionCell = document.createElement("td");
                descriptionCell.textContent = delivery.description;
                row.appendChild(descriptionCell);

                // Añadir la fila a la tabla
                deliveryTableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching deliveries:", error));
}

// Llamar la función fetchDeliveries() cuando la página se cargue
window.onload = function() {
    fetchDeliveries();
};

// Función para agregar o actualizar una entrega
document.getElementById("deliveryForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Evitar refrescar la página

    const delivery = {
        address: document.getElementById("address").value,
        price: document.getElementById("price").value,
        size: document.getElementById("size").value,
        description: document.getElementById("description").value
    };

    const id = document.getElementById("id").value;

    if (id) {
        // Si el campo ID está lleno, se actualiza la entrega
        fetch(`/api/deliveries/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(delivery)
        })
        .then(response => {
            if (response.ok) {
                alert(`Delivery with ID: ${id} updated successfully!`);
                document.getElementById("deliveryForm").reset();
                fetchDeliveries(); // Actualizar la tabla de entregas
            } else {
                alert(`Failed to update delivery with ID: ${id}`);
            }
        })
        .catch(error => console.error("Error updating delivery:", error));
    } else {
        // Si el campo ID está vacío, se agrega una nueva entrega
        fetch("/api/deliveries", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(delivery)
        })
        .then(response => response.json())
        .then(data => {
            alert(`Delivery added with ID: ${data.id}`);
            document.getElementById("deliveryForm").reset(); // Limpiar formulario
            fetchDeliveries(); // Actualizar la tabla de entregas
        })
        .catch(error => console.error("Error adding delivery:", error));
    }
});

// Función para eliminar una entrega por ID
document.getElementById("deleteDeliveryBtn").addEventListener("click", function() {
    const id = document.getElementById("id").value;

    if (id) {
        fetch(`/api/deliveries/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (response.ok) {
                alert(`Delivery with ID: ${id} deleted successfully!`);
                document.getElementById("deliveryForm").reset(); // Limpiar formulario
                fetchDeliveries(); // Actualizar la tabla de entregas
            } else {
                alert(`Failed to delete delivery with ID: ${id}`);
            }
        })
        .catch(error => console.error("Error deleting delivery:", error));
    } else {
        alert("Please provide a valid ID to delete a delivery.");
    }
});
