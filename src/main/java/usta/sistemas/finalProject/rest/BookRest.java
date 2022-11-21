package usta.sistemas.finalProject.rest;

import java.util.List;

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
import usta.sistemas.finalProject.models.BookEntity;
import usta.sistemas.finalProject.services.BookService;

@RestController
@RequestMapping("api/book")
@RequiredArgsConstructor
public class BookRest{
	private final BookService bookService;

    @GetMapping("list")
    public ResponseEntity<List<BookEntity>> getAllCategories(){
        return ResponseEntity.ok(bookService.findAll());
    }
    @PostMapping("save")
    private ResponseEntity<BookEntity> save(@RequestBody BookEntity bookEntity) {
			try{
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookEntity));
			}catch(Error e){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
    }

    @PutMapping("edit")
    private ResponseEntity<BookEntity> edit(@RequestBody BookEntity bookEntity) {
        BookEntity temporal = bookService.edit(bookEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(temporal);
    }

    @DeleteMapping("delete/{id}")
    private ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            bookService.deleteById(id);
            return ResponseEntity.ok("successfully removed");
        } catch (DataIntegrityViolationException de) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can´t remove this foreign key because it have been already assigned.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id doesn't match with any entity");
        }
    }

}
