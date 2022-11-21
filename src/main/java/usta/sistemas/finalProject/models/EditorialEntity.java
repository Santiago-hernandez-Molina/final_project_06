package usta.sistemas.finalProject.models;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "editorials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditorialEntity implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name",length = 200,nullable = false)
	private String name;

	@Column(name = "country",length = 30)
	private String country;

	@Column(name = "phone",length = 20,nullable = false)
	private String phone;

	@OneToMany(mappedBy = "editorial")
	@JsonIgnore
	private List<BookEntity> books;
}
