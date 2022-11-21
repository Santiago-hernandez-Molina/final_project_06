package usta.sistemas.finalProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import usta.sistemas.finalProject.models.LoanDetailEntity;

public interface LoanDetailRepo extends JpaRepository<LoanDetailEntity, Long>{}
