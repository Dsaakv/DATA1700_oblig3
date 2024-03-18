

function validerFilm(film) {
    if (film === "") {
        $("#filmfeil").html("Du må velge en film");
        return false;
    } else {
        $("#filmfeil").html("");
        return true;
    }
}

function validerAntall(antall){
    if (antall <= 0){
        $("#feilmeldingantall").html("Du må skrive inn et antall som er høyere en 0");
        return false;
    } else {
        $("#feilmeldingantall").html("");
        return true;
    }
}

function validerFornavn(fornavn){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    const ok = regexp.test(fornavn);
    if(!ok){
        $("#feilmeldingfornavn").html("Må skrive inn et fornavn som består av 2 til 20 bokstaver");
        return false;
    }
    else{
        $("#feilmeldingfornavn").html("");
        return true;
    }
}

function validerEtternavn(etternavn){
    const regexp = /^[a-zA-ZæøåÆØÅ. \-]{2,20}$/;
    const ok = regexp.test(etternavn);
    if(!ok){
        $("#feilmeldingetternavn").html("Må skrive inn et etternavn som består av 2 til 20 bokstaver");
        return false;
    }
    else{
        $("#feilmeldingetternavn").html("");
        return true;
    }
}

function validerTelefonnr(telefonnr){
    const regexp = /^\d{8}$/;
    const ok = regexp.test(telefonnr);
    if(!ok){
        $("#feilmeldingtelefonnr").html("Du må skrive inn et gyldig telefon nummer på 8 siffre");
        return false;
    }
    else{
        $("#feilmeldingtelefonnr").html("");
        return true;
    }
}

function validerEpost(epost){
    const regexp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const ok = regexp.test(epost);
    if(!ok){
        $("#feilmeldingepost").html("Du må skrive inn en gyldig epost");
        return false;
    }
    else{
        $("#feilmeldingepost").html("");
        return true;
    }
}