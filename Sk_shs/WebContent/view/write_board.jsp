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

<!-- UI Object -->
<form action="<c:url value='/board/insert' />" method="post">
	<fieldset>
		<legend>글 작성</legend>
		<div class="form_table">
		<table border="1" cellspacing="0" summary="표의 요약을 반드시 넣어 주세요">
		<tbody>
			<tr>
			<th scope="row">제목</th>
			<td>
				<div class="item">
					<label for="name_input" class="i_label" style="position:absolute; visibility:visible;"></label>
					<input type="text" name="name" id="name_input" class="i_text" style="width:300px">
					<input type="hidden" name="board_name" value="${board_name}">
				</div>
			</td>
			</tr>
			<tr>
			<th scope="row">내용</th>
			<td>
				<div class="item">
					<textarea name="content" cols="50" rows="5" title="레이블 텍스트" class="i_text"></textarea>
				</div>
			</td>
			</tr>			
		</tbody>
		</table>
		<div style="margin-top:10px">
			<input type="submit" value="저장">
		</div>
	</div>
	</fieldset>
</form>
<div>
	<a href="<c:url value='/board/view-list?board_name=${board_name}' />" > 목록보기</a>
</div>

<br>
<div>
		<c:if test="${sessionScope.login_flag == 'Y' }" >
			<div>${sessionScope.user_id}님 접속중입니다.</div>
		</c:if>
		<c:if test="${sessionScope.login_flag != 'Y' }" >
			<div>Guest님 접속중입니다.</div>
		</c:if>
</div>
<!-- //UI Object -->