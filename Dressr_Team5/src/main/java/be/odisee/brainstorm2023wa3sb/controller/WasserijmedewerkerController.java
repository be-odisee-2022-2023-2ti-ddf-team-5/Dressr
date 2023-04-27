package be.odisee.brainstorm2023wa3sb.controller;

import be.odisee.brainstorm2023wa3sb.domain.Kledingstuk;
import be.odisee.brainstorm2023wa3sb.domain.Rol;
import be.odisee.brainstorm2023wa3sb.service.KledingstukSessieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// om deze controller te gebruiken, moet je uw rol
@Controller
@Secured({"Wasserijmedewerker"})
public class WasserijmedewerkerController {

    @Autowired
    protected KledingstukSessieService kledingstukSessieService =null; // ready for dependency injection

    @RequestMapping(value={"/wasserijmedewerker/index.html"},method=RequestMethod.GET)
    public String index(@RequestParam("rolid") Integer id, ModelMap model){
        Rol deRol = kledingstukSessieService.zoekRolMetId(id);
        model.addAttribute("rol", deRol);
        return "/wasserijmedewerker/index";
    }
    @RequestMapping(value={"/wasserijmedewerker/home.html","/wasserijmedewerker/home.html"},method=RequestMethod.GET)
    public String home(ModelMap model){
        return "/wasserijmedewerker/home";
    }

    @RequestMapping(value = {"/wasserijmedewerker/overzicht.html"},method = RequestMethod.GET)
    public String overzicht(ModelMap model){
        List<Kledingstuk> lijst= kledingstukSessieService.getAllKledingstuk();
        model.addAttribute("kledingstukken",lijst);
        return "/wasserijmedewerker/overzicht";
    }
    @RequestMapping(value={"/wasserijmedewerker/kledingstuk.html"},method=RequestMethod.GET)
    public String kledingstukdetail(@RequestParam("id") Integer id, ModelMap model){
        // Optional<Persoon> is nu nodig in deze versie van Spring  boot bij een opzoeking op id
        Kledingstuk kledingstuk = kledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "/wasserijmedewerker/kledingstuk";
    }

    @RequestMapping(value = {"/wasserijmedewerker/updateKledingstuk.html"},method= RequestMethod.GET)
    public String formulieraanpassen(@RequestParam("id") Integer id, ModelMap model){
        Kledingstuk kledingstuk = kledingstukSessieService.searchKeldingstukId(id);
        model.addAttribute("kledingstuk",kledingstuk);
        return "/wasserijmedewerker/updateKledingstuk.html";
    }
    @RequestMapping(value = "/wasserijmedewerker/updateKledingstuk.html", method = RequestMethod.POST)
    public String  kledingstukaanpassen(@ModelAttribute("kledingstuk") Kledingstuk kledingstuk, ModelMap model) {
        Kledingstuk updatedKledingstuk = kledingstukSessieService.updateKledingstukStatus(kledingstuk.getId(), kledingstuk.getKledingspecificaties());
        return "redirect:/wasserijmedewerker/kledingstuk.html?id="+updatedKledingstuk.getId();
    }
}
