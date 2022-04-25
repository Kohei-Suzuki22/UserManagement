'use strict';



/* 画面ロード時の処理  **/

jQuery(function($) {

	/* 登録ボタンを押した時の処理 **/
	$('#rest-signup').click(function(event) {
		signupUser();
	})
})



/* ユーザー登録処理 **/
function signupUser() {

	// バリデーション結果クリア
	removeValidResult();

	// フォームの値を取得
	var formData = $('#signup-form').serializeArray();

	$.ajax({
		type: "POST",
		cache: false,
		url: '/user/signup/rest',
		data: formData,
		dataType: 'json'
	}).done(function(data) {
		// ajax成功時の処理
		console.log(data);

		if (data.resultCode === 90) {
			// validationエラー時の処理
			$.each(data.errors, function(key, value) {
				reflectValidResult(key, value)
			});
		} else if (data.resultCode === 0) {
			alert('ユーザーを登録しました');
			window.location.href = '/login';
		}
	}).fail(function() {
		alert('ユーザー登録に失敗しました');
	}).always(function() {
		//常に実行する処理
	})

}


/* バリデーション結果をクリア **/
function removeValidResult() {
	$('.is-invalid').removeClass('is-invalid');
	$('.invalid-feedback').remove();
	$('.text-danger').remove();
}


/* バリデーション結果の反映 **/
function reflectValidResult(key, value) {
	// エラーメッセージ追加
	if (key === 'gender') {
		// CSS適用
		$('input[name=' + key + ']').addClass('is-invalid');
		// エラーメッセージの追加
		$('input[name=' + key + ']').parent().parent()
			.append('<div class="text-danger">' + value + '</div>');


	} else {
		// CSS適用
		$('input[id=' + key + ']').addClass('is-invalid');
		// エラーメッセージ追加
		$('input[id=' + key + ']').after('<div class="invalid-feedback">' + value + '</div>')
	}
}



