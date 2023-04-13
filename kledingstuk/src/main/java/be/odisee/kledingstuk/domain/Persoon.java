package be.odisee.kledingstuk.domain;

import be.odisee.kledingstuk.utulities.RolNotFoundException;
import jakarta.persistence.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="personen",
        indexes = { @Index(name="IPersoon_naam",columnList="familienaam, voornaam"),
                @Index(name="IPersoon_email",columnList="emailadres")
        } )
public class Persoon implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String voornaam;

    @Column
    @Size(min=2,message="Familienaam is minstens 2 letters aub")
    private String familienaam;

    @Column
    @Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",message="Vul een geldig e-mail adres in ")
    private String emailadres;

    @Column
    @Size(min=6,message="Paswoord is minstens 6 letters aub")
    private String paswoord;
    // we zullen nu toch een verwijzing naar Persoon in Rol moeten toevoegen
    @OneToMany(fetch=FetchType.EAGER,mappedBy="persoon")
    private Set<Rol> m_Rollen= new HashSet<Rol>();

    public Persoon(){

    }

    public Persoon( String voornaam, String familienaam, String emailadres, String paswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.emailadres = emailadres;
        this.paswoord = paswoord;
    }

    public Persoon(int id, String voornaam, String familienaam, String emailadres, String paswoord) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.emailadres = emailadres;
        this.paswoord = paswoord;
    }

    public int getId() {
        return id;
    }

    public String getEmailadres() {
        return emailadres;
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
        this.emailadres = emailadres;
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

    public Rol voegRolToe(String type, String usernaam) throws RolNotFoundException {
        Rol newRol=null;
        if (type.toLowerCase().equals("kledingmanager")) newRol= new Kledingmanager(type,usernaam);
        if (type.toLowerCase().equals("klant")) newRol= new Klant(type, usernaam);
        if (newRol==null) throw new RolNotFoundException("Type "+type+" is geen bekende Rol");
        m_Rollen.add(newRol);
        return newRol;
    }
}
