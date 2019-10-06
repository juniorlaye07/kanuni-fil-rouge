package com.kanuniprojet.kanuni.controller;

import com.kanuniprojet.kanuni.modele.*;
import com.kanuniprojet.kanuni.repository.ComptBancaireRepository;
import com.kanuniprojet.kanuni.repository.DepotRepository;
import com.kanuniprojet.kanuni.repository.PartenaireRepository;
import com.kanuniprojet.kanuni.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/partenaire")
public class PartenaireController {

    @Autowired
    PartenaireRepository partenaireRepository;
    @Autowired
    private ComptBancaireRepository comptBancaireRepository;
    @Autowired
    private DepotRepository depotRepository;
    @Autowired
    PasswordEncoder encoder ;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/parten")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Partenaire> listeParten(){
        return partenaireRepository.findAll();
    }

    @PostMapping(value = "/addParten",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> add(@RequestBody(required = false) Partenaire partenaire){

        partenaire.getUsers().get(0).setPassword(encoder.encode(partenaire.getUsers().get(0).getPassword()));
        partenaire.getUsers().get(0).setPartenaire(partenaire);
        SimpleDateFormat formater =new SimpleDateFormat("MMyyyy-ddhh-mmss");
        Date now=new Date();
        String mat=formater.format(now);
        partenaire.getComptBancaires().get(0).setNumCompte(mat);
        partenaire.getComptBancaires().get(0).setSolde((long)0);
        partenaire.getComptBancaires().get(0).setPartenaire(partenaire);

        Partenaire partenaire1 = partenaireRepository.save(partenaire);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/partenaire/addParten")
                .buildAndExpand(partenaire1.getUsers()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Votre préstataire a bien été enregistrer!"));

    }

    @PostMapping(value = "/addCompte",consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> addCompte(@RequestBody(required = false)Partenaire comptBancaire){

      Partenaire part =  partenaireRepository.findOneByNinea(comptBancaire.getNinea());
      SimpleDateFormat formater =new SimpleDateFormat("MMyyyy-ddhh-mmss");
      Date now=new Date();

      ComptBancaire compte = new ComptBancaire();
      String numcompte=formater.format(now);
        compte.setPartenaire(part);
        compte.setNumCompte(numcompte);
        compte.setSolde((long)0);

        ComptBancaire comptBancaire1 = comptBancaireRepository.save(compte);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/partenaire/addCompte")
                .buildAndExpand(comptBancaire1.getDepots()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Le compte a été cré avec success !"));

    }

    @PostMapping(value = "/depotCompte",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Depot depotCompte(@RequestBody(required = false)PartenaireModel partenaire){

        User us = userDetailsService.getUserConnected();
        ComptBancaire  compte = comptBancaireRepository.findComptBancaireByNumCompte(partenaire.getNumCompt());
        Depot depot = new Depot();

        Long montant = partenaire.getMontant();

        depot.setMontant(montant);

        compte.setSolde(depot.getMontant()+partenaire.getMontant());

        depot.setComptBancaire(compte);

        depot.setDateDepot(new Date());

        depot.setUser(us);

        comptBancaireRepository.save(compte);

        return  depotRepository.save(depot);
    }

    @PutMapping(value ="/updateParten/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Partenaire updatePartenaire(@PathVariable long id){
        Partenaire parten  = partenaireRepository.findById(id) .orElseThrow(() -> new IllegalArgumentException("Partenaire invalide:" + id));
        if (parten.getEtat().equals("Actif")) {
            parten.setEtat("Bloquer");
            return partenaireRepository.save(parten);
        }
        else if (parten.getEtat().equals("Bloquer")){
            parten.setEtat("Actif");
            return partenaireRepository.save(parten);
        }
        return null;
    }
}
