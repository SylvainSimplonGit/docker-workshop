package co.simplon.restaurant.repository;

import co.simplon.restaurant.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
