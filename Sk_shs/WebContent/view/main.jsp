<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %> 


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
<div class="section">
	<h2 class="hx">메인페이지</h2>
	<div class="tx">
		<c:if test="${sessionScope.login_flag == 'Y' }" >
			<div>로그인되었습니다. ${sessionScope.user_id}님 환영합니다.</div>
			<div><a href="<c:url value='/auth/do-logout?board_name=${board_name}' />">로그아웃</a></div>
		</c:if>
		<c:if test="${sessionScope.login_flag != 'Y' }" >
			<div>로그인되지 않았습니다.</div>
			<div><a href="<c:url value='/view-login' />">로그인 이동하기</a></div>
		</c:if>
		<p></p>
		<div>
			<a href="<c:url value='/board/view-list?board_name=${board_name}' />">
				게시판 이동하기
			</a>
		</div>
	
	</div>
	<a href="#" class="section_more"><span>›</span> 더보기</a>
</div>

<script>
	<c:if test="${result == 'F' }" >
		alert('로그인에 실패하였습니다.');
	</c:if>
</script>
<!-- //UI Object -->