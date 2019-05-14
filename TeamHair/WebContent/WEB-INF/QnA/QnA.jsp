<%@page import="kr.or.bit.dao.QnADao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>

	<style>
		#qnalist {
			margin: 5%;
		}
		
		#pagination {
			margin: 1%;
		}
	</style>
		
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,400i,600,700,800" rel="stylesheet">
<hr>
		<%  
      	   int cpage = (Integer)request.getAttribute("cpage");
     	   int pagesize = (Integer)request.getAttribute("pagesize");
     	   int pagecount = (Integer)request.getAttribute("pagecount");
     	   int totalboardCount = (Integer)request.getAttribute("totalboardCount");
     	   List<QnADao> qnalist = (List)request.getAttribute("qnalist");
   		 %>
		<c:set var="pagesize" value="<%=pagesize%>" />
		<c:set var="cpage" value="<%=cpage%>" />
		<c:set var="pagecount" value="<%=pagecount%>" />
		<c:set var="qnalist" value="<%=qnalist%>"/>
		<c:set var="totalboardCount" value="<%=totalboardCount%>"/>
		
				
		
		<div id="qnalist">
			<table>
				<tr>
	    			<th><p>글번호</p></th>
					<th class="name">제목</th>
					<th><p>작성자</p></th>
					<th><p>작성일</p></th>
					<th><p>조회수</p></th>
				</tr>
				<c:set var="qnalist" value="${requestScope.qnalist}"></c:set>
				<c:forEach var="i" items="${qnalist }">
				<tr>
					<td>${i.boardID }</td>
					<td>
						<a href="QnAdetail.do?boardID=${i.boardID}&cpage=${cpage}">
							<fmt:parseNumber value="${i.replyDepth}" var="rr" type="number"/>
								<c:forEach var="a" begin="1" end="${rr}" step="1">
									&nbsp; &nbsp;<img src="img/re.gif"> 
								</c:forEach>
							${i.boardSubject}
						</a>
					</td>
					<td>${i.userID }</td>
					<td>${i.createDate }</td>
					<td>${i.readCount}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		
		<div id="pagination">
		
			<nav aria-label="Page navigation example">
		  		<ul class="pagination justify-content-center">
			  		<c:if test="${cpage>1}">
				    	<li class="page-item"><a class="page-link" href="QnA.do?cpage=${cpage-1}&pagesize=${pagesize}">Previous</a></li>
				    </c:if> 
				    <c:forEach var="i" begin="1" end="${pagecount}" step="1">
						<li class="page-item"><a class="page-link" href="QnA.do?cpage=${i}&pagesize=${pagesize}">${i}</a></li>
					</c:forEach> 
					<c:if test="${cpage<pagecount}">
				    	<li class="page-item"><a class="page-link" href="QnA.do?cpage=${cpage+1}&pagesize=${pagesize}">Next</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
<hr>
<div class="qna-sub">
<input type="button" class="dropdown" value="글쓰기" onclick="location='QnAwrite.do'">
</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>

