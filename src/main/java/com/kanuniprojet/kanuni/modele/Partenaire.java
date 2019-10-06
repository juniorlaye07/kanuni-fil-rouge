package com.kanuniprojet.kanuni.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "users")
@Table(name = "partenaires", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "ninea"
        })
})
public class Partenaire implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(nullable = false)
        private String ninea;

        @Column()
        @Size( max = 20)
        private String raisonsosial;

        @Column()
        private String adresse;

        @NotBlank
        @Size(max = 50)
        @Email
        @Column(nullable = false)
        private String email;

        @NotBlank
        @Column(length = 30)
        private String tel;

        @NotBlank
        @Column()
        private String etat;

        @OneToMany(mappedBy = "partenaire",cascade = CascadeType.ALL)
        @JsonIgnoreProperties("partenaire")
        @JsonIgnore
        private List<ComptBancaire> comptBancaires;

        @JsonIgnoreProperties("partenaire")
        @OneToMany(mappedBy ="partenaire",cascade = CascadeType.ALL)
        @JsonIgnore
        private List<User> users;
    public Partenaire() {
    }

    public Partenaire(String ninea, String raisonsosial, String adresse, String etat, String tel, String email) {
        this.ninea = ninea;
        this.raisonsosial = raisonsosial;
        this.adresse = adresse;
        this.etat = etat;
        this.tel = tel;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getRaisonsosial() {
        return raisonsosial;
    }

    public void setRaisonsosial(String raisonsosial) {
        this.raisonsosial = raisonsosial;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<ComptBancaire> getComptBancaires() {
        return comptBancaires;
    }

    public void setComptBancaires(List<ComptBancaire> comptBancaires) {
        this.comptBancaires = comptBancaires;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
