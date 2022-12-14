package usta.sistemas.finalProject.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title",length = 200,nullable = false)
	private String title;

	@Column(name = "publication_date")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date publicationDate;

	@Column(name = "code",nullable = false)
	private int code;

	@Column(name = "isbn",length = 10,nullable = false)
	private String isbn;
	
	@ManyToOne
	@JoinColumn(name = "editorial_id",referencedColumnName = "id")
	private EditorialEntity editorial;

	
	@JoinTable(
		name = "authors_books",
		joinColumns = @JoinColumn(name = "book_id",nullable = false),
		inverseJoinColumns = @JoinColumn(name = "author_id")
		)

	@ManyToMany(fetch = FetchType.LAZY,
	cascade = {CascadeType.PERSIST,
						 CascadeType.MERGE})
	private List<AuthorEntity> authors;

	public void addAuthor(AuthorEntity author){
      if(this.authors == null){
          this.authors = new ArrayList<>();
      }  
      this.authors.add(author);
    }

	public void removeAuthor(long authorId) {
    AuthorEntity author = this.authors.stream().filter(ath -> ath.getId() == authorId).findFirst().orElse(null);
    if (author != null) {
      this.authors.remove(author);
      author.getBooks().remove(this);
    }
  }

	@OneToMany(mappedBy = "book")
	@JsonIgnore
	private List<LoanDetailEntity> loanDetailEntities;

}
