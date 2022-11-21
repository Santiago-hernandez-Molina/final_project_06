package usta.sistemas.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import usta.sistemas.finalProject.models.LoanEntity;

public interface LoanRepo extends JpaRepository<LoanEntity,Long>{

}
