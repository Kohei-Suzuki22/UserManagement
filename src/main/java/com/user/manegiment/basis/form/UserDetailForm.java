package com.user.manegiment.basis.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.user.manegiment.basis.entity.MasterDepartment;
import com.user.manegiment.basis.form.validation.ValidGroup1;
import com.user.manegiment.basis.form.validation.ValidGroup2;

import lombok.Data;

@Data
public class UserDetailForm {
	
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String userId;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 4, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;
	
	@NotBlank(groups = ValidGroup1.class)
	private String userName;
	private Date birthday;
	private Integer age;
	private Integer gender;
	private MasterDepartment department;

}
