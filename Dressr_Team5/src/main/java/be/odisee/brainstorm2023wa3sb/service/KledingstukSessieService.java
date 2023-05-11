package be.odisee.brainstorm2023wa3sb.service;

import be.odisee.brainstorm2023wa3sb.dao.KledingstukRepository;
import be.odisee.brainstorm2023wa3sb.domain.*;

import java.util.List;

public interface KledingstukSessieService {



    public Persoon zoekPersoonMetEmailadres(String username);


    public Kledingstuk updateKledingstukStatus(int kledingstukId, String nieuweStatus);

    public Kledingstuk wijzigenreservatie(int kledingstukID, boolean gereserveerd);

    public Rol zoekRolMetId(int id);

    public Kledingstuk voegKledingstukToe(String naam,String merk, String kledingspecificaties);

    public List<Kledingstuk> getAllKledingstuk();

    public void verwijderKledingstuk(int id);

    public Kledingstuk searchKeldingstukId(int id);

    public List<Kledingstuk> getAllgereserveerd();

    public Kledingstuk updatekledingstuk(Kledingstuk kledingstuk);

    public Kledingstuk addkledingstuk(Kledingstuk kledingstuk);
}