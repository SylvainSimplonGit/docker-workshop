package co.simplon.restaurant.service;

import co.simplon.restaurant.model.Client;
import co.simplon.restaurant.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client clientToCreate) {
        return clientRepository.save(clientToCreate);
    }
}
