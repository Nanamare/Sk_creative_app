<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %> 

<fmt:requestEncoding value="utf-8"/>

<style>
/* UI Object */
.section{position:relative;border:1px solid #e9e9e9;background:#fff;font-size:12px;line-height:normal;*zoom:1}
.section .hx{margin:0;padding:10px 0 7px 9px;border:1px solid #fff;background:#f7f7f7 url(img/br_section_title.gif) repeat-x left bottom;font-size:12px;line-height:normal;color:#333}
.section .tx{padding:10px;border-top:1px solid #e9e9e9;color:#666}
.section .section_more{position:absolute;top:9px;right:10px;font:11px Dotum, 돋움, Tahoma;color:#656565;text-decoration:none !important}
.section .section_more span{font:14px/1 Tahoma;color:#6e89aa}
/* //UI Object */
</style>

<!-- UI Object -->
<form name = "frm" action="<c:url value='/board/modify' />" method="post">
<div class="section">
	<h2 class="hx">${board_info.name}</h2>
	<div class="tx">
		${board_info.content}
		<c:if test="${board_info.attachUrl != null}">
			<a href="<c:url value='/board/down-attach-file?path_name=${board_info.attachUrl}' />" >다운로드</a>
		</c:if>
	</div>
	<div class="tx">
		작성자 : ${board_info.regiUserId} &nbsp;&nbsp;&nbsp;&nbsp;
	<!-- 	<a href="<c:url value='/board/modify' />" >수정하기</a> -->
		
	<input type="hidden" name="board_name" value="${board_name}">
	<input type="hidden" name="name" value="${board_info.name}">
	<input type="hidden" name="content" value="${board_info.content}">
	  <input type="hidden" name="id" value="${board_info.id}">	

		<input type="submit" value="수정 하기"> 
		
		<div>
	
	<a href="<c:url value='/board/delete?id=${board_info.id}&board_name=${board_info.boardName}' />" class="section_more"><span>›</span> 삭제</a>
	
	
	
</div>
<div>
	<a href="<c:url value='/board/view-list?board_name=${board_info.boardName}' />" > 목록보기</a>
</div>
</form>
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