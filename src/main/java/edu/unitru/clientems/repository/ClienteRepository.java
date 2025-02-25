package edu.unitru.clientems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.unitru.clientems.repository.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
