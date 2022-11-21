## FINAL PROJECT 
### TO excecute the project run
	./gradlew build
	./gradlew bootRun

## This RESTful Application offers to the users the endpoints below:
###CRUD
	/api/{entity_name}/list        #findAll
	/api/{entity_name}/save        #create new entity
	/api/{entity_name}/list/{id}   #detail
	/api/{entity_name}/delete/{id} #delete by id 
	/api/{entity_name}/edit/{id}   #edit entity

###Especial Endpoints
	/api/{entity_name}/list
	/api/author/delete/book/{book_id}/author/{author_id}  #remove many to many relation
	/api/author/books/{book_id}/authors                   #and an author to the book and create if doesn´t exist   
	


###QUERIES
	/api/editorial/queryOne/{Book_id}
	/api/client/queryTwo/{Loan_id}



