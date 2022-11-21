package usta.sistemas.finalProject.models;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "dni",nullable = false,length = 50)
	private String dni;

	@Column(name = "name",nullable = false,length = 200)
	private String name;

	@Column(name = "lastName",nullable = false, length = 200)
	private String lastName;

	@Column(name = "address",nullable = false,length = 150)
	private String address;

	@Column(name = "phone",length = 20)
	private String phone;

}
