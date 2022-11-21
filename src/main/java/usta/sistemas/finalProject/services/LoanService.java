package usta.sistemas.finalProject.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.LowerCase;
import org.hibernate.query.criteria.internal.expression.function.LowerFunction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import usta.sistemas.finalProject.models.LoanEntity;
import usta.sistemas.finalProject.repositories.LoanRepo;

@Service
@RequiredArgsConstructor
public class LoanService{
	private final LoanRepo loanRepo;

	public List<LoanEntity> findAll() {
		return loanRepo.findAll();
	}

	public Optional<LoanEntity> findById(Long id) {
		return loanRepo.findById(id);
	}


	public void deleteById(Long id){
		loanRepo.deleteById(id);
	}

	public LoanEntity create(LoanEntity loanEntity){
		try{
			return loanRepo.save(loanEntity);
		}catch(Error e){
			return null;
		}
	}
	public LoanEntity edit(LoanEntity loanEntity){
		try{
			if(findById(loanEntity.getId())!=null){
				return loanRepo.save(loanEntity);
			}else{
				return null;
			}
		}catch(Error e){
			System.err.println("something wents wrong");
			return null;
		}
	}
}

