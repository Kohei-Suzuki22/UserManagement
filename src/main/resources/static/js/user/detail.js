'use strict';


/* 画面ロード時の処理  **/
jQuery(function($) {

	$('#rest-update').click(function(event) {
		updateUser();
	});

	$('#rest-delete').click(function(event) {
		deleteUser();
	});
})

/* ユーザー更新処理 **/
function updateUser() {

	var formData = $('#user-detail-form').serializeArray();

	$.ajax({
		type: "PUT",
		cache: false,
		url: '/user/update',
		data: formData,
		dataType: 'json'
	}).done(function() {
		// ajax成功時の処理
		alert('ユーザーを更新しました')
		 window.location.href = '/user/list';
	}).fail(function() {
		// ajax失敗時の処理
		alert('ユーザー更新に失敗しました')
	}).always(function() {
		// 常に実行する処理
	});
}

/* ユーザー削除処理 **/
function deleteUser() {

	var formData = $('#user-detail-form').serializeArray();


	$.ajax({
		type: 'DELETE',
		cache: false,
		url: '/user/delete',
		data: formData,
		dataType: 'json',
	}).done(function() {
		// ajax成功時の処理
		alert("ユーザーを削除しました")
		 window.location.href = '/user/list';
	}).fail(function() {
		// ajax失敗時の処理
		alert("ユーザー削除に失敗しました");
	}).always(function() {
		//常に実行する処理
	})

}