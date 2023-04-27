package be.odisee.brainstorm2023wa3sb.domain;

import org.hibernate.annotations.Index;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="rollen")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("Rol")
public abstract class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected int id;

    // we willen dit even aan de db overlaten
    // specifieke noden FitLibrary zullen we met een functie oplossen
    // private static int nextid=1;



    @Column(unique=true)
    @Index(name="IRol_usernaam",columnNames="usernaam")
    protected String usernaam;



    @ManyToOne
    @JoinColumn(name="persoon_id")
    protected Persoon persoon;

    public Rol(){}

    public Rol(String usernaam,  Persoon persoon) {
        this.usernaam = usernaam;
        this.persoon = persoon;
    }

    public Rol(String usernaam) {
        this.usernaam = usernaam;
    }

    public Rol(int id, String usernaam, Persoon persoon) {

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