package com.example.oblig3_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public class BillettRepository {

    @Autowired
    private JdbcTemplate db;



    public void lagreBillett(Billett innBillett) {
        String sql = "INSERT INTO Billett (film, antall, fornavn, etternavn, telefonnr, epost) VALUES (?, ?, ?, ?, ?, ?)";
        this.db.update(sql, new Object[]{
                innBillett.getFilm(),
                innBillett.getAntall(),
                innBillett.getFornavn(),
                innBillett.getEtternavn(),
                innBillett.getTelefonnr(),
                innBillett.getEpost()
        });
    }


    public List<Billett> hentAlleBilletter(){
        String sql = "SELECT * FROM Billett";
        List<Billett>alleBilletter = db.query(sql, new BeanPropertyRowMapper(Billett.class));
        return  alleBilletter;
    }

    public void slettAlleBilletter(){
        String sql = "DELETE FROM Billett";
        db.update(sql);
    }

    public Billett hentEnBillett(int id){
        Object[] param = new Object[]{id};
        String sql = "SELECT * FROM Billett WHERE id=?";
        Billett enBillett = (Billett)this.db.queryForObject(sql, param, BeanPropertyRowMapper.newInstance(Billett.class));
        return enBillett;
    }

    public void endreEnBillett(Billett billett){
        String sql = "UPDATE Billett SET film=?, antall=?, fornavn=?, etternavn=?, telefonnr=?, epost=? WHERE id=?";
        this.db.update(sql, new Object[]{billett.getFilm(), billett.getAntall(), billett.getFornavn(),
                billett.getEtternavn(), billett.getTelefonnr(), billett.getEpost(), billett.getId()});
    }

    public void slettEnBillett(int id){
        String sql = "DELETE FROM Billett WHERE id=?";
        this.db.update(sql, new Object[]{id});
    }

}
