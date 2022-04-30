package com.user.manegiment.basis.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "salary")
public class Salary {
	
	
	
//	private String userId;
//	private String yearMonth;
	
	@EmbeddedId
	private SalaryKey salaryKey;
	private Integer salary;
	

}
