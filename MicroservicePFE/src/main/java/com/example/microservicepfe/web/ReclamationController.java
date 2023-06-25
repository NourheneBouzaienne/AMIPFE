package com.example.microservicepfe.web;


import com.example.microservicepfe.dao.DemandeRepository;
import com.example.microservicepfe.dao.NotificationRepository;
import com.example.microservicepfe.dao.UserRepository;
import com.example.microservicepfe.models.Demande;
import com.example.microservicepfe.models.Notification;
import com.example.microservicepfe.models.User;
import com.example.microservicepfe.payload.request.EmailRequest;
import com.example.microservicepfe.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/reclammation")
public class ReclamationController {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender javaMailSender;



    @CrossOrigin(origins = "*")
    @GetMapping("/reclammations")
    public List<Demande> getReclammations (@AuthenticationPrincipal UserDetailsImpl userDetails) {
       Long userId = userDetails.getId(); // Récupération de l'ID de l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId)); // Récupération de l'utilisateur à partir de l'ID
        return demandeRepository.findAll(); // Récupération des demandes
    }

    @GetMapping("/{reclamationId}")
    public ResponseEntity<Demande> getReclamationById(@PathVariable Long reclamationId) {
        Optional<Demande> demandeOptional = demandeRepository.findById(reclamationId);
        if (demandeOptional.isPresent()) {
            Demande demande = demandeOptional.get();
            return new ResponseEntity<>(demande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PutMapping("/{reclamationId}")
    public ResponseEntity<Demande> updateEtatReclamation(@PathVariable Long reclamationId, @RequestBody Demande updatedDemande) {
        Optional<Demande> demandeOptional = demandeRepository.findById(reclamationId);
        if (demandeOptional.isPresent()) {
            Demande existingDemande = demandeOptional.get();

            // Mettre à jour les propriétés de la demande existante avec les nouvelles valeurs
            existingDemande.setEtat(updatedDemande.getEtat());

            Demande updatedReclamation = demandeRepository.save(existingDemande);

            return new ResponseEntity<>(updatedReclamation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/{id}/repondre")
    public void repondreReclamation(@PathVariable("id") Long reclamationId, @RequestBody String message) throws Exception {
        Demande reclamation = demandeRepository.findById(reclamationId)
                .orElseThrow(() -> new Exception("Réclamation non trouvée : " + reclamationId));

        // Enregistrer la réponse dans la base de données
        reclamation.setReponse(message);
        demandeRepository.save(reclamation);

        // Récupérer l'utilisateur associé à la réclamation
        User user = reclamation.getUser();

        // Créer une nouvelle instance de notification avec l'utilisateur et le contenu de la réponse
        Notification notification = new Notification();
        notification.setContenu(message);
        notification.setUser(user);

        // Enregistrer la notification dans la base de données
        notificationRepository.save(notification);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getEmail());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getContent());

        javaMailSender.send(message);
    }


}
