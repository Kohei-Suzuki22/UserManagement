package com.user.manegiment.basis.REST.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestResult {
	
	/* リターンコード **/
	private int resultCode;
	
	/** エラーマップ
	 * key: フィールド名
	 * value: エラーメッセージ
	 */
	private Map<String,String> errors;

}
