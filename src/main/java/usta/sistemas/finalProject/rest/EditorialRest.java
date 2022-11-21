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
import usta.sistemas.finalProject.models.EditorialEntity;
import usta.sistemas.finalProject.services.EditorialService;

@RestController
@RequestMapping("api/editorial")
@RequiredArgsConstructor
public class EditorialRest{
	private final EditorialService editorialService;

	@GetMapping("queryOne/{id}")
	public ResponseEntity<List<EditorialEntity>> getAllByBook(@PathVariable Long id) {
		return ResponseEntity.ok(editorialService.editorialByBookId(id));
	}
	@GetMapping("list")
	public ResponseEntity<List<EditorialEntity>> getAllCategories(){
		return ResponseEntity.ok(editorialService.findAll());
	}
	@GetMapping("list/{id}")
	private ResponseEntity<Optional<EditorialEntity>> listById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(editorialService.findById(id));
	}
	@PostMapping("save")
	private ResponseEntity<EditorialEntity> save(@RequestBody EditorialEntity editorialEntity) {
		try{
			return ResponseEntity.status(HttpStatus.CREATED).body(editorialService.create(editorialEntity));
		}catch(Error e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@PutMapping("edit")
	private ResponseEntity<EditorialEntity> edit(@RequestBody EditorialEntity editorialEntity) {
		EditorialEntity temporal = editorialService.edit(editorialEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(temporal);
	}

	@GetMapping("count")
	private ResponseEntity<Long> count(){
		return ResponseEntity.status(HttpStatus.OK).body(editorialService.count());
	}
	@DeleteMapping("delete/{id}")
	private ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			editorialService.deleteById(id);
			return ResponseEntity.ok("successfully removed");
		} catch (DataIntegrityViolationException de) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can´t remove this foreign key because it have been already assigned.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id doesn't match with any entity");
		}
	}

}
