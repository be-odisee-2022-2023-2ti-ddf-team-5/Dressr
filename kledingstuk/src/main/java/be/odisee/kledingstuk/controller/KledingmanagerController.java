package be.odisee.kledingstuk.controller;

import be.odisee.kledingstuk.domain.Rol;
import be.odisee.kledingstuk.service.KledingstukSessieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PostAuthorize("#model.get('rol').persoon.emailadres == authentication.principal.username")
@Secured({"Kledingmanager"})
public class KledingmanagerController {
    @Autowired
    protected KledingstukSessieService kledingstukSessieService = null; // ready for dependency injection
    @RequestMapping(value={ "/home.html" , "/menu.html"},method= RequestMethod.GET)
    public String index(@RequestParam("rolid") Integer id, ModelMap model){
        Rol deRol = kledingstukSessieService.zoekRolMetId(id);
        model.addAttribute("rol", deRol);
        return "home";
    }

}
