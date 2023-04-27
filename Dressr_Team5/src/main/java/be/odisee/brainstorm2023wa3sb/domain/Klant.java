package be.odisee.brainstorm2023wa3sb.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Klant")
public class Klant extends Rol {
    public Klant(){}

    public Klant(String usernaam, Persoon persoon) {
        super(usernaam, persoon);
    }

    public Klant(int id, String usernaam, Persoon persoon) {
        super(id, usernaam, persoon);
    }

    public Klant(String type, String usernaam) {
    }

    @Override
    public String getType(){
        return "Klant";
    }
}
