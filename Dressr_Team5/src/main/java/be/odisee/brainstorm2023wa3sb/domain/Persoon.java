package be.odisee.brainstorm2023wa3sb.domain;

import be.odisee.brainstorm2023wa3sb.utilities.RolNotFoundException;
import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="personen",
        indexes = { @Index(name="IPersoon_naam",columnList="familienaam, voornaam"),
                @Index(name="IPersoon_email",columnList="emailadress")
        } )
public class Persoon implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;


    @Column
    @NotEmpty(message="Vul voornaam in aub")
    private String voornaam;

    @Column
    @Size(min=2,message="Familienaam is minstens 2 letters aub")
    private String familienaam;

    @Column
    @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",message="Vul een geldig e-mail adres in ")
    private String emailadress;

    @Column
    @Size(min=6,message="Paswoord is minstens 6 letters aub")
    private String paswoord;

    public String getEmailadress() {
        return emailadress;
    }

    public void setEmailadress(String emailadress) {
        this.emailadress = emailadress;
    }

    public Set<Rol> getM_Rollen() {
        return m_Rollen;
    }



    // we zullen nu toch een verwijzing naar Persoon in Rol moeten toevoegen
    @OneToMany(fetch=FetchType.EAGER,mappedBy="persoon")
    private Set<Rol> m_Rollen= new HashSet<Rol>();

    public Persoon(){

    }

    public Persoon(String voornaam, String familienaam, String emailadres, String paswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.emailadress = emailadres;
        this.paswoord = paswoord;
    }

    public Persoon(int id, String voornaam, String familienaam, String emailadres, String paswoord) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.emailadress = emailadres;
        this.paswoord = paswoord;
    }


    public int getId() {
        return id;
    }

    public String getEmailadres() {
        return emailadress;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Set<Rol> getRollen(){
        return m_Rollen;
    }

    public void setEmailadres(String emailadres) {
        this.emailadress = emailadres;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setM_Rollen(Set<Rol> m_Rollen) {
        this.m_Rollen = m_Rollen;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }


    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }


}