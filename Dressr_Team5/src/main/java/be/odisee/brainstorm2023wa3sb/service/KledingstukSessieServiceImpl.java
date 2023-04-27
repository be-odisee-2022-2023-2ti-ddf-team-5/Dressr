package be.odisee.brainstorm2023wa3sb.service;

import be.odisee.brainstorm2023wa3sb.dao.*;
import be.odisee.brainstorm2023wa3sb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
@Service("kledingstuksessieservice")
public class KledingstukSessieServiceImpl implements KledingstukSessieService {

    private KledingstukRepository kledingstukRepository;
    private PersoonRepository persoonRepository;
    private RolRepository rolRepository;

    public KledingstukSessieServiceImpl(){}

    @Autowired
    public KledingstukSessieServiceImpl(KledingstukRepository kledingstukRepository){
        this.kledingstukRepository = kledingstukRepository;
    }
    @Autowired
    public void setPersoonRepository(PersoonRepository persoonRepository) {
        this.persoonRepository = persoonRepository;
    }

    @Autowired
    public void setRolRepository(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }



    public Persoon zoekPersoonMetId(int id){
        Persoon persoon = null;

        Optional<Persoon> optionalPersoon = persoonRepository.findById(id);
        if ( optionalPersoon.isPresent() ) persoon = optionalPersoon.get();
        return persoon;
    }

    public Persoon zoekPersoonMetEmailadres(String emailadres){
        return persoonRepository.findByEmailadress(emailadres);
    }


    public Rol zoekRolMetId(int id) {
        Rol rol = null;

        Optional<Rol> optionalRol = rolRepository.findById(id);
        if ( optionalRol.isPresent() ) rol = optionalRol.get();
        return rol;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Kledingstuk voegKledingstukToe(String naam, String merk, String kledingspecificaties) {

        return kledingstukRepository.save( createKledingstuk(naam, merk, kledingspecificaties) );
    }
    private Kledingstuk createKledingstuk(String naam,String merk, String kledingspecificaties) {

        return new Kledingstuk(naam,merk,kledingspecificaties); // hier status toevoegen? want ook deel van kledingstuk
    }


    @Override
    public List<Kledingstuk> getAllKledingstuk() {
        return kledingstukRepository.findAll();
    }
    @Override
    public List<Kledingstuk> getAllgereserveerd(){
        List<Kledingstuk> example = new ArrayList<>();

        for(Kledingstuk kledingstuk: kledingstukRepository.findAll())
            if(kledingstuk.isGereserveerd()){
                example.add(kledingstuk);
        }
        return example;
    }

    @Override
    public void verwijderKledingstuk(int id) {
        kledingstukRepository.deleteById(id);

    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Kledingstuk searchKeldingstukId(int id) {
        Kledingstuk kledingstuk;

        Optional<Kledingstuk> optionalKledingstuk = kledingstukRepository.findById(id);
        if (optionalKledingstuk.isPresent()) kledingstuk = optionalKledingstuk.get();
        else kledingstuk = null;
        return kledingstuk;
    }
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Kledingstuk updateKledingstukStatus(int kledingstukId, String nieuwekledingspecific) {
        Kledingstuk kledingstuk = null;
        Optional<Kledingstuk> optionalKledingstuk = kledingstukRepository.findById(kledingstukId);
        if (optionalKledingstuk.isPresent())
            kledingstuk = optionalKledingstuk.get();
        kledingstuk.setKledingspecificaties(nieuwekledingspecific);

        return kledingstukRepository.save(kledingstuk);

    }

    @Transactional
    public Kledingstuk wijzigenreservatie(int kledingstukID, boolean gereserveerd){
        Kledingstuk kledingstuk = null;
        Optional<Kledingstuk> optionalKledingstuk = kledingstukRepository.findById(kledingstukID);
        if (optionalKledingstuk.isPresent())
            kledingstuk = optionalKledingstuk.get();
        kledingstuk.setGereserveerd(gereserveerd);

        return kledingstukRepository.save(kledingstuk);
    }


}