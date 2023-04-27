package be.odisee.brainstorm2023wa3sb.domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Wasserijmedewerker")
public class Wasserijmedewerker extends Rol {
    public Wasserijmedewerker(){}

    public Wasserijmedewerker(String usernaam, Persoon persoon){
        super(usernaam,persoon);
    }

    public Wasserijmedewerker(int id, String usernaam, Persoon persoon){
        super(id,usernaam,persoon);
    }

    public Wasserijmedewerker(String type, String usernaam) {
    }

    @Override
    public String getType(){
        return "Wasserijmedewerker";
    }
}

