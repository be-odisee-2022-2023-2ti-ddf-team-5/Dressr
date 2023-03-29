package be.odisee.kledingstuk.service;

import be.odisee.kledingstuk.domain.Kledingstuk;

import java.util.List;

public interface KledingstukSessieService {

    public Kledingstuk voegKledingstukToe(String naam, String kledingspecificaties);

    public List<Kledingstuk> getAllKledingstuk();

    public void verwijderKledingstuk(int id);

    public Kledingstuk searchKeldingstukId(int id);
}
