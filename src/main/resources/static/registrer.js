$(function (){
    hentAlleFilmer();
});

function validerOglagreBillett(){
    const filmOK = validerFilm($("#valgtFilm").val());
    const antallOK = validerAntall($("#antall").val());
    const fornavnOK = validerFornavn($("#fornavn").val());
    const etternavnOK = validerEtternavn($("#etternavn").val());
    const telefonnrOK = validerTelefonnr($("#telefonnr").val());
    const epostOK = validerEpost($("#epost").val());
    if (filmOK && antallOK && fornavnOK && etternavnOK && telefonnrOK && epostOK){
        registrerbillett();
    }
}
function registrerbillett() {
        const billett = {
            film: $("#valgtFilm").val(),
            antall: $("#antall").val(),
            fornavn: $("#fornavn").val(),
            etternavn: $("#etternavn").val(),
            telefonnr: $("#telefonnr").val(),
            epost: $("#epost").val()
        };
        $.post("/lagre", billett, function(){
            window.location.href= "index.html";
        })
       .fail(function (jqXHR){
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
        document.getElementById("bilettForm").reset();
}

function formaterFilmer(filmGrupper){
    let ut = "<select id='valgtFilm'>";
    ut+= "<option value=''>Velg Film</option>";
    for (const sjangerGruppe of filmGrupper){
        for (const film of sjangerGruppe){
            ut+="<option value='"+film.navn+"'>"+film.navn+"</option>";
        }
    }
    ut+="</select>";
    $("#filmene").html(ut);
}


function hentAlleFilmer(){
    $.get("/hentFilmer", function( filmer ) {
        const filmGrupper = {};
        filmer.forEach(film => {
            if (!filmGrupper[film.sjanger]) {
                filmGrupper[film.sjanger] = [];
            }
            filmGrupper[film.sjanger].push(film);
        });
        formaterFilmer(Object.values(filmGrupper));
    });

}