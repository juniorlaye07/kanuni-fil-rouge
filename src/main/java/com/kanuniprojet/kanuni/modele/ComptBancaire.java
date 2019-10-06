package com.kanuniprojet.kanuni.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "partenaire")
public class ComptBancaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numCompte;

    private Long solde;

    @JoinColumn(name = "partenaire_id", referencedColumnName = "id")
    @JsonIgnoreProperties("comptBancaire")
    @JsonIgnore
    @ManyToOne(optional = false)
    private Partenaire partenaire;

    @JsonIgnoreProperties("comptBancaire")
    @JsonIgnore
    @OneToMany(mappedBy ="comptBancaire")
    private List<Depot> depots;


    public ComptBancaire() {
    }
    public ComptBancaire(String numCompte, Long solde, Partenaire partenaire) {
        this.numCompte = numCompte;
        this.solde = solde;;
        this.partenaire = partenaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public Long getSolde() {
        return solde;
    }

    public void setSolde(Long solde) {
        this.solde = solde;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public List<Depot> getDepots() {
        return depots;
    }

    public void setDepots(List<Depot> depots) {
        this.depots = depots;
    }
}
