package usta.sistemas.finalProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import usta.sistemas.finalProject.models.EditorialEntity;

public interface EditorialRepo extends JpaRepository<EditorialEntity, Long>{
	List<EditorialEntity> findAllByBooksId(Long id);
}
