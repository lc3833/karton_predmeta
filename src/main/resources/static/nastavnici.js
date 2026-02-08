window.izabraniNastavniciIds = [];

document.addEventListener("DOMContentLoaded", function() {
    
    const dropdown = document.getElementById('nastavnik');

    if (!dropdown) return;

    let addButton = document.createElement('button');
    addButton.textContent = "Додај";
    addButton.style.marginLeft = "10px";
    addButton.addEventListener('click', dodajNastavnika);
    
    if(dropdown.parentNode) {
        dropdown.parentNode.appendChild(addButton);
    }

    let container = document.createElement('div');
    container.id = "selectedNastavnici";
    container.style.marginTop = "10px";
    
    if(dropdown.parentNode) {
        dropdown.parentNode.appendChild(container);
    }

    fetch('/api/nastavnici')
        .then(response => response.json())
        .then(data => {
            dropdown.innerHTML = '<option value="">Одабери наставника</option>';
            
            data.forEach(n => {
                const option = document.createElement('option');
                option.value = n.id; 
                
                let prikaz = (n.prezime || "") + " " + (n.ime || "");
                
                if (n.zvanje && n.zvanje.naziv) {
                    prikaz += ", " + n.zvanje.naziv; 
                }
                
                option.text = prikaz;
                dropdown.add(option);
            });
        })
        .catch(err => console.error("Greška pri učitavanju nastavnika:", err));
});

function dodajNastavnika() {
    const dropdown = document.getElementById('nastavnik');
    const container = document.getElementById('selectedNastavnici');
    
    const selectedId = dropdown.value; 
    const selectedText = dropdown.options[dropdown.selectedIndex].text; 

    if (!selectedId || selectedId === "") {
        alert("Молимо изаберите наставника.");
        return;
    }

    if (window.izabraniNastavniciIds.includes(parseInt(selectedId))) {
        alert("Тај наставник је већ додат!");
        return;
    }

    window.izabraniNastavniciIds.push(parseInt(selectedId));

    const itemDiv = document.createElement('div');
    itemDiv.className = "nastavnik-item";
    itemDiv.style.display = "flex";
    itemDiv.style.alignItems = "center";
    itemDiv.style.justifyContent = "space-between";
    itemDiv.style.marginBottom = "5px";
    itemDiv.style.padding = "8px";
    itemDiv.style.border = "1px solid #ddd";
    itemDiv.style.backgroundColor = "#f9f9f9";
    itemDiv.style.borderRadius = "4px";
    itemDiv.style.width = "100%"; 

    const nameSpan = document.createElement('span');
    nameSpan.textContent = selectedText;
    nameSpan.style.fontWeight = "bold";

    const deleteBtn = document.createElement('button');
    deleteBtn.textContent = "Обриши";
    deleteBtn.style.marginLeft = "15px";
    deleteBtn.style.cursor = "pointer";
    deleteBtn.style.color = "white";
    deleteBtn.style.backgroundColor = "#dc3545";
    deleteBtn.style.border = "none";
    deleteBtn.style.padding = "5px 10px";
    deleteBtn.style.borderRadius = "3px";

    deleteBtn.addEventListener('click', () => {
        const index = window.izabraniNastavniciIds.indexOf(parseInt(selectedId));
        if (index > -1) {
            window.izabraniNastavniciIds.splice(index, 1);
        }
        container.removeChild(itemDiv);
    });

    itemDiv.appendChild(nameSpan);
    itemDiv.appendChild(deleteBtn);
    container.appendChild(itemDiv);
}