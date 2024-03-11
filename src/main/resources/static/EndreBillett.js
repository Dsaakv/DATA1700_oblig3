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
});

function endreBilletten() {


    let film = $("#valgtFilm").val();
    const antall = $("#antall").val();
    const fornavn = $("#fornavn").val();
    const etternavn = $("#etternavn").val();
    const telefonnr = $("#telefonnr").val();
    const epost = $("#epost").val();
    const filmfeil = $("#filmfeil");
    const antallfeil = $("#feilmeldingantall");
    const fornavnfeil = $("#feilmeldingfornavn");
    const etternavnfeil = $("#feilmeldingetternavn");
    const telefonnrfeil = $("#feilmeldingtelefonnr");
    const epostfeil = $("#feilmeldingepost");

    let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    let regexPhone = /^\d{8}$/;

    // Clear previous error messages
    filmfeil.html("");
    antallfeil.html('');
    fornavnfeil.html('');
    etternavnfeil.html('');
    telefonnrfeil.html('');
    epostfeil.html('');

    let errors = [];

    if (film === "") {
        filmfeil.html("Du må velge en film");
        errors.push('filmfeil');
    }

    if (antall.length === 0) {
        antallfeil.html("Du må skrive noe inn i antall");
        errors.push('antallfeil');
    }

    if (fornavn.length === 0) {
        fornavnfeil.html("Du må skrive noe inn i fornavn");
        errors.push('fornavnfeil');
    }

    if (etternavn.length === 0) {
        etternavnfeil.html("Du må skrive noe inn i etternavn");
        errors.push('etternavnfeil');
    }

    if (telefonnr.length === 0) {
        telefonnrfeil.html("Du må skrive noe inn i telefonnr");
        errors.push('telefonnrfeil');
    } else if (!regexPhone.test(telefonnr)) {
        telefonnrfeil.html("Du må skrive et gyldig telefonnummer (8 siffer)");
        errors.push('telefonnrfeil');
    }

    if (epost.length === 0) {
        epostfeil.html("Du må skrive noe inn i epost");
        errors.push('epostfeil');
    } else if (!regex.test(epost)) {
        epostfeil.html("Du må skrive inn en gyldig epost");
        errors.push('epostfeil');
    }

    if (errors.length > 0) {
        return false; // Prevent form submission if there are errors
    } else  {
        const billett = {
        id: $("#id").val(),
        film: $("#valgtFilm").find(":selected").text(), // Changed this line
        antall: $("#antall").val(),
        fornavn: $("#fornavn").val(),
        etternavn: $("#etternavn").val(),
        telefonnr: $("#telefonnr").val(),
        epost: $("#epost").val()
    }
    $.post("/endreEnBillett", billett,function(){
        window.location.href = 'index.html';
    })
}
}