package usta.sistemas.finalProject.services;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.LowerCase;
import org.hibernate.query.criteria.internal.expression.function.LowerFunction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import usta.sistemas.finalProject.models.BookEntity;
import usta.sistemas.finalProject.repositories.BookRepo;

@Service
@RequiredArgsConstructor
public class BookService{
	  private final BookRepo bookRepo;

    public List<BookEntity> findAll() {
        return bookRepo.findAll();
    }

    public Optional<BookEntity> findById(Long id) {
        return bookRepo.findById(id);
    }


    public void deleteById(Long id){
        bookRepo.deleteById(id);
    }

		public BookEntity create(BookEntity bookEntity){
			try{
				return bookRepo.save(bookEntity);
			}catch(Error e){
				return null;
			}
		}
		public BookEntity edit(BookEntity bookEntity){
			try{
				if(findById(bookEntity.getId())!=null){
					return bookRepo.save(bookEntity);
				}else{
					return null;
				}
			}catch(Error e){
				System.err.println("something wents wrong");
				return null;
			}
		}
}

