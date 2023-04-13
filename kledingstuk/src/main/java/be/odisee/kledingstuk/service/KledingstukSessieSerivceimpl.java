package be.odisee.kledingstuk.service;

import be.odisee.kledingstuk.dao.KledingstukRepository;
import be.odisee.kledingstuk.dao.PersoonRepository;
import be.odisee.kledingstuk.dao.RolRepository;
import be.odisee.kledingstuk.domain.Kledingstuk;
import be.odisee.kledingstuk.domain.Persoon;
import be.odisee.kledingstuk.domain.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service("kledingstuksessieservice")
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class KledingstukSessieSerivceimpl implements KledingstukSessieService {

    private KledingstukRepository kledingstukRepository;
    private PersoonRepository persoonRepository;
    private RolRepository rolRepository;


    @Autowired
    public KledingstukSessieSerivceimpl(KledingstukRepository kledingstukRepository){
        this.kledingstukRepository = kledingstukRepository;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false)
    public Kledingstuk voegKledingstukToe(String naam, String merk, String kledingspecificaties) {

        return kledingstukRepository.save( createKledingstuk(naam, merk, kledingspecificaties) );
    }
    private Kledingstuk createKledingstuk(String naam,String merk, String kledingspecificaties) {

        return new Kledingstuk(naam,merk, kledingspecificaties);
    }


    @Override
    public List<Kledingstuk> getAllKledingstuk() {
        return kledingstukRepository.findAll();
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

    public Persoon zoekPersoonMetEmailadres(String emailadres){
        return persoonRepository.findByEmailadres(emailadres);
    }

    public Rol zoekRolMetId(int id) {
        Rol rol = null;

        Optional<Rol> optionalRol = rolRepository.findById(id);
        if ( optionalRol.isPresent() ) rol = optionalRol.get();
        return rol;
    }

}
