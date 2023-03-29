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

    @Column String kledingstukspecificaties;


    public Kledingstuk(){

    }
    public Kledingstuk(String naam, String kledingspecificaties) {
        this.naam = naam;
        this.kledingstukspecificaties = kledingspecificaties;
    }

    public int getId() {
        return id;
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





