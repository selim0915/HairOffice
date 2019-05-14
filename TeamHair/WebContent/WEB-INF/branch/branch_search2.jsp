<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


${requestScope.branchlist}

<c:forEach var="i" begin="0" end="${branchlist.size() }">
	<tr>
		<td>${branchlist[i].map_x }</td>
		<td>${branchlist[i].map_Y }</td>
		
	</tr>
</c:forEach>