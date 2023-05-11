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
@CrossOrigin // by default all Origins are allowed => Postman can handle it
public class KledingstukController {

    protected KledingstukSessieService kledingstukService;

    @Autowired
    public KledingstukController(KledingstukSessieService kledingstukService) {
        this.kledingstukService = kledingstukService;
    }


    // REST GET ... breng de toestand van bestaande resources van de server naar de client (lijst van objecten)
    @RequestMapping(value={""},method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Kledingstuk>> getKledingstuk(){

        List<Kledingstuk> kledingstuks = kledingstukService.getAllKledingstuk();
        if (kledingstuks != null) return ResponseEntity.ok().body(kledingstuks);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // REST GET ... breng de toestand van bestaande resource van de server naar de client (één object)
    @RequestMapping(value={"/{id:[0-9]*}"},method= RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<Kledingstuk> getKledingstukById(@PathVariable("id") int id){

        Kledingstuk kledingstuk = kledingstukService.searchKeldingstukId(id);
        if (kledingstuk != null) return ResponseEntity.ok().body(kledingstuk);
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // REST PUT ... breng de toestand van (gewijzigde) bestaande resource van de client naar de server (één object)
    @RequestMapping(value={"/{id:[0-9]*}"},method= RequestMethod.PUT)
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

    // REST DELETE ... verwijder een bestaande resource
    @RequestMapping(value={"/{id:[0-9]*}"},method= RequestMethod.DELETE)
    @ResponseBody
    public  ResponseEntity<Kledingstuk> deleteKledingstukById(@PathVariable("id") int id){

        Kledingstuk person2update = kledingstukService.searchKeldingstukId(id);
        if (person2update != null) {
            kledingstukService.verwijderKledingstuk(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // REST POST ... hiermee wordt een resource gecreëerd
    @RequestMapping(value={""},method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Kledingstuk createPerson(@RequestBody Kledingstuk kledingstuk, HttpServletResponse response)
            throws BindException {

        kledingstuk = kledingstukService.addkledingstuk(kledingstuk);
        return kledingstuk;
    }

}
