package com.kanuniprojet.kanuni.modele;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = "partenaire")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min=12, max = 16)
    private String nomE;

    @NotBlank
    @Size(min=12, max = 16)
    private String prenomE;

    @NotBlank
    @Size(min=9, max = 16)
    private String telephoneE;

    @NotBlank
    @Size(min=12, max = 16)
    private String cniE;

    @NotBlank
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateTrans;

    @NotBlank
    @Size(max = 20)
    private String envoi;

    @NotBlank
    @Size(max = 20)
    private String retrait;

    @NotBlank
    @Size(min=9, max = 16)
    private String telephoneB;

    @NotBlank
    @Size(min=9, max = 16)
    private String nomB;

    @NotBlank
    @Size(min=9, max = 16)
    private String prenomB;

    @NotBlank
    @Size(min=12, max = 16)
    private String cniB;

    @NotBlank
    @Size(min=12, max = 16)
    private BigInteger montant;

    @NotBlank
    @Size(min=12, max = 16)
    private String code_trans;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;

    @JsonIgnoreProperties("transactions")
    public Transaction() {
    }
    public Transaction(String nomE, String prenomE, String cniE,String cniB,Date dateTrans,String envoi,String retrait,String nomB,String prenomB,BigInteger montant,String code_trans,String telephoneB,String telephoneE) {
        this.montant = montant;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.prenomB = prenomB;
        this.cniE= cniE;
        this.cniB = cniB;
        this.dateTrans = dateTrans;
        this.envoi= envoi;
        this.retrait= retrait;
        this.nomB= nomB;
        this.code_trans = code_trans;
        this.telephoneB = telephoneB;
        this.telephoneE = telephoneE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getPrenomE() {
        return prenomE;
    }

    public void setPrenomE(String prenomE) {
        this.prenomE = prenomE;
    }

    public String getTelephoneE() {
        return telephoneE;
    }

    public void setTelephoneE(String telephoneE) {
        this.telephoneE = telephoneE;
    }

    public String getCniE() {
        return cniE;
    }

    public void setCniE(String cniE) {
        this.cniE = cniE;
    }

    public Date getDateTrans() {
        return dateTrans;
    }

    public void setDateTrans(Date dateTrans) {
        this.dateTrans = dateTrans;
    }

    public String getEnvoi() {
        return envoi;
    }

    public void setEnvoi(String envoi) {
        this.envoi = envoi;
    }

    public String getRetrait() {
        return retrait;
    }

    public void setRetrait(String retrait) {
        this.retrait = retrait;
    }

    public String getTelephoneB() {
        return telephoneB;
    }

    public void setTelephoneB(String telephoneB) {
        this.telephoneB = telephoneB;
    }

    public String getNomB() {
        return nomB;
    }

    public void setNomB(String nomB) {
        this.nomB = nomB;
    }

    public String getPrenomB() {
        return prenomB;
    }

    public void setPrenomB(String prenomB) {
        this.prenomB = prenomB;
    }

    public String getCniB() {
        return cniB;
    }

    public void setCniB(String cniB) {
        this.cniB = cniB;
    }

    public BigInteger getMontant() {
        return montant;
    }

    public void setMontant(BigInteger montant) {
        this.montant = montant;
    }

    public String getCode_trans() {
        return code_trans;
    }

    public void setCode_trans(String code_trans) {
        this.code_trans = code_trans;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
