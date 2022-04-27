package com.user.manegiment.basis.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@Transient
	private MasterDepartment department;
	@Transient
	private List<Salary> salaryList;

}
