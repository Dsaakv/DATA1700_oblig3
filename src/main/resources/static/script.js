
$(function(){
    hentAlle();
})


function hentAlle() {
    $.get("/hentAlle", function(billetter) {
        formaterBilletter(billetter)
    });
}

function formaterBilletter(billetter){
    let ut = "<table class=\"bilettliste-table custom-table\"><tr>" +
        "<th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Telefonnr</th><th>Epost</th> " +
        "</tr>";

    for (const billet of billetter) {
        ut += "<tr>" +
            "<td class='bilettliste-cell'>" + billet.film + "</td>" +
            "<td class='bilettliste-cell'>" + billet.antall + "</td>" +
            "<td class='bilettliste-cell'>" + billet.fornavn + "</td>" +
            "<td class='bilettliste-cell'>" + billet.etternavn + "</td>" +
            "<td class='bilettliste-cell'>" + billet.telefonnr + "</td>" +
            "<td class='bilettliste-cell'>" + billet.epost + "</td>" +
            "<td> <a class='btn btn-primary' href='endreBillett.html?id=" + billet.id + "'>Endre</a></td>" +
            "<td> <button class='btn btn-danger' onclick='slettEnBillett(" + billet.id + ")'>Slett</button></td>" +
            "</tr>";
    }
    document.getElementById("bilettliste").innerHTML = ut;
}

function slettEnBillett(id) {
    const url = "/slettEnBillett?id="+id;
    $.get( url, function(){
        window.location.href = "/";
    });
}


function slettbiletter(){
    const ok=confirm("Vil du slette alle billetter?")
    if(ok){
        $.get( "/slettAlle", function() {
            hentAlle();
        });
    }
}