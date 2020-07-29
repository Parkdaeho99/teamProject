<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String id = (String)session.getAttribute("id");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#loginFormArea{
		margin : auto;
		width : 400px;
		height : 200px;
		border : 2px double purple; 
		border-radius : 10px;
		text-align: center;
	}
	fieldset{
		text-align: center;
		border : none;
	}
	#selectButton{
		margin-top : 10px;
	}
	table{
	    width : 380px;
		margin : auto;
	}
	.td_left{
		width : 180px
	}
	.td_right{
		width : 200px
	}
	*{
		list-style: none;
	}
	#logo {
		float:left;
	}
	#category {
		float:left;
	}
	#search {
		float:left;
	}
	#login {
		float:left;
	}
	#clearfix {
		clear:both;
		content:"";
		display:block;
	}
</style>
</head>
<body>
    <section  id = "loginFormArea">
	<h1>회원 검색</h1>
	<form action="./memberSearch.do" method = "POST">
		<fieldset>
			<table>
				<tr>
					<td class = "td_left">
					<label for = "id">아이디 : </label>
					</td>
					<td class = "td_right">
					<input type = "text" name = "id" id = "id"/>
					</td>
				</tr>
			</table>
			<input style="margin-top:50px;"type = "submit" value = "검색" id = "selectButton"/>
		</fieldset>
	</form>
	</section>
</body>
</html>