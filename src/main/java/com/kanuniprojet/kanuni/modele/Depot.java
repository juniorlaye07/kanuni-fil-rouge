package com.kanuniprojet.kanuni.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = "comptBancaire")
public class Depot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long montant;

    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateDepot;

    @JsonIgnoreProperties("depots")
    @JsonIgnore
    @JoinColumn(name = "numCompt_id", referencedColumnName = "id")
    @ManyToOne( optional = false)
    private ComptBancaire comptBancaire;

    @JsonIgnoreProperties("depots")
    @JsonIgnore
    @JoinColumn(name = "caissier_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;


    public Depot() {
    }
    public Depot(Long montant, Date dateDepot, User user,ComptBancaire comptBancaire ) {
        this.montant = montant;
        this.dateDepot = dateDepot;
        this.user = user;
        this.comptBancaire = comptBancaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMontant() {
        return montant;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    public ComptBancaire getComptBancaire() {
        return comptBancaire;
    }

    public void setComptBancaire(ComptBancaire comptBancaire) {
        this.comptBancaire = comptBancaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
