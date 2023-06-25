package com.example.microservicepfe.web;

import com.example.microservicepfe.dao.RoleRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.*;
import com.example.microservicepfe.payload.request.LoginRequest;
import com.example.microservicepfe.payload.request.SignupRequest;
import com.example.microservicepfe.payload.response.JwtResponse;
import com.example.microservicepfe.payload.response.MessageResponse;
import com.example.microservicepfe.security.jwt.JwtUtils;
import com.example.microservicepfe.security.services.EmailService;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    EmailService emailService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.isAuthentificated(),
                    roles));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Veuillez vérifier votre identifiant et /ou mot de passe");
        }
    }


    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/signupClient")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String activationCode = UUID.randomUUID().toString();
        // Create new user's account
        User user = new Client(
                signUpRequest.getActivationCode(),
                signUpRequest.isEnabled(),
                signUpRequest.isAuthentificated(),
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getAdresse(),
                signUpRequest.getTypeIDNT(),
                signUpRequest.getTypePers(),
                signUpRequest.getNumTel(),
                encoder.encode(signUpRequest.getPassword())
                );

        user.setEnabled(false);
        user.setAuthentificated(false);
        user.setActivationCode(activationCode);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "gestionnaire":
                        Role gestionnaireRole = roleRepository.findByName(ERole.ROLE_GESTIONNAIRE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(gestionnaireRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        // Envoyez un e-mail d'activation au nouvel utilisateur
       // String activationLink = "http://localhost:8060/activate/" + activationCode;
        //Envoyer un code d'activation
        String activationLink =  activationCode;

        emailService.sendActivationEmail(user.getEmail(), activationLink);

        return ResponseEntity.ok("Un e-mail d'activation a été envoyé à votre adresse e-mail.");
    }
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Vérifiez si l'e-mail est déjà utilisé
       // User existingUser = userRepository.findByEmail(user.getEmail());
        //if (existingUser != null) {
            //return ResponseEntity.badRequest().body("L'e-mail est déjà utilisé.");
        //}

        // Créez un nouveau compte d'utilisateur avec un code d'activation généré
        user.setEnabled(false);
        // Générez un code d'activation unique, par exemple en utilisant UUID.randomUUID().toString()
        String activationCode = UUID.randomUUID().toString();
        user.setActivationCode(activationCode);
        userRepository.save(user);

        // Envoyez un e-mail d'activation au nouvel utilisateur
        String activationLink = "http://localhost:8060/activate/" + activationCode;
        emailService.sendActivationEmail(user.getEmail(), activationLink);

        return ResponseEntity.ok("Un e-mail d'activation a été envoyé à votre adresse e-mail.");
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestParam("activationCode") String activationCode) {
        // Recherchez l'utilisateur en utilisant le code d'activation
        User user = userRepository.findByActivationCode(activationCode);

        // Vérifiez si l'utilisateur existe et si son compte est déjà activé
        if (user == null) {
            return ResponseEntity.badRequest().body("Code d'activation invalide.");
        }

        if (user.isEnabled()) {
            return ResponseEntity.badRequest().body("Ce compte a déjà été activé.");
        }

        // Activez le compte de l'utilisateur
        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok("Votre compte a été activé avec succès. Vous pouvez maintenant vous connecter.");
    }

    // Méthode pour envoyer un e-mail d'activation


    @PostMapping("/signinGest")
    public ResponseEntity<?> authenticateGestionnaire(@Validated @RequestBody LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.isAuthentificated(),
                    roles));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Veuillez vérifier votre identifiant et / ou mot de passe");
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/registerGest")
    public ResponseEntity<?> registerGestionnaire(@Validated @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        String activationCode = UUID.randomUUID().toString();
        // Create new user's account
        User user = new Gestionnaire(
                signUpRequest.getActivationCode(),
                signUpRequest.isEnabled(),
                signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );

        user.setEnabled(false);
        user.setAuthentificated(false);
        user.setActivationCode(activationCode);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "gestionnaire":
                        Role gestionnaireRole = roleRepository.findByName(ERole.ROLE_GESTIONNAIRE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(gestionnaireRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        // Envoyez un e-mail d'activation au nouvel utilisateur
        // String activationLink = "http://localhost:8060/activate/" + activationCode;
        //Envoyer un code d'activation
        String activationLink =  activationCode;

        emailService.sendActivationEmail(user.getEmail(), activationLink);

        return ResponseEntity.ok("Un e-mail d'activation a été envoyé à votre adresse e-mail.");
    }

}
