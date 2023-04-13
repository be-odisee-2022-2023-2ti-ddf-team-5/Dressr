package be.odisee.kledingstuk.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="rollen")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Rol")
public abstract class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected int id;

    @Column(unique=true)
    @org.hibernate.annotations.Index(name="IRol_usernaam",columnNames="usernaam")
    protected String usernaam;

    @ManyToOne
    @JoinColumn(name="persoon_id")
    protected Persoon persoon;

    public Rol(){}

    public Rol(String usernaam,Persoon persoon) {
        this.usernaam = usernaam;
        this.persoon = persoon;
    }

    public Rol(int id, String usernaam, Persoon persoon) {
        this.id = id;
        this.usernaam = usernaam;
        this.persoon = persoon;
    }

    public int getId() {
        return id;
    }



    public String getUsernaam() {
        return usernaam;
    }

    public abstract String getType();


    public Persoon getPersoon() {
        return persoon;
    }

    public void setPersoon(Persoon persoon) {
        this.persoon = persoon;
    }

}
