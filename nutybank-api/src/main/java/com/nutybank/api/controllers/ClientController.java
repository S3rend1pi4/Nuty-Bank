package com.nutybank.api.controllers;

import com.nutybank.api.dto.ClientDto;
import com.nutybank.api.entities.Client;
import com.nutybank.api.services.client.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> listAll() {
        return clientService.findAll();
    }

    @GetMapping("/client/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Client> clientOptional = clientService.findById(id);
        if(clientOptional.isPresent()) {
            return ResponseEntity.ok(clientOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/client/dni/{dni}")
    public ResponseEntity<?> findByDni(@PathVariable String dni) {
        Optional<Client> clientOptional = clientService.findByDni(dni);
        if(clientOptional.isPresent()) {
            return ResponseEntity.ok(clientOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("client/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        Optional<Client> clientOptional = clientService.findByUserName(name);
        if(clientOptional.isPresent()) {
            return ResponseEntity.ok(clientOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Client client, BindingResult result) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClientDto client, BindingResult result, @PathVariable Long id) {
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<ClientDto> clientOptional = clientService.update(id, client);
        if(clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(clientOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
