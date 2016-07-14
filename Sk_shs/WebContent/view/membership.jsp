<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>
/* UI Object */
.form_table{margin:1em 0;font-size:12px;font-family:Tahoma, Geneva, sans-serif}
.form_table input,
.form_table textarea,
.form_table select,
.form_table table,
.form_table button{font-size:12px}
.form_table table{width:100%;border:0;border-top:2px solid #999}
.form_table caption{padding:8px 0;font-weight:bold;text-align:left}
.form_table th,
.form_table td{padding:8px;border:0;vertical-align:top}
.form_table th{color:#333}
.form_table td{color:#666}
.form_table thead th{border-bottom:1px solid #ccc;background:#f8f8f8}
.form_table tbody th{border-bottom:1px solid #ddd;background:#fafafa;text-align:left}
.form_table tfoot th{border-bottom:1px solid #ddd}
.form_table tbody td{border-bottom:1px solid #ddd}
.form_table tfoot td{border-bottom:1px solid #ddd;font-weight:bold;color:#333}
.form_table .item{position:relative;margin:-4px 0}
.form_table .item .i_help{position:absolute;top:4px;right:0}
.form_table .item .i_label{ top:6px; left:10px}
.form_table .item .i_dsc{margin:2px 0}
.form_table .item .i_check,
.form_table .item .i_radio{width:13px;height:13px;margin:2px 5px 2px 0;padding:0;vertical-align:middle}
.form_table .item .i_text{position:relative;margin:2px;padding:3px 4px;border:1px solid #b7b7b7;border-right-color:#e1e1e1;border-bottom-color:#e1e1e1;background:transparent}
.form_table .item .hide{visibility:hidden;position:absolute;top:0;left:0;width:1px;height:1px;font-size:0;line-height:0}
.form_table .item select{margin:2px}
.form_table .item label{margin:0 10px 0 0;cursor:pointer}
/* //UI Object */
</style>
	<script>
		function dologin(){
			if(frm.user_id.value==""){
				alert("아이디를 입력해주세요");
				frm.user_id.focus();				
				return 0;
			}
			if(frm.user_id.value.length<5){
				alert("아이디는 5글자 이상입니다");
				frm.user_id.focus();
				return 0;
			}
			
			
			if(frm.password.value==""){
				alert("패스워드를 입력해주세요");
				frm.password.focus();
				return 0;
			}
			
			if(frm.password.value.length<6){
				alert("패스워드는 6글자 이상입니다");
				frm.password.focus();
				return 0;
			}
			if(frm.password.value !=frm.password_comfirm.value){
				alert("비밀 번호가 다릅니다. 확인하여 주시기 바랍니다");
				frm.password.focus();
				return 0;
			}
			frm.submit();
			alert("가입 완료");
		}
		
	
	</script>
	


<!-- UI Object -->
<form name = "frm" action="<c:url value='/board/membership' />" method="post">
	<fieldset>
		<legend>회원가입</legend>
		<div class="form_table">
		<table border="1" cellspacing="0" summary="표의 요약을 반드시 넣어 주세요">
		<tbody>
			<tr>
			<th scope="row">아이디</th>
			<td>
				<div class="item">
					<label for="name_input" class="i_label" style="position:absolute; visibility:visible;"></label>
					<input type="text" name="user_id" id="id_input" class="i_text" style="width:300px">
				</div>
			</td>
			</tr>
			<tr>
			<th scope="row">이름 </th>
			<td>
				<div class="item">
					<label for="name" class="i_label" style="position:absolute; visibility:visible;"></label>
					<input type="text" name="user_name" id="user_name" class="i_text" style="width:300px"> *이름을 입력해주세요
				</div>
			</td>
			<tr>
			<th scope="row">비밀번호</th>
			<td>
				<div class="item">
					<label for="password_input" class="i_label" style="position:absolute; visibility:visible;"></label>
					<input type="password" name="password" id="password" class="i_text" style="width:300px"> *비밀번호 : pwd
				</div>
			</td>
			<tr>
			<th scope="row">비밀번호 확인</th>
			<td>
				<div class="item">
					<label for="password_input" class="i_label" style="position:absolute; visibility:visible;"></label>
					<input type="password" name="password_comfirm" id="password_comfirm" class="i_text" style="width:300px"> *비밀번호를 다시입력해 주세요
				</div>
			</td>
			</tr>
			<tr>
			<th scope="row">학번</th>
			<td>
				<div class="item">
					<label for="board_name" class="i_label" style="position:absolute; visibility:visible;"></label>
					<input type="text" name="board_name" id="board_name" class="i_text" style="width:300px"> *학번을 입력해주세요
				</div>
			</td>			
			</tr>			
		</tbody>
		</table>
		<div style="margin-top:10px">
				<input type="button" id="membership" value="가입하기" 	onclick = "dologin()">
				<a href="<c:url value='/view-login' />" > 뒤로가기 </a></td>
		</div>
	</div>
	</fieldset>
</form>
<div>
	<a href="<c:url value='/main' />" > 메인 페이지 이동 </a>
</div>
<!-- //UI Object -->