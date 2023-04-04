package be.odisee.kledingstuk.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "kledingstukken")


public class Kledingstuk {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String naam;

    @Column
    private String merk;

    @Column String kledingstukspecificaties;


    public Kledingstuk(){

    }
    public Kledingstuk(String naam,String merk, String kledingspecificaties) {
        this.naam = naam;
        this.merk = merk;
        this.kledingstukspecificaties = kledingspecificaties;
    }

    public int getId() {
        return id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getNaam() {
        return naam;
    }

    public String getKledingspecificaties() {
        return kledingstukspecificaties;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setKledingspecificaties(String kledingstukspecificaties) {
        this.kledingstukspecificaties = kledingstukspecificaties;
    }

}





