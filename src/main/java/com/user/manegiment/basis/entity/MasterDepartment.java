package com.user.manegiment.basis.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "master_department")
public class MasterDepartment {

	@Id
	private Integer departmentId;
	private String departmentName;
}
