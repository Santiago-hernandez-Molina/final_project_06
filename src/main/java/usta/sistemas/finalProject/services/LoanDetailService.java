package usta.sistemas.finalProject.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.LowerCase;
import org.hibernate.query.criteria.internal.expression.function.LowerFunction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import usta.sistemas.finalProject.dtos.loanDetailDtos.loanDetailDTO;
import usta.sistemas.finalProject.models.LoanDetailEntity;
import usta.sistemas.finalProject.repositories.BookRepo;
import usta.sistemas.finalProject.repositories.LoanDetailRepo;
import usta.sistemas.finalProject.repositories.LoanRepo;

@Service
@RequiredArgsConstructor
public class LoanDetailService{
	private final LoanDetailRepo loanDetailRepo;
	private final BookRepo bookRepo;
	private final LoanRepo loanRepo;

	public List<LoanDetailEntity> findAll() {
		return loanDetailRepo.findAll();
	}

	public Optional<LoanDetailEntity> findById(Long id) {
		return loanDetailRepo.findById(id);
	}


	public void deleteById(Long id){
		loanDetailRepo.deleteById(id);
	}

	public LoanDetailEntity create(loanDetailDTO loanDetailEntity){
		try{
			LoanDetailEntity loanDetail = new LoanDetailEntity();
			loanDetail.setRefundDate(loanDetailEntity.getRefundDate());
			loanDetail.setDaysPastDue(loanDetailEntity.getDaysPastDue());
			loanDetail.setDescription(loanDetailEntity.getDescription());
			loanDetail.setDeliveryDate(loanDetailEntity.getDeliveryDate());
			loanDetail.setBook(bookRepo.findById(loanDetailEntity.getBookId())
					.orElseThrow());
			loanDetail.setLoan(loanRepo.findById(loanDetailEntity.getLoanId())
					.orElseThrow());
			return loanDetailRepo.save(loanDetail);
		}catch(Error e){
			return null;
		}
	}
	public LoanDetailEntity edit(LoanDetailEntity loanDetailEntity){
		try{
			if(findById(loanDetailEntity.getId())!=null){
				return loanDetailRepo.save(loanDetailEntity);
			}else{
				return null;
			}
		}catch(Error e){
			System.err.println("something wents wrong");
			return null;
		}
	}
	public long count(){
		return loanDetailRepo.count();
	}	
}

