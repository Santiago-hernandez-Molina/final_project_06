package usta.sistemas.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import usta.sistemas.finalProject.models.AuthorEntity;

public interface AuthorRepo extends JpaRepository<AuthorEntity, Long>{

}
