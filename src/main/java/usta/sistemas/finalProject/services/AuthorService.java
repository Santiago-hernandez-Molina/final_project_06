package usta.sistemas.finalProject.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.LowerCase;
import org.hibernate.query.criteria.internal.expression.function.LowerFunction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import usta.sistemas.finalProject.models.AuthorEntity;
import usta.sistemas.finalProject.models.BookEntity;
import usta.sistemas.finalProject.repositories.AuthorRepo;
import usta.sistemas.finalProject.repositories.BookRepo;


@Service
@RequiredArgsConstructor
public class AuthorService{
	private final AuthorRepo authorRepo;
	private final BookRepo bookRepo;


	public List<AuthorEntity> findAll() {
		return authorRepo.findAll();
	}

	public Optional<AuthorEntity> findById(Long id) {
		return authorRepo.findById(id);
	}

	public AuthorEntity addAuthorByBook(Long id, AuthorEntity authorRequest){
		AuthorEntity author = bookRepo.findById(id).map(book -> {
			Long authorId = authorRequest.getId();

			// author is existed
			if (authorId != null) {
				AuthorEntity _author = authorRepo.findById(authorId)
					.orElseThrow(() -> new NullPointerException());
				book.addAuthor(_author);
				bookRepo.save(book);
				return _author;
			}

			// add and create new Tag
			book.addAuthor(authorRequest);
			AuthorEntity authorEntity = authorRepo.save(authorRequest);
			return authorEntity;
		}).orElseThrow(() -> new NullPointerException());
		return author;
	}


	public void deleteByIdManyToMany(Long bookId,Long authorId){
		BookEntity bookEntity = bookRepo.findById(bookId)
        .orElseThrow();
    
    bookEntity.removeAuthor(authorId);
    bookRepo.save(bookEntity);
	}

	public void deleteById(Long authorId){
		authorRepo.deleteById(authorId);
	}

	public AuthorEntity create(AuthorEntity authorEntity){
		try{
			return authorRepo.save(authorEntity);
		}catch(Error e){
			return null;
		}
	}
	public AuthorEntity edit(AuthorEntity authorEntity){
		try{
			if(findById(authorEntity.getId())!=null){
				return authorRepo.save(authorEntity);
			}else{
				return null;
			}
		}catch(Error e){
			System.err.println("something wents wrong");
			return null;
		}
	}
}

