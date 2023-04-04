package be.odisee.kledingstuk.service;

import be.odisee.kledingstuk.dao.KledingstukRepository;
import be.odisee.kledingstuk.domain.Kledingstuk;
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

}
