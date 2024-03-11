package com.example.oblig3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BillettController {

    @Autowired
    private BillettRepository rep;



    @PostMapping("/lagre")
    public Billett returBillett(Billett innBillett){
        rep.lagreBillett(innBillett);
        return innBillett;
    }

    @GetMapping("/hentAlle")
    public List<Billett> hentAlle(){
        return rep.hentAlleBilletter();
    }

    @GetMapping("/slettAlle")
    public void slettAlle(){
        rep.slettAlleBilletter();
    }

    @GetMapping("/hentEnBillett")
    public Billett hentEnBillett(int id){return this.rep.hentEnBillett(id);}

    @PostMapping("/endreEnBillett")
    public void endreEnBillett(Billett billett) {this.rep.endreEnBillett(billett);}

    @PostMapping("/slettEnBillett")
    public void slettEnBillett(int id){this.rep.slettEnBillett(id);}

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