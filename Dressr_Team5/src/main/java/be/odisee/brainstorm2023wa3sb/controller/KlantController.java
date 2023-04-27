package be.odisee.brainstorm2023wa3sb.controller;

import be.odisee.brainstorm2023wa3sb.domain.Kledingstuk;
import be.odisee.brainstorm2023wa3sb.domain.Persoon;
import be.odisee.brainstorm2023wa3sb.domain.Rol;
import be.odisee.brainstorm2023wa3sb.service.KledingstukSessieService;
import be.odisee.brainstorm2023wa3sb.service.UserContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import java.util.List;

@Controller
public class KlantController {

    @Autowired
    protected KledingstukSessieService kledingstukSessieService =null; // ready for dependency injection

    @Autowired
    protected UserContextService userContextService=null;

    @RequestMapping(value={"/klant/index.html"},method=RequestMethod.GET)
    public String index(@RequestParam("rolid") Integer id, ModelMap model){
        Persoon dePersoon = userContextService.getAuthenticatedPersoon();
        Rol deRol = kledingstukSessieService.zoekRolMetId(id);
        model.addAttribute("rol", deRol);
        return "/klant/index";
    }

    @RequestMapping(value={"/klant/home","/klant/home.html"},method=RequestMethod.GET)
    public String home(ModelMap model){
        return "/klant/home";
    }
    // je zal naar home.html gaan

    @RequestMapping(value = {"/klant/overzicht.html"},method = RequestMethod.GET)
    public String overzicht(ModelMap model){
        List<Kledingstuk> lijst= kledingstukSessieService.getAllKledingstuk();
        model.addAttribute("kledingstukken",lijst);
        return "/klant/overzicht";
    }
    @RequestMapping(value={"/klant/kledingstuk.html"},method=RequestMethod.GET)
    public String kledingstukdetail(@RequestParam("id") Integer id, ModelMap model){
        // Optional<Persoon> is nu nodig in deze versie van Spring  boot bij een opzoeking op id
        Kledingstuk kledingstuk = kledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "/klant/kledingstuk";
    }

    @RequestMapping(value = {"/klant/wijzigenreservatie.html"},method= RequestMethod.GET)
    public String reservatieopenen(@RequestParam("id") Integer id, ModelMap model){
        Kledingstuk kledingstuk = kledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "/klant/wijzigenreservatie.html";
    }
    @RequestMapping(value = "/klant/wijzigenreservatie.html", method = RequestMethod.POST)
    public String  reservatieaanpassen(@ModelAttribute ("kledingstuk") Kledingstuk kledingstuk, ModelMap model) {
        Kledingstuk updatedKledingstuk = kledingstukSessieService.wijzigenreservatie(kledingstuk.getId(),kledingstuk.isGereserveerd());
        return "redirect:/klant/kledingstuk.html?id="+updatedKledingstuk.getId();
    }
}
