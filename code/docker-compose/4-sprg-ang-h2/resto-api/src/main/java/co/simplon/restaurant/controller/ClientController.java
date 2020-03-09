package co.simplon.restaurant.controller;

import co.simplon.restaurant.model.Client;
import co.simplon.restaurant.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client clientToCreate) {
        Client savedClient = clientService.createClient(clientToCreate);
        return ResponseEntity.ok(savedClient);
    }
}
