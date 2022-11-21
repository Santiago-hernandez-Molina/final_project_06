package usta.sistemas.finalProject.dtos.loanDetailDtos;

import java.util.Date;

import lombok.Data;

@Data
public class loanDetailDTO{
	private Long id;
	private Long loanId;
	private Long bookId;
	private Date refundDate;
	private int daysPastDue;
	private String description;
	private Date deliveryDate;
}

