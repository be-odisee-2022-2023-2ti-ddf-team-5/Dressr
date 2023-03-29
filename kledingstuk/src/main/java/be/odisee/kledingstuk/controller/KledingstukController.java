package be.odisee.kledingstuk.controller;

import be.odisee.kledingstuk.dao.KledingstukRepository;
import be.odisee.kledingstuk.domain.Kledingstuk;
import be.odisee.kledingstuk.service.KledingstukSessieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class KledingstukController {


    @Autowired
    protected KledingstukSessieService KledingstukSessieService=null;

    @RequestMapping(value = {"/overzicht.html"},method = RequestMethod.GET)
    public String overzicht(ModelMap model){
        List<Kledingstuk> lijst= KledingstukSessieService.getAllKledingstuk();
        model.addAttribute("kledingstukken",lijst);
        return "overzicht";
    }
    @RequestMapping(value={"/kledingstuk.html"},method=RequestMethod.GET)
    public String kledingstukdetail(@RequestParam("id") Integer id, ModelMap model){
        // Optional<Persoon> is nu nodig in deze versie van Spring  boot bij een opzoeking op id
        Kledingstuk kledingstuk = KledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "kledingstuk";
    }
    @RequestMapping(value={"/verwijderd.html"},method=RequestMethod.GET)
    public String delete(@RequestParam int id) {
        KledingstukSessieService.verwijderKledingstuk(id);
        return "verwijderd";
    }
}
