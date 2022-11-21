package usta.sistemas.finalProject.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.LowerCase;
import org.hibernate.query.criteria.internal.expression.function.LowerFunction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import usta.sistemas.finalProject.models.ClientEntity;
import usta.sistemas.finalProject.repositories.ClientRepo;

@Service
@RequiredArgsConstructor
public class ClientService{
	private final ClientRepo clientRepo;

	public List<ClientEntity> findAll() {
		return clientRepo.findAll();
	}

	public Optional<ClientEntity> findById(Long id) {
		return clientRepo.findById(id);
	}

	public Optional<ClientEntity> findByLoan(Long id){
		return clientRepo.findClientByLoansId(id);
	}

	public void deleteById(Long id){
		clientRepo.deleteById(id);
	}

	public ClientEntity create(ClientEntity clientEntity){
		try{
			return clientRepo.save(clientEntity);
		}catch(Error e){
			return null;
		}
	}
	public ClientEntity edit(ClientEntity clientEntity){
		try{
			if(findById(clientEntity.getId())!=null){
				return clientRepo.save(clientEntity);
			}else{
				return null;
			}
		}catch(Error e){
			System.err.println("something wents wrong");
			return null;
		}
	}
	public long count(){
		return clientRepo.count();
	}	

}

