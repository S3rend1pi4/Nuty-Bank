package com.nutybank.api.Repositories;

import com.nutybank.api.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
