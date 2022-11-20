package usta.sistemas.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import usta.sistemas.finalProject.models.AuthorEntity;

public interface AuthorRepo extends JpaRepository<AuthorEntity, Long>{

}
