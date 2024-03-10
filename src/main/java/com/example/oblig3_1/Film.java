package com.example.oblig3_1;

public class Film {
    private String navn;
    private String sjanger;

    public Film(String navn, String sjanger){
        this.navn = navn;
        this.sjanger = sjanger;
    }

    public Film(){
    }

    public String getNavn() {return navn; }

    public void setNavn(String navn) {this.navn = navn; }

    public String getSjanger() {return sjanger; }

    public void setSjanger(String sjanger) {this.sjanger = sjanger;}
}
