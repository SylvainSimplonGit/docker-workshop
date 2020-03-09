package co.simplon.restaurant.service;

import co.simplon.restaurant.model.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {

    Client createClient(Client clientToCreate);

}
