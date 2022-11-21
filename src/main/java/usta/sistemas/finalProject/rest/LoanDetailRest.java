package usta.sistemas.finalProject.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import usta.sistemas.finalProject.dtos.loanDetailDtos.loanDetailDTO;
import usta.sistemas.finalProject.models.LoanDetailEntity;
import usta.sistemas.finalProject.services.LoanDetailService;

@RestController
@RequestMapping("api/loanDetail")
@RequiredArgsConstructor
public class LoanDetailRest{
	private final LoanDetailService loanDetailService;

	@GetMapping("list")
	public ResponseEntity<List<LoanDetailEntity>> getAllCategories(){
		return ResponseEntity.ok(loanDetailService.findAll());
	}
	@GetMapping("list/{id}")
	private ResponseEntity<Optional<LoanDetailEntity>> listById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(loanDetailService.findById(id));
	}
	@PostMapping("save")
	private ResponseEntity<LoanDetailEntity> save(@RequestBody loanDetailDTO loanDetailEntity) {
		try{
			return ResponseEntity.status(HttpStatus.CREATED).body(loanDetailService.create(loanDetailEntity));
		}catch(Error e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PutMapping("edit")
	private ResponseEntity<LoanDetailEntity> edit(@RequestBody LoanDetailEntity loanDetailEntity) {
		LoanDetailEntity temporal = loanDetailService.edit(loanDetailEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(temporal);
	}

	@DeleteMapping("delete/{id}")
	private ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			loanDetailService.deleteById(id);
			return ResponseEntity.ok("successfully removed");
		} catch (DataIntegrityViolationException de) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can´t remove this foreign key because it have been already assigned.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id doesn't match with any entity");
		}
	}
	@GetMapping("count")
	private ResponseEntity<Long> count(){
		return ResponseEntity.status(HttpStatus.OK).body(loanDetailService.count());
	}

}
