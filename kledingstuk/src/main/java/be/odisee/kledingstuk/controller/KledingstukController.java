package be.odisee.kledingstuk.controller;

import be.odisee.kledingstuk.domain.Kledingstuk;
import be.odisee.kledingstuk.service.KledingstukSessieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class KledingstukController {


    @Autowired
    protected KledingstukSessieService kledingstukSessieService =null;

    @RequestMapping(value = {"/overzicht.html"},method = RequestMethod.GET)
    public String overzicht(ModelMap model){
        List<Kledingstuk> lijst= kledingstukSessieService.getAllKledingstuk();
        model.addAttribute("kledingstukken",lijst);
        return "overzicht";
    }
    @RequestMapping(value={"/kledingstuk.html"},method=RequestMethod.GET)
    public String kledingstukdetail(@RequestParam("id") Integer id, ModelMap model){
        // Optional<Persoon> is nu nodig in deze versie van Spring  boot bij een opzoeking op id
        Kledingstuk kledingstuk = kledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "kledingstuk";
    }
    @RequestMapping(value={"/verwijderd.html"},method=RequestMethod.GET)
    public String delete(@RequestParam int id) {
        kledingstukSessieService.verwijderKledingstuk(id);
        return "verwijderd";
    }
// je zal naar persoon.jsp gaan

    @RequestMapping(value={"/nieuweKledingstuk.html"},method=RequestMethod.GET)
    public String kledingstukFormulier(ModelMap model){
        Kledingstuk kledingstuk = new Kledingstuk();
        model.addAttribute("hetkledingstuk", kledingstuk);

        return "nieuweKledingstuk";
    }

    // hier gaan we naar nieuweKledingstuk.html

    @RequestMapping(value={"/nieuweKledingstuk.html"},method=RequestMethod.POST)
    public String kledingstukToevoegen(@ModelAttribute("hetkledingstuk") Kledingstuk kledingstuk, ModelMap model){
        Kledingstuk toegevoegdKledingstuk = kledingstukSessieService.voegKledingstukToe(kledingstuk.getNaam(),kledingstuk.getMerk(), kledingstuk.getKledingspecificaties());
        System.out.println("DEBUG kledingsgegevens Kledingspecificaties: "+kledingstuk.getKledingspecificaties());
        return "redirect:kledingstuk.html?id="+toegevoegdKledingstuk.getId();
    }
}
