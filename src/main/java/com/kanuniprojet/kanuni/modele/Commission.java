package com.kanuniprojet.kanuni.modele;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 160000000)
    private BigInteger etat;

    @NotBlank
    @Size(max = 160000000)
    private BigInteger partenaire;

    @NotBlank
    @Size(max = 160000000)
    private BigInteger envoie;

    @NotBlank
    @Size(max = 160000000)
    private BigInteger retrait;

    @NotBlank
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    public Commission() {
    }
    public Commission(BigInteger etat, BigInteger partenaire,BigInteger retrait,BigInteger envoie,Date date ) {
        this.etat = etat;
        this.partenaire = partenaire;
        this.retrait = retrait;
        this.envoie = envoie;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getEtat() {
        return etat;
    }

    public void setEtat(BigInteger etat) {
        this.etat = etat;
    }

    public BigInteger getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(BigInteger partenaire) {
        this.partenaire = partenaire;
    }

    public BigInteger getEnvoie() {
        return envoie;
    }

    public void setEnvoie(BigInteger envoie) {
        this.envoie = envoie;
    }

    public BigInteger getRetrait() {
        return retrait;
    }

    public void setRetrait(BigInteger retrait) {
        this.retrait = retrait;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
