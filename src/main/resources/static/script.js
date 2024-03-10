
$(function(){
    hentAlle();
})


function hentAlle() {
    $.get("/hentAlle", function(billetter) {
        let ut = "<table class=\"bilettliste-table custom-table\"><tr>" +
            "<th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Telefonnr</th><th>Epost</th> " +
            "</tr>";

        for (const billet of billetter) {
            ut += "<tr class='bilettliste-row'>";
            ut += "<td class='bilettliste-cell'>" + billet.film + "</td><td class='bilettliste-cell'>" + billet.antall + "</td><td class='bilettliste-cell'>" + billet.fornavn + "</td><td class='bilettliste-cell'>" + billet.etternavn + "</td><td class='bilettliste-cell'>" + billet.telefonnr + "</td><td class='bilettliste-cell'>" + billet.epost + "</td>";
            ut += "</tr>";
        }
        document.getElementById("bilettliste").innerHTML = ut;
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