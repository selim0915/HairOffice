<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 

	String control=request.getParameter("control");
	String pagetitle=request.getParameter("pagetitle");
	String content=request.getParameter("controlpage");
	String left=control+"Left.jsp";
	
%>
<html>
	<head><title><%=pagetitle %></title></head>
	<body>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
		<table width="500" border="1" cellpadding="2" cellspacing="0">
			<tr>
				<td colspan="2">
					<jsp:include page="top.jsp" flush="false" />
				</td>
			</tr>
			<tr>
				<td width="150" valign="top">
					<jsp:include page="<%=left %>" flush="false" />
				</td>
				<td width="350" valign="top">
					<jsp:include page="<%=content %>" flush="false" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<jsp:include page="bottom.jsp" flush="false" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center">
					<input type="submit" name="payment" id="btnDonate" value="결 제" style="width: 75px">
				</td>
			</tr>
		</table>
		<!-- <span>우편 및 소포 처리를 사용하시겠습니까?</span>
		<input type="radio" value="" disabled="" id="radio_yes" class="">
		<label for=""><span>예</span></label>
		<input type="radio" value="" disabled="" id="radio_yes" class="">
		<label for=""><span>아니오</span></label><br> -->
	
		
	
	
	
		<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
	</body>
</html>
	
	