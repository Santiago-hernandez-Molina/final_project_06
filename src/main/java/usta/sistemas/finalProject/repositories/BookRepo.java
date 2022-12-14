package usta.sistemas.finalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import usta.sistemas.finalProject.models.AuthorEntity;
import usta.sistemas.finalProject.models.BookEntity;


public interface BookRepo extends JpaRepository<BookEntity, Long>{
	List<AuthorEntity> findBooksByAuthorsId(Long id);

	Optional<BookEntity> findBookByLoanDetailEntitiesId(Long id);

}
