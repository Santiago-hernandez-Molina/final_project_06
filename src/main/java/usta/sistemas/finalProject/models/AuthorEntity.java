package usta.sistemas.finalProject.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Entity
@Table(name = "authors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name",nullable = false,length = 200)
	private String name;

	@Column(name = "lastName",nullable = false, length = 200)
	private String lastName;

	@Column(name = "nationality",nullable = false,length = 200)
	private String nationality;

	@Column(name = "description",length = 200)
	private String description;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
      cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
      }, mappedBy = "authors")
	private List<BookEntity> books;

}
