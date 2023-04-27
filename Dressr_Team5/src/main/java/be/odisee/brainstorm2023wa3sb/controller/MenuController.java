package be.odisee.brainstorm2023wa3sb.controller;

import be.odisee.brainstorm2023wa3sb.domain.Kledingstuk;
import be.odisee.brainstorm2023wa3sb.domain.Persoon;
import be.odisee.brainstorm2023wa3sb.domain.Rol;
import be.odisee.brainstorm2023wa3sb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    protected KledingstukSessieService kledingstukSessieService =null; // ready for dependency injection
    
    @Autowired
    protected UserContextService userContextService=null;

    @RequestMapping(value={"/login","/login.html"},method=RequestMethod.GET)
    public String login(ModelMap model){
    	return "/login";
    }
    // je zal naar login.html gaan

    @RequestMapping(value={"/","/home","/home.html"},method=RequestMethod.GET)
    public String home(ModelMap model){
        return "/home";
    }
    // je zal naar home.html gaan

    @RequestMapping(value={"/logout","/logoutSuccess.html"},method=RequestMethod.GET)
    public String logout(ModelMap model){
    	return "/logoutSuccess";
    }
    // je zal naar logoutSuccess.html gaan

    @RequestMapping(value={"/accessDenied","/accessDenied.html", "403.html"},method=RequestMethod.GET)
    public String accessDenied(ModelMap model){
    	return "/403";
    }
    // je zal naar accessDenied.html gaan

    @RequestMapping(value={"/menu.html","/index.html"},method=RequestMethod.GET)
    public String menu(ModelMap model){
        Persoon dePersoon=null;
        dePersoon = userContextService.getAuthenticatedPersoon();
        model.addAttribute("persoon",dePersoon);
        return "/menu";
    }
    // je zal naar menu.html gaan

    @RequestMapping(value={"/rol.html"},method=RequestMethod.GET)
    @PostAuthorize("#model.get('rol').persoon.emailadres == authentication.principal.username")
    public String indexVoorRol(@RequestParam("id") Integer id, ModelMap model){
        Rol deRol = kledingstukSessieService.zoekRolMetId(id);
        model.addAttribute("rol",deRol);
        if (deRol.getType().equals("Kledingmanager")) return "redirect:/kledingmanager/home.html?rolid="+deRol.getId();
        if (deRol.getType().equals("Wasserijmedewerker")) return "redirect:/wasserijmedewerker/home.html?rolid="+deRol.getId();
        return "redirect:/klant/index.html?rolid="+deRol.getId(); // voor de andere rollen
    }
    // je zal gaan naar de pagina conform uw rol

}
