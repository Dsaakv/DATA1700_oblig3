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


$(document).ready(function(){
    hentAlleFilmer();
});


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

$(function(){
    const id = window.location.search.substring(1);
    const url = "/hentEnBillett?"+id;
    $.get(url,function(billett){
        $("#id").val(billett.id);
        $("#film").val(billett.film);
        $("#antall").val(billett.antall);
        $("#fornavn").val(billett.fornavn);
        $("#etternavn").val(billett.etternavn);
        $("#telefonnr").val(billett.telefonnr);
        $("#epost").val(billett.epost);
    })
        .fail(function() {
            $("#feil").html("Feil i db - prøv igjen senere");
        });
});

function validerOgendreBillett(){
    const filmOK = validerFilm($("#valgtFilm").val());
    const antallOK = validerAntall($("#antall").val());
    const fornavnOK = validerFornavn($("#fornavn").val());
    const etternavnOK = validerEtternavn($("#etternavn").val());
    const telefonnrOK = validerTelefonnr($("#telefonnr").val());
    const epostOK = validerEpost($("#epost").val());
    if (filmOK && antallOK && fornavnOK && etternavnOK && telefonnrOK && epostOK){
        endreBilletten();
    }
}

function endreBilletten() {
        const billett = {
        id: $("#id").val(),
        film: $("#valgtFilm").find(":selected").text(),
        antall: $("#antall").val(),
        fornavn: $("#fornavn").val(),
        etternavn: $("#etternavn").val(),
        telefonnr: $("#telefonnr").val(),
        epost: $("#epost").val()
    }
    $.post("/endreEnBillett", billett,function(){
        window.location.href = 'index.html';
    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });
}