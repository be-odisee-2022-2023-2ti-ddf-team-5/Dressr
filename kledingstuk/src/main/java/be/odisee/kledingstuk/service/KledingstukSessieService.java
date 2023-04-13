package be.odisee.kledingstuk.service;

import be.odisee.kledingstuk.domain.Kledingstuk;
import be.odisee.kledingstuk.domain.Persoon;
import be.odisee.kledingstuk.domain.Rol;

import java.util.List;

public interface KledingstukSessieService {

    public Kledingstuk voegKledingstukToe(String naam,String merk, String kledingspecificaties);

    public List<Kledingstuk> getAllKledingstuk();

    public void verwijderKledingstuk(int id);

    public Kledingstuk searchKeldingstukId(int id);

    Persoon zoekPersoonMetEmailadres(String usernaam);
    public Rol zoekRolMetId(int id);
}
