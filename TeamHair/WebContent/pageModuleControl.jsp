<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

	<h2>페이지 제어</h2>
<% 
	request.setCharacterEncoding("utf-8");

	String product=request.getParameter("branch");
	
	
	String control=null;
	String controlpage=null;
	String pagetitle=null;
	
	if(product.equals("강남점")){
		control="gangnam";
		controlpage="gangnam.jsp";
		pagetitle="강남역 2호점";
	}else if(product.equals("청담점")){
		control="chaungdam";
		controlpage="chungdam.jsp";
		pagetitle="청담점";
	}else if(product.equals("역삼점")){
		control="yeoksam";
		controlpage="yeoksam.jsp";
		pagetitle="역삼점";
	}
%>
<jsp:forward page="template.jsp">
	<jsp:param name="control" value="<%=control%>"/>
	<jsp:param name="controlpage" value="<%=controlpage%>"/>
	<jsp:param name="pagetitle" value="<%=pagetitle%>"/>
</jsp:forward>

</html>
