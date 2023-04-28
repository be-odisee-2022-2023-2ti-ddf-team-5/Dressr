package be.odisee.brainstorm2023wa3sb.controller;

import be.odisee.brainstorm2023wa3sb.domain.*;
import be.odisee.brainstorm2023wa3sb.service.KledingstukSessieService;
import be.odisee.brainstorm2023wa3sb.service.UserContextService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// om deze controller te gebruiken, moet je uw rol gebruiken Kledingmanager
@Controller
@Secured({"Kledingmanager"})
public class KledingmanagerController {

    @Autowired
    protected KledingstukSessieService kledingstukSessieService =null; // ready for dependency injection

    @Autowired
    protected UserContextService userContextService=null;

    @RequestMapping(value={"/kledingmanager/index.html"},method=RequestMethod.GET)
    public String index(@RequestParam("rolid") Integer id, ModelMap model){
        Persoon dePersoon = userContextService.getAuthenticatedPersoon();
        Rol deRol = kledingstukSessieService.zoekRolMetId(id);
        model.addAttribute("rol", deRol);
        return "/kledingmanager/index";
    }


    @RequestMapping(value = {"/kledingmanager/overzicht.html"},method = RequestMethod.GET)
    public String overzicht(ModelMap model){
        List<Kledingstuk> lijst= kledingstukSessieService.getAllKledingstuk();
        model.addAttribute("kledingstukken",lijst);
        return "/kledingmanager/overzicht";
    }
    @RequestMapping(value={"/kledingmanager/kledingstuk.html"},method=RequestMethod.GET)
    public String kledingstukdetail(@RequestParam("id") Integer id, ModelMap model){
        // Optional<Persoon> is nu nodig in deze versie van Spring  boot bij een opzoeking op id
        Kledingstuk kledingstuk = kledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "/kledingmanager/kledingstuk";
    }
    @RequestMapping(value={"/kledingmanager/verwijderd.html"},method=RequestMethod.GET)
    public String delete(@RequestParam int id) {
        kledingstukSessieService.verwijderKledingstuk(id);
        return "/kledingmanager/verwijderd";
    }
// je zal naar persoon.jsp gaan

    @RequestMapping(value={"/kledingmanager/nieuweKledingstuk.html"},method=RequestMethod.GET)
    public String kledingstukFormulier(ModelMap model){
        Kledingstuk kledingstuk = new Kledingstuk();
        model.addAttribute("hetkledingstuk", kledingstuk);

        return "/kledingmanager/nieuweKledingstuk";
    }

    // hier gaan we naar nieuweKledingstuk.html

    @RequestMapping(value={"/kledingmanager/nieuweKledingstuk.html"},method=RequestMethod.POST)
    public String kledingstukToevoegen(@ModelAttribute("hetkledingstuk") @Valid Kledingstuk kledingstuk, BindingResult result, ModelMap model){

        if (result.hasErrors()) return "/kledingmanager/nieuweKledingstuk.html";  // fouten op de form => form opnieuw tonen


        Kledingstuk toegevoegdKledingstuk = kledingstukSessieService.voegKledingstukToe(kledingstuk.getNaam(),kledingstuk.getMerk(), kledingstuk.getKledingspecificaties());
        System.out.println("DEBUG kledingsgegevens Kledingspecificaties: "+kledingstuk.getKledingspecificaties());
        return "redirect:/kledingmanager/kledingstuk.html?id="+toegevoegdKledingstuk.getId();
    }

    @RequestMapping(value={"/kledingmanager/home","/kledingmanager/home.html"},method=RequestMethod.GET)
    public String home(ModelMap model){
        return "/kledingmanager/home";
    }
    // je zal naar home.html gaan

    @RequestMapping(value = {"/kledingmanager/updateKledingstuk.html"},method= RequestMethod.GET)
    public String formulieraanpassen(@RequestParam("id") Integer id, ModelMap model){
        Kledingstuk kledingstuk = kledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "/kledingmanager/updateKledingstuk.html";
    }
    @RequestMapping(value = "/kledingmanager/updateKledingstuk.html", method = RequestMethod.POST)
    public String  kledingstukaanpassen(@ModelAttribute ("kledingstuk") Kledingstuk kledingstuk, ModelMap model) {
        Kledingstuk updatedKledingstuk = kledingstukSessieService.updateKledingstukStatus(kledingstuk.getId(), kledingstuk.getKledingspecificaties());
        return "redirect:/kledingmanager/kledingstuk.html?id="+updatedKledingstuk.getId();
    }
    @RequestMapping(value = {"/kledingmanager/wijzigenreservatie.html"},method= RequestMethod.GET)
    public String reservatieopenen(@RequestParam("id") Integer id, ModelMap model){
        Kledingstuk kledingstuk = kledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "/kledingmanager/wijzigenreservatie.html";
    }
    @RequestMapping(value = "/kledingmanager/wijzigenreservatie.html", method = RequestMethod.POST)
    public String  reservatieaanpassen(@ModelAttribute ("kledingstuk") Kledingstuk kledingstuk, ModelMap model) {
        Kledingstuk updatedKledingstuk = kledingstukSessieService.wijzigenreservatie(kledingstuk.getId(),kledingstuk.isGereserveerd());
        return "redirect:/kledingmanager/kledingstuk.html?id="+updatedKledingstuk.getId();
    }
    @RequestMapping(value = {"/kledingmanager/overzichtgereserveerd.html"},method = RequestMethod.GET)
    public String overzichtgerserveerd(ModelMap model){
        List<Kledingstuk> lijst= kledingstukSessieService.getAllgereserveerd();
        model.addAttribute("kledingstukken",lijst);
        return "/kledingmanager/overzichtgereserveerd";
    }
}
