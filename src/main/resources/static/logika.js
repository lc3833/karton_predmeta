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

    let urlParams = new URLSearchParams(window.location.search);
    let currentId = urlParams.get('id');

    let predmet = window.ucitaniPredmet || {};

    if (currentId) {
        predmet.id = parseInt(currentId);
    }

    predmet.naziv = document.getElementById("nazivPredmeta").value;
    predmet.status = document.getElementById("statusPredmeta").value;
    predmet.espb = parseInt(document.getElementById("espb").value);
    predmet.uslov = document.getElementById("uslov").value;
    predmet.studijskiProgram = { id: parseInt(document.getElementById("studijskiProgram").value) };
    predmet.nastavnici = listaNastavnikaZaSlanje;
    
    if (!predmet.ciljPredmeta) predmet.ciljPredmeta = {};
    predmet.ciljPredmeta.opis = document.querySelector(".naslov-polje:nth-of-type(1) .tekst-polje").innerText;

    if (!predmet.ishodPredmeta) predmet.ishodPredmeta = {};
    predmet.ishodPredmeta.opis = document.querySelectorAll(".naslov-polje .tekst-polje")[1].innerText;

    if (!predmet.metodIzvodjenja) predmet.metodIzvodjenja = {};
    predmet.metodIzvodjenja.opis = document.querySelector(".sadrzaj-predmeta").innerText;

    if (!predmet.fondCasova) predmet.fondCasova = {};
    predmet.fondCasova.teorija = teorija;
    predmet.fondCasova.vezbe = vezbe;
    predmet.fondCasova.don = don;
    predmet.fondCasova.sir = sir;
    predmet.fondCasova.ostalo = ostalo;

    if (!predmet.obaveze || predmet.obaveze.length === 0) {
        predmet.obaveze = [{ tip: "PREDISPITNA" }, { tip: "ZAVRSNI" }];
    }
    let predObaveza = predmet.obaveze.find(o => o.tip === 'PREDISPITNA') || predmet.obaveze[0] || { tip: "PREDISPITNA" };
    predObaveza.opisAktivnosti = celijeOcena[0].innerText;
    predObaveza.obavezna = predispitnaObavezna;
    predObaveza.poeni = poeniPredispitne;

    let zavObaveza = predmet.obaveze.find(o => o.tip === 'ZAVRSNI') || predmet.obaveze[1] || { tip: "ZAVRSNI" };
    zavObaveza.formatIspita = celijeOcena[3].innerText;
    zavObaveza.obavezna = zavrsniObavezan;
    zavObaveza.poeni = poeniZavrsni;

    predmet.obaveze = [predObaveza, zavObaveza];

    let inputiNedelja = document.querySelectorAll(".nedelja-input");
    if(inputiNedelja.length > 0) {
        if (!predmet.nedeljniPlan) predmet.nedeljniPlan = [];
        inputiNedelja.forEach((input, index) => {
            let np = predmet.nedeljniPlan.find(p => p.brojNedelje === (index + 1));
            if (np) {
                np.tema = input.value || "Није дефинисано";
            } else {
                predmet.nedeljniPlan.push({
                    brojNedelje: index + 1,
                    tema: input.value || "Није дефинисано"
                });
            }
        });
    }

    let literaturaLista = [];
    document.querySelectorAll("#literatura-body tr").forEach((row, index) => {
        let inputs = row.querySelectorAll("input");
        if(inputs.length > 0) {
            let staraLit = (predmet.literatura && predmet.literatura[index]) ? predmet.literatura[index] : {};
            literaturaLista.push({
                id: staraLit.id || null, 
                autor: inputs[0].value,
                naslov: inputs[1].value,
                izdavac: inputs[2].value,
                godina: parseInt(inputs[3].value) || 2023
            });
        }
    });
    predmet.literatura = literaturaLista;

    console.log("Šaljem JSON:", predmet); 

    let metoda = currentId ? 'PUT' : 'POST';
    let url = currentId ? '/api/predmeti/' + currentId : '/api/predmeti';

    fetch(url, {
        method: metoda,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(predmet)
    })
    .then(response => {
        if (response.ok) {
            alert("УСПЕШНО САЧУВАНО!");
            window.location.href = 'lista.html';
            return response.json(); 
        } else {
            alert("Грешка! Провери конзолу (F12).");
            console.error(response);
        }
    })
    .catch(error => console.error('Greška:', error));
}

function nazadNaListu() {
    let potvrda = confirm("Уколико напустите страницу без чувања, изгубићете овај картон предмета. Да ли сте сигурни да желите да се вратите назад?");
    if (potvrda) {
        window.location.href = "lista.html";
    }
}