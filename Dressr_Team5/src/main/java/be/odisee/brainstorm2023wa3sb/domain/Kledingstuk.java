package be.odisee.brainstorm2023wa3sb.domain;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "kledingstukken")


public class Kledingstuk {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;



    @Column
    @NotEmpty(message="Vul naam in aub")
    private String naam;

    @Column
    @NotEmpty(message="Vul merk in aub")
    private String merk;

    @Column String kledingstukspecificaties;

    @Column boolean gereserveerd;



    public Kledingstuk(){

    }
    public Kledingstuk(String naam,String merk, String kledingspecificaties) {
        this.naam = naam;
        this.merk = merk;
        this.kledingstukspecificaties = kledingspecificaties;
        setGereserveerd(false);

    }



    public String getKledingstukspecificaties() {
        return kledingstukspecificaties;
    }

    public void setKledingstukspecificaties(String kledingstukspecificaties) {
        this.kledingstukspecificaties = kledingstukspecificaties;
    }

    public boolean isGereserveerd() {
        return gereserveerd;
    }

    public void setGereserveerd(boolean gereserveerd) {
        this.gereserveerd = gereserveerd;
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





