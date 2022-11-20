package usta.sistemas.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usta.sistemas.finalProject.models.ClientEntity;


public interface ClientRepo extends JpaRepository<ClientEntity,Long >{

}
