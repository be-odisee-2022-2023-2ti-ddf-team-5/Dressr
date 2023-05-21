package be.odisee.brainstorm2023wa3sb.controller;

import be.odisee.brainstorm2023wa3sb.domain.Kledingstuk;
import be.odisee.brainstorm2023wa3sb.service.KledingstukSessieService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value={"/api/v1/kledingstuks"})
@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
@CrossOrigin(origins={"http://localhost:8888", "chrome-extension://aejoelaoggembcahagimdiliamlcdmfm"},
        maxAge = 3600, allowCredentials = "true")
public class KledingstukController {

    protected KledingstukSessieService kledingstukService;

    @Autowired
    public KledingstukController(KledingstukSessieService kledingstukService) {
        this.kledingstukService = kledingstukService;
    }

    @RequestMapping(value={""},method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Kledingstuk>> getKledingstuk(){

        List<Kledingstuk> kledingstuks = kledingstukService.getAllKledingstuk();
        if (kledingstuks != null) return ResponseEntity.ok().body(kledingstuks);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @RequestMapping(value={"/{id:[0-9]*}"},method= RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<Kledingstuk> getKledingstukById(@PathVariable("id") int id){

        Kledingstuk kledingstuk = kledingstukService.searchKeldingstukId(id);
        if (kledingstuk != null) return ResponseEntity.ok().body(kledingstuk);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value={"/aanpassen/{id:[0-9]*}"},method= RequestMethod.PUT)
    @ResponseBody
    public  ResponseEntity<Kledingstuk> putKledingstukById(@PathVariable("id") int id, @RequestBody Kledingstuk kledingstuk){

        kledingstuk.setId(id);
        Kledingstuk kledingstuk2update = kledingstukService.searchKeldingstukId(id);
        if (kledingstuk2update != null) {
            kledingstuk = kledingstukService.updatekledingstuk(kledingstuk);
            return ResponseEntity.ok().body(kledingstuk);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value={"/verwijderen/{id:[0-9]*}"},method= RequestMethod.DELETE)
    @ResponseBody
    public  ResponseEntity<Kledingstuk> deleteKledingstukById(@PathVariable("id") int id){

        Kledingstuk person2update = kledingstukService.searchKeldingstukId(id);
        if (person2update != null) {
            kledingstukService.verwijderKledingstuk(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @RequestMapping(value={"/createkledingstuk"},method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Kledingstuk createKledingstuk(@RequestBody Kledingstuk kledingstuk, HttpServletResponse response)
            throws BindException {

        kledingstuk = kledingstukService.addkledingstuk(kledingstuk);
        return kledingstuk;
    }

}
