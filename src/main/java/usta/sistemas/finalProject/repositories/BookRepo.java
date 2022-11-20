package usta.sistemas.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import usta.sistemas.finalProject.models.BookEntity;


public interface BookRepo extends JpaRepository<BookEntity, Long>{

}
