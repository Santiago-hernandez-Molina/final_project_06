package usta.sistemas.finalProject.models;

import java.io.Serial;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loans_detail")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDetailEntity{
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "loan_id",referencedColumnName = "id")
	private LoanEntity loan;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "book_id",referencedColumnName = "id")
	private BookEntity book;

	@Column(name = "refund_date",nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date refundDate;

	@Column(name = "days_past_due")
	private int daysPastDue;

	@Column(name = "description",length = 200,nullable = false)
	private String description;

	@Column(name = "delivery_date",nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date deliveryDate;

}
