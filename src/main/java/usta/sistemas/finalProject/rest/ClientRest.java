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
import usta.sistemas.finalProject.models.ClientEntity;
import usta.sistemas.finalProject.services.ClientService;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientRest{
	private final ClientService clientService;

	@GetMapping("queryTwo/{id}")
	public ResponseEntity<Optional<ClientEntity>> getClientByLoan(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.findByLoan(id));
	}

	@GetMapping("list")
	public ResponseEntity<List<ClientEntity>> getAllCategories(){
		return ResponseEntity.ok(clientService.findAll());
	}
	@GetMapping("list/{id}")
	private ResponseEntity<Optional<ClientEntity>> listById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.FOUND).body(clientService.findById(id));
	}
	@PostMapping("save")
	private ResponseEntity<ClientEntity> save(@RequestBody ClientEntity clientEntity) {
		try{
			return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(clientEntity));
		}catch(Error e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping("count")
	private ResponseEntity<Long> count(){
		return ResponseEntity.status(HttpStatus.OK).body(clientService.count());
	}
	@PutMapping("edit")
	private ResponseEntity<ClientEntity> edit(@RequestBody ClientEntity clientEntity) {
		ClientEntity temporal = clientService.edit(clientEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(temporal);
	}

	@DeleteMapping("delete/{id}")
	private ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			clientService.deleteById(id);
			return ResponseEntity.ok("successfully removed");
		} catch (DataIntegrityViolationException de) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can´t remove this foreign key because it have been already assigned.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("id doesn't match with any entity");
		}
	}

}
