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
import usta.sistemas.finalProject.models.AuthorEntity;
import usta.sistemas.finalProject.services.AuthorService;

@RestController
@RequestMapping("api/author")
@RequiredArgsConstructor
public class AuthorRest{
	private final AuthorService authorService;

	@GetMapping("list")
	public ResponseEntity<List<AuthorEntity>> getAllCategories(){
		return ResponseEntity.ok(authorService.findAll());
	}

	@GetMapping("list/{id}")
	private ResponseEntity<Optional<AuthorEntity>> listById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(authorService.findById(id));
	}

	@PostMapping("/books/{bookId}/authors")
	public ResponseEntity<AuthorEntity> addAuthorEntity(@PathVariable(value = "bookId") Long bookId, @RequestBody AuthorEntity authorRequest) {
		AuthorEntity author = authorService.addAuthorByBook(bookId, authorRequest);
		return new ResponseEntity<>(author, HttpStatus.CREATED);
	}

	@PutMapping("edit")
	private ResponseEntity<AuthorEntity> edit(@RequestBody AuthorEntity authorEntity) {
		AuthorEntity temporal = authorService.edit(authorEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(temporal);
	}

	@DeleteMapping("delete/book/{bookId}/author/{authorId}")
	private ResponseEntity<String> deleteFromManyToMany(@PathVariable(value = "bookId") Long bookId, @PathVariable(value = "authorId") Long authorId) {
		try {
			authorService.deleteByIdManyToMany(bookId,authorId);
			return ResponseEntity.ok("successfully removed");
		} catch (DataIntegrityViolationException de) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can´t remove this foreign key because it have been already assigned.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id doesn't match with any entity");
		}
	}

	@DeleteMapping("delete/{bookId}")
	private ResponseEntity<String> delete(@PathVariable Long bookId) {
		try {
			authorService.deleteById(bookId);
			return ResponseEntity.ok("successfully removed");
		} catch (DataIntegrityViolationException de) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can´t remove this foreign key because it have been already assigned.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id doesn't match with any entity");
		}
	}
	@GetMapping("count")
	private ResponseEntity<Long> count(){
		return ResponseEntity.status(HttpStatus.OK).body(authorService.count());
	}
}
