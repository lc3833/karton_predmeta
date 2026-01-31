document.addEventListener("DOMContentLoaded", function() {
    popuniStatickePodatke();
    ucitajSmerove();
    ucitajNastavnike();
    ucitajPredmeteZaPreduslov();
});

// 1. STATIČKI PODACI (ESPB, STATUS)
function popuniStatickePodatke() {
    // --- ESPB (1 do 60) ---
    const espbSelect = document.getElementById("espb");
    if (espbSelect) {
        for (let i = 1; i <= 60; i++) {
            let opcija = document.createElement("option");
            opcija.value = i;
            opcija.text = i;
            espbSelect.appendChild(opcija);
        }
    }

    // --- STATUS (Obavezan / Izborni) ---
    const statusSelect = document.getElementById("statusPredmeta");
    if (statusSelect) {
        let opcije = ["Obavezan", "Izborni"];
        opcije.forEach(st => {
            let opcija = document.createElement("option");
            opcija.value = st;
            opcija.text = st;
            statusSelect.appendChild(opcija);
        });
    }
}

// 2. UCITAVANJE SMEROVA IZ BAZE
function ucitajSmerove() {
    fetch('/api/programi')
        .then(response => response.json())
        .then(data => {
            let select = document.getElementById("studijskiProgram");
            if (!select) return;
            
            data.forEach(smer => {
                let opcija = document.createElement("option");
                opcija.value = smer.id;
                opcija.text = smer.naziv;
                select.appendChild(opcija);
            });
        })
        .catch(err => console.error("Greška pri učitavanju smerova:", err));
}

// 3. UCITAVANJE NASTAVNIKA IZ BAZE
function ucitajNastavnike() {
    fetch('/api/nastavnici')
        .then(response => response.json())
        .then(data => {
            let select = document.getElementById("nastavnik");
            if (!select) return;

            data.forEach(n => {
                let opcija = document.createElement("option");
                opcija.value = n.id;
                let prikaz = (n.ime || "") + " " + (n.prezime || "");
                if (n.zvanje && n.zvanje.naziv) {
                    prikaz += " (" + n.zvanje.naziv + ")";
                }
                opcija.text = prikaz;
                select.appendChild(opcija);
            });
        })
        .catch(err => console.error("Greška pri učitavanju nastavnika:", err));
}

// 4. UCITAVANJE PREDMETA (ZA LISTU PREDUSLOVA)
function ucitajPredmeteZaPreduslov() {
    fetch('/api/predmeti')
        .then(response => response.json())
        .then(data => {
            let select = document.getElementById("preduslov");
            if (!select) return;

            data.forEach(p => {
                let opcija = document.createElement("option");
                opcija.value = p.naziv; 
                opcija.text = p.naziv;
                select.appendChild(opcija);
            });
        })
        .catch(err => console.error("Greška pri učitavanju predmeta:", err));
}

// --- DEO ZA ČUVANJE (SAVE) ---

function sacuvajKarton() {
    // 1. Osnovni podaci
    let predmet = {
        naziv: document.getElementById("nazivPredmeta").value,
        status: document.getElementById("statusPredmeta").value,
        espb: parseInt(document.getElementById("espb").value),
        uslov: document.getElementById("uslov").value,
        studijskiProgram: { id: document.getElementById("studijskiProgram").value },
        nastavnik: { id: document.getElementById("nastavnik").value },
        
        // 2. Cilj i Ishod
        ciljPredmeta: { 
            opis: document.querySelector(".naslov-polje:nth-of-type(1) .tekst-polje").innerText 
        },
        ishodPredmeta: { 
             opis: document.querySelectorAll(".naslov-polje .tekst-polje")[1].innerText 
        }
    };

    // 3. Literatura
    let literaturaLista = [];
    document.querySelectorAll("#literatura-body tr").forEach(row => {
        let inputs = row.querySelectorAll("input");
        if(inputs.length > 0) {
            literaturaLista.push({
                autor: inputs[0].value,
                naslov: inputs[1].value,
                izdavac: inputs[2].value,
                godina: parseInt(inputs[3].value) || 2023
            });
        }
    });
    predmet.literatura = literaturaLista;

    // 4. Fond Časova (Pazi na redosled celija!)
    let nastavaRed = document.querySelector(".nastava-table tbody tr");
    let celijeNastave = nastavaRed.querySelectorAll("td[contenteditable='true']");
    
    predmet.fondCasova = {
        teorija: parseInt(celijeNastave[0].innerText) || 0, 
        vezbe: parseInt(celijeNastave[1].innerText) || 0,   
        don: parseInt(celijeNastave[2].innerText) || 0,     
        sir: parseInt(celijeNastave[3].innerText) || 0,
        ostalo: parseInt(celijeNastave[4].innerText) || 0
    };

    // 5. Obaveze (Predispitne i Završni)
    let oceneRed = document.querySelector(".ocene-table tr:nth-child(3)");
    let celijeOcena = oceneRed.querySelectorAll("td");
    
    let predispitna = {
        tip: "PREDISPITNA",
        opisAktivnosti: celijeOcena[0].innerText,
        obavezna: celijeOcena[1].innerText.toLowerCase().includes("da"),
        poeni: parseFloat(celijeOcena[2].innerText) || 0
    };
    
    let zavrsni = {
        tip: "ZAVRSNI",
        formatIspita: celijeOcena[3].innerText,
        obavezna: celijeOcena[4].innerText.toLowerCase().includes("da"),
        poeni: parseFloat(celijeOcena[5].innerText) || 0
    };

    predmet.obaveze = [predispitna, zavrsni];

    // Slanje
    console.log("Šaljem JSON:", predmet); 

    fetch('/api/predmeti', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(predmet)
    })
    .then(response => {
        if (response.ok) {
            alert("✅ Uspešno sačuvano!");
            return response.json(); // Ako hoces da vidis odgovor
        } else {
            alert("❌ Greška! Proveri konzolu (F12).");
            console.error(response);
        }
    })
    .catch(error => console.error('Greška:', error));
}