package usta.sistemas.finalProject.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.LowerCase;
import org.hibernate.query.criteria.internal.expression.function.LowerFunction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import usta.sistemas.finalProject.models.EditorialEntity;
import usta.sistemas.finalProject.repositories.EditorialRepo;

@Service
@RequiredArgsConstructor
public class EditorialService{
	private final EditorialRepo editorialRepo;

	public List<EditorialEntity> findAll() {
		return editorialRepo.findAll();
	}

	public Optional<EditorialEntity> findById(Long id) {
		return editorialRepo.findById(id);
	}


	public void deleteById(Long id){
		editorialRepo.deleteById(id);
	}

	public EditorialEntity create(EditorialEntity editorialEntity){
		try{
			return editorialRepo.save(editorialEntity);
		}catch(Error e){
			return null;
		}
	}
	public EditorialEntity edit(EditorialEntity editorialEntity){
		try{
			if(findById(editorialEntity.getId())!=null){
				return editorialRepo.save(editorialEntity);
			}else{
				return null;
			}
		}catch(Error e){
			System.err.println("something wents wrong");
			return null;
		}
	}
}

