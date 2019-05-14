<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include> 

	    
	<div class="wrapper" style="margin-left: 100px">
	
	<h2>가입</h2>
	<h3>지점 선택</h3>
	<form action="pageModuleControl.jsp">
	<label for="branch-select">Choose a location:</label>
	<div class="form-group">
	<div class="single-element-widget">
	<div class="branch-select-sub" id="branch-select-sub">
	<select id="branch-select">
    <option value="">--Please choose an option--</option>
    <option value="서울" selected="">서울</option>
    <option value="경기" disabled="">경기</option>
    <option value="인천" disabled="">인천</option>   
    <option value="기타" disabled="">기타</option>
	</select><br>
	</div></div></div>
		
	<table width="500" border="1" cellpadding="2" cellspacing="0">
			<tr>
				<td width="150" valign="top">
					<input type="radio" name="branch" value="강남점">강남점<br>
				</td>
				<td width="350" valign="top">
					(06159) 서울 강남구 테헤란로 507
				</td>
				<td width="350" valign="top">
					₩320,000.00
				</td>
			</tr>
			<tr>
				<td width="150" valign="top">
					<input type="radio" name="branch" value="청담점">청담점<br>
				</td>
				<td width="350" valign="top">
					(06159) 서울 강남구 테헤란로 507
				</td>
				<td width="350" valign="top">
					₩320,000.00
				</td>
			</tr>
			<tr>
				<td width="150" valign="top">
					<input type="radio" name="branch" value="역삼점">역삼점<br>
				</td>
				<td width="350" valign="top">
					(06159) 서울 강남구 테헤란로 507
				</td>
				<td width="350" valign="top">
					₩320,000.00
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center">
					<input type="submit" value="확인"><br>
				</td>
			</tr>
		</table>	
		
		
	</form>
	</div>
	
</html>
	
	
<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>