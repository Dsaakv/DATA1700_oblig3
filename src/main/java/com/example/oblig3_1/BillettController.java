package com.example.oblig3_1;

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



    @PostMapping("/lagre")
    public void returBillett(Billett innBillett, HttpServletResponse response) throws IOException{

        if(!rep.lagreBillett(innBillett)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
        }
        //rep.lagreBillett(innBillett);
        //return innBillett;
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
        if(!rep.endreEnBillett(billett)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB - prøv igjen senere");
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
        listFilmer.add(new Film("Avengers", "Action"));
        listFilmer.add(new Film("Ringenes herre", "Fantasy"));
        listFilmer.add(new Film("Titanic", "Romanse"));
        listFilmer.add(new Film("John wick", "Action"));
        listFilmer.add(new Film("Taken", "Action"));
        listFilmer.add(new Film("Narnia", "Fantasy"));
        listFilmer.add(new Film("Dune", "Fantasy"));
        listFilmer.add(new Film("Notebook", "Romanse"));
        listFilmer.add(new Film("La la land", "Romanse"));
        return listFilmer;
    }
}