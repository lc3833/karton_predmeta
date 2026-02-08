function addLiteratura() {
    const tbody = document.getElementById("literatura-body");
    const rowNumber = tbody.rows.length + 1;

    const row = tbody.insertRow();
    
    const cell1 = row.insertCell();
    cell1.style.display = "flex";
    cell1.style.alignItems = "center";
    cell1.style.justifyContent = "space-between";
    cell1.innerHTML = `
        <span>${rowNumber}</span>
        <button onclick="deleteRow(this)">Obriši</button>
    `;

    for (let i = 0; i < 4; i++) {
        const cell = row.insertCell();
        const input = document.createElement("input");
        input.type = "text";
        cell.appendChild(input);
    }
}

function deleteRow(btn) {
    const row = btn.closest('tr');
    row.remove();

    const rows = document.querySelectorAll("#literatura-body tr");
    rows.forEach((r, index) => {
        r.cells[0].querySelector('span').textContent = index + 1;
    });
}
