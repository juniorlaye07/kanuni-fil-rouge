package com.kanuniprojet.kanuni.controller;

import com.kanuniprojet.kanuni.modele.ApiResponse;
import com.kanuniprojet.kanuni.modele.Partenaire;
import com.kanuniprojet.kanuni.modele.User;
import com.kanuniprojet.kanuni.repository.UserRepository;
import com.kanuniprojet.kanuni.services.UserDetailsServiceImpl;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;



@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping(value = "/listeUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> listeUser() {
        User user = userDetailsService.getUserConnected();
     List<User>  userList = user.getPartenaire().getUsers();
        if (user.getRoles().equals("ROLE_ADMIN")) {
            return userList;
        }
        return userRepository.findAll();
    }

    @PostMapping(value = "/form",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> addUser(@RequestBody(required = false) User u) {

        u.setPassword(encoder.encode(u.getPassword()));
        User user = userDetailsService.getUserConnected();
        u.setPartenaire(user.getPartenaire());

        User user1 = userRepository.save(u);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/form")
                .buildAndExpand(user1.getDepots()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Utilisateur enregistré avec success"));
    }

    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User updateUser(@PathVariable long id){
        User user  = userRepository.findById(id) .orElseThrow(() -> new IllegalArgumentException("User invalide:" + id));
        if (user.getStatus().equals("Actif")) {
           user.setStatus("Bloquer");
           return  userRepository.save(user);
        }
        else if (user.getStatus().equals("Bloquer")){
           user.setStatus("Actif");
          return userRepository.save(user);
        }
        return null;
    }
}
