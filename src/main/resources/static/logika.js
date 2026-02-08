document.addEventListener("DOMContentLoaded", function() {
    popuniStatickePodatke();
    ucitajSmerove();
    ucitajPredmeteZaPreduslov();
});

function popuniStatickePodatke() {
    const espbSelect = document.getElementById("espb");
    if (espbSelect) {
        for (let i = 1; i <= 30; i++) {
            let opcija = document.createElement("option");
            opcija.value = i;
            opcija.text = i;
            espbSelect.appendChild(opcija);
        }
    }

    const statusSelect = document.getElementById("statusPredmeta");
    if (statusSelect) {
        let opcije = ["Обавезан", "Изборни"];
        opcije.forEach(st => {
            let opcija = document.createElement("option");
            opcija.value = st;
            opcija.text = st;
            statusSelect.appendChild(opcija);
        });
    }
}

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
        .catch(err => console.error(err));
}

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
        .catch(err => console.error(err));
}

function sacuvajKarton() {
    if (typeof window.izabraniNastavniciIds === 'undefined' || window.izabraniNastavniciIds.length === 0) {
        alert("ГРЕШКА: Морате изабрати бар једног наставника!");
        return;
    }

    let nastavaRed = document.querySelector(".nastava-table tbody tr");
    let celijeNastave = nastavaRed.querySelectorAll("td[contenteditable='true']");
    let teorija = parseInt(celijeNastave[0].innerText) || 0;
    let vezbe = parseInt(celijeNastave[1].innerText) || 0;
    let don = parseInt(celijeNastave[2].innerText) || 0;
    let sir = parseInt(celijeNastave[3].innerText) || 0;
    let ostalo = parseInt(celijeNastave[4].innerText) || 0;

    if (teorija < 1) {
        alert("ГРЕШКА У ФОНДУ ЧАСОВА!\n\nТеоријска настава мора имати најмање 1 час.");
        return; 
    }

    let zbirCasova = teorija + vezbe + don + sir + ostalo;

    if (zbirCasova !== 4) {
        alert("ГРЕШКА У ФОНДУ ЧАСОВА!\n\nУкупан збир часова активне наставе мора бити тачно 4.\nТренутни збир: " + zbirCasova + "\n(Могуће комбинације: 2+2, 2+1+1, 3+1, 4+0...)");
        return; 
    }

    let oceneRed = document.querySelector(".ocene-table tr:nth-child(3)");
    let celijeOcena = oceneRed.querySelectorAll("td");
    let poeniPredispitne = parseFloat(celijeOcena[2].innerText) || 0;
    let poeniZavrsni = parseFloat(celijeOcena[5].innerText) || 0;
    let ukupnoPoena = poeniPredispitne + poeniZavrsni;

    if (ukupnoPoena !== 100) {
        alert("ГРЕШКА У БОДОВАЊУ!\n\nЗбир поена мора бити тачно 100.\nТренутни збир: " + ukupnoPoena + "\n(Предиспитне: " + poeniPredispitne + " + Завршни: " + poeniZavrsni + ")");
        return;
    }

    let tekstPredispitne = celijeOcena[1].innerText.toLowerCase();
    let tekstZavrsni = celijeOcena[4].innerText.toLowerCase();
    let predispitnaObavezna = tekstPredispitne.includes("d") || tekstPredispitne.includes("\u0434");
    let zavrsniObavezan = tekstZavrsni.includes("d") || tekstZavrsni.includes("\u0434");
    let listaNastavnikaZaSlanje = window.izabraniNastavniciIds.map(id => ({ id: id }));

    let predmet = {
        naziv: document.getElementById("nazivPredmeta").value,
        status: document.getElementById("statusPredmeta").value,
        espb: parseInt(document.getElementById("espb").value),
        uslov: document.getElementById("uslov").value,
        studijskiProgram: { id: document.getElementById("studijskiProgram").value },
        nastavnici: listaNastavnikaZaSlanje,
        
        ciljPredmeta: { 
            opis: document.querySelector(".naslov-polje:nth-of-type(1) .tekst-polje").innerText 
        },
        ishodPredmeta: { 
             opis: document.querySelectorAll(".naslov-polje .tekst-polje")[1].innerText 
        },
        metodIzvodjenja: {
            opis: document.querySelector(".sadrzaj-predmeta").innerText
        },
        fondCasova: {
            teorija: teorija, vezbe: vezbe, don: don, sir: sir, ostalo: ostalo
        },
        obaveze: [
            {
                tip: "PREDISPITNA",
                opisAktivnosti: celijeOcena[0].innerText,
                obavezna: predispitnaObavezna,
                poeni: poeniPredispitne
            },
            {
                tip: "ZAVRSNI",
                formatIspita: celijeOcena[3].innerText,
                obavezna: zavrsniObavezan,
                poeni: poeniZavrsni
            }
        ]
    };

    let nedeljniPlanLista = [];
    let inputiNedelja = document.querySelectorAll(".nedelja-input");
    
    if(inputiNedelja.length > 0) {
        inputiNedelja.forEach((input, index) => {
            nedeljniPlanLista.push({
                brojNedelje: index + 1,
                tema: input.value || "Није дефинисано"
            });
        });
        predmet.nedeljniPlan = nedeljniPlanLista;
    }

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

    console.log("Šaljem JSON:", predmet); 

    fetch('/api/predmeti', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(predmet)
    })
    .then(response => {
        if (response.ok) {
            alert("УСПЕШНО САЧУВАНО!");
            return response.json(); 
        } else {
            alert("Грешка! Провери конзолу (F12).");
            console.error(response);
        }
    })
    .catch(error => console.error('Greška:', error));
}