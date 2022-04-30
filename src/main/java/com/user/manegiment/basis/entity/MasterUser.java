package com.user.manegiment.basis.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="master_user")
public class MasterUser {
	
	@Id
	private String userId;
	private String password;
	private String userName;
	private Date birthday;
	private Integer age;
	private Integer gender;
	private Integer departmentId;
	private String role;
	
	@ManyToOne(optional = true)
	@JoinColumn(insertable = false, updatable = false, name="departmentId", referencedColumnName = "departmentId")
	private MasterDepartment department;
	
	@OneToMany
	@JoinColumn(insertable = false, updatable = false, name="userId", referencedColumnName = "userId")
	private List<Salary> salaryList;

}
