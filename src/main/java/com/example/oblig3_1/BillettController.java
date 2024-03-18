package com.example.oblig3_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

@RestController
public class BillettController {

    @Autowired
    private BillettRepository rep;


    private Logger logger = LoggerFactory.getLogger(BillettController.class);

    public boolean validerBillett(Billett billett) {
        String regexNavn = "[a-zA-ZæøåÆØÅ. \\-]{2,20}";
        String regexTelefonnr = "\\d{8}";
        String regexEpost = "\\b[\\w.%-]+@[\\w.-]+\\.[a-zA-Z]{2,4}\\b";

        boolean navnOK = billett.getFornavn().matches(regexNavn);
        boolean etternavnOK = billett.getEtternavn().matches(regexNavn);
        boolean telefonnrOK = billett.getTelefonnr().matches(regexTelefonnr);
        boolean epostOK = billett.getEpost().matches(regexEpost);

        if (navnOK && etternavnOK && telefonnrOK && epostOK && billett.getAntall() > 0) {
            return true;
        }
        logger.error("Validerinsfeil");
        return false;
    }


    @PostMapping("/lagre")
    public void returBillett(Billett innBillett, HttpServletResponse response) throws IOException{

        if(!validerBillett(innBillett)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i Validering - prøv igjen senere");
        }
        else {
            if (!rep.lagreBillett(innBillett)) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            }
        }
    }

    @GetMapping("/hentAlle")
    public List<Billett> hentAlle(HttpServletResponse response) throws IOException{
        List<Billett> alleBilletter = rep.hentAlleBilletter();
        if(alleBilletter==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
        return alleBilletter;
    }

    @GetMapping("/slettAlle")
    public void slettAlle(HttpServletResponse response) throws IOException{
        if(!rep.slettAlleBilletter()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
    }

    @GetMapping("/hentEnBillett")
    public Billett hentEnBillett(int id, HttpServletResponse response) throws IOException{
        Billett billetten = rep.hentEnBillett(id);

        if(billetten == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
        return billetten;
    }

    @PostMapping("/endreEnBillett")
    public void endreEnBillett(Billett billett, HttpServletResponse response) throws IOException {

        if(!validerBillett(billett)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i Validering - prøv igjen senere");
        }
        else {
            if (!rep.endreEnBillett(billett)) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
            }
        }
    }

    @PostMapping("/slettEnBillett")
    public void slettEnBillett(int id, HttpServletResponse response) throws IOException{
        if (!rep.slettEnBillett(id)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
        }
    }

    @GetMapping("/hentFilmer")
    public List<Film> hentFilmer(){
        List<Film> listFilmer = new ArrayList<>();
        listFilmer.add(new Film("Avengers"));
        listFilmer.add(new Film("Ringenes herre"));
        listFilmer.add(new Film("Titanic"));
        listFilmer.add(new Film("John wick"));
        listFilmer.add(new Film("Taken"));
        listFilmer.add(new Film("Narnia"));
        listFilmer.add(new Film("Dune"));
        listFilmer.add(new Film("Notebook"));
        listFilmer.add(new Film("La la land"));
        return listFilmer;
    }
}