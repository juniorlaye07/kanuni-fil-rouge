package com.kanuniprojet.kanuni.modele;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Entity
@Data
public class Tarifs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 160000000)
    private BigInteger borneinfo;

    @NotBlank
    @Size(max = 160000000)
    private BigInteger bornesup;

    @NotBlank
    @Size(max = 160000000)
    private BigInteger valeur;
    public Tarifs() {
    }
    public Tarifs(BigInteger borneinfo, BigInteger bornesup,BigInteger valeur ) {
        this.borneinfo = borneinfo;
        this.borneinfo = borneinfo;
        this.valeur = valeur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getBorneinfo() {
        return borneinfo;
    }

    public void setBorneinfo(BigInteger borneinfo) {
        this.borneinfo = borneinfo;
    }

    public BigInteger getBornesup() {
        return bornesup;
    }

    public void setBornesup(BigInteger bornesup) {
        this.bornesup = bornesup;
    }

    public BigInteger getValeur() {
        return valeur;
    }

    public void setValeur(BigInteger valeur) {
        this.valeur = valeur;
    }
}
