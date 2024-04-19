package com.example.oblig3_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BillettRepository {

    @Autowired
    private JdbcTemplate db;


    private Logger logger = LoggerFactory.getLogger(BillettRepository.class);

    public boolean lagreBillett(Billett innBillett) {
        String sql = "INSERT INTO Billett (film, antall, fornavn, etternavn, telefonnr, epost) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            db.update(sql,
                    innBillett.getFilm(),
                    innBillett.getAntall(),
                    innBillett.getFornavn(),
                    innBillett.getEtternavn(),
                    innBillett.getTelefonnr(),
                    innBillett.getEpost()
            );
            return true;
        }
        catch (Exception e){
            logger.error("Feil i lagrebillett : "+e);
            return false;
        }
    }


    public List<Billett> hentAlleBilletter(){
        String sql = "SELECT * FROM Billett ORDER BY etternavn ASC";
        try {
            List<Billett> alleBilletter = db.query(sql, new BeanPropertyRowMapper(Billett.class));
            return alleBilletter;
        }
        catch (Exception e){
            logger.error("Feil i hentAlleBilletter : "+e);
            return null;
        }
    }

    public boolean slettAlleBilletter(){
        String sql = "DELETE FROM Billett";
        try {
            db.update(sql);
            return true;
        }
        catch(Exception e){
            logger.error("Feil i slettAlleBilletter : "+e);
            return false;
        }
    }

    public Billett hentEnBillett(int id){
        //Object[] param = new Object[]{id};
        String sql = "SELECT * FROM Billett WHERE id=?";
        try {
            Billett enBillett = db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Billett.class), id);
            return enBillett;
        }
        catch(Exception e){
            logger.error("Feil i hentEnBillett : "+e);
            return null;
        }
    }

    public boolean endreEnBillett(Billett billett){
        String sql = "UPDATE Billett SET film=?, antall=?, fornavn=?, etternavn=?, telefonnr=?, epost=? WHERE id=?";
        try {
            db.update(sql, billett.getFilm(), billett.getAntall(), billett.getFornavn(),
                    billett.getEtternavn(), billett.getTelefonnr(), billett.getEpost(), billett.getId());
            return true;
        }
        catch (Exception e){
            logger.error("Feil i endreEnBillett : "+e);
            return false;
        }
    }

    public boolean slettEnBillett(int id){
        String sql = "DELETE FROM Billett WHERE id=?";
        try {
            db.update(sql, id);
            return true;
        }
        catch (Exception e){
            logger.error("Feil i slettEnBillett : "+e);
            return false;
        }
    }

}
