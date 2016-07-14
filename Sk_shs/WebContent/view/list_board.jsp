<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>
/* UI Object */
.section_ul {
	position: relative;
	border: 1px solid #ddd;
	background: #fff;
	font-size: 12px;
	font-family: Tahoma, Geneva, sans-serif;
	line-height: normal;
	*zoom: 1
}

.section_ul a {
	color: #666;
	text-decoration: none
}

.section_ul a:hover, .section_ul a:active, .section_ul a:focus {
	text-decoration: underline
}

.section_ul em {
	font-style: normal
}

.section_ul h2 {
	margin: 0;
	padding: 10px 0 8px 13px;
	border-bottom: 1px solid #ddd;
	font-size: 12px;
	color: #333
}

.section_ul h2 em {
	color: #cf3292
}

.section_ul ul {
	margin: 13px;
	padding: 0;
	list-style: none
}

.section_ul li {
	position: relative;
	margin: 0 0 10px 0
}

.section_ul li:after {
	display: block;
	clear: both;
	content: ""
}

.section_ul li .bu {
	float: left;
	margin: 0 4px 0 0;
	color: #999
}

.section_ul li a {
	float: left
}

.section_ul li .time {
	float: right;
	clear: right;
	font-size: 11px;
	color: #a8a8a8;
	white-space: nowrap
}

.section_ul .more {
	position: absolute;
	top: 10px;
	right: 13px;
	font: 11px Dotum, 돋움;
	text-decoration: none !important
}

.section_ul .more span {
	margin: 0 2px 0 0;
	font-size: 16px;
	font-weight: bold;
	color: #d76ea9;
	vertical-align: middle
}
/* //UI Object */
</style>
<!-- UI Object -->



<div class="section_ul">
	<h2>
		최신 <em>게시판 목록</em>
		<span style="float:right;margin-right:10px">
			<a href="<c:url value='/board/view-insert?board_name=${board_name}' />" class="section_more">작성하기</a>
			<a href="<c:url value='/board/view-insert-file?board_name=${board_name}' />" class="section_more">작성하기-파일첨부</a>
		</span>
		
	</h2>
	<ul>
		<c:forEach var="item" items="${board_list}">
			<li><span class="bu">›</span> 
				<a href="<c:url value='/board/view-detail?id=${item.id}&board_name=${board_name}' />">
					${item.name}
				</a> 
			<span class="time">${item.regiDatetime}</span></li>
		</c:forEach>
	</ul>
</div>

<br><br><br>

<form name = "frm" action="<c:url value='/board/search-list?board_name=${board_name}' />" method="post">
   <fieldset>
   
      <legend>게시물 검색</legend>
      <div class="form_table">
      <table border="1" cellspacing="0" summary="표의 요약을 반드시 넣어 주세요">
      <tbody>
         <tr>
         <th scope="row">게시물명</th>
         <td>
            <div class="item">
               <label for="name_input" class="i_label" style="position:absolute; visibility:visible;"></label>
               <input type="text" name="name" id="name_input" class="i_text" style="width:300px">
            </div>
         </td>
         </tr>
      </tbody>
      </table>
      <div style="margin-top:10px">
            <input type="submit" id="search" value="검색" onclick = "dologin()">
        
      </div>
   </fieldset>
   </form>














<div>
	<a href="<c:url value='/main?board_name=${board_name}' />" > 메인 페이지 이동 </a>
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