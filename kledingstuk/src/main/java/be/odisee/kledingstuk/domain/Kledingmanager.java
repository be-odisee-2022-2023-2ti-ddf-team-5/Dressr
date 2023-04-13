package be.odisee.kledingstuk.domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Kledingmanager")
public class Kledingmanager extends Rol {
    public Kledingmanager(){}

    public Kledingmanager(String usernaam, Persoon persoon){
        super(usernaam,persoon);
    }

    public Kledingmanager(int id, String usernaam, Persoon persoon){
        super(id,usernaam,persoon);
    }

    public Kledingmanager(String type, String usernaam) {
    }

    @Override
    public String getType(){
        return "Kledingmanager";
    }
}
