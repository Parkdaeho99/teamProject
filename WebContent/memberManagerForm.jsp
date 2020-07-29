<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 	Member member = null;
	String id="";
	if(request.getAttribute("member") != null) {
		member = (Member)request.getAttribute("member");
		id = request.getParameter("id");
		System.out.println("멤버 정보 가져오기 성공");
		
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(request.getAttribute("member") != null){ %>
<div>이름: <%=member.getName() %></div>
<div>아이디: <%=member.getId() %></div>
<div>성별: <%=member.getGender() %></div>
<div>생일: <%=member.getBirth() %></div>
<div id="state">밴 상태: <%=member.isBan() %></div>
<button onclick='history.back();'>뒤로가기</button>
<button id="banBtn" type="button">밴 등록</button>
<button id="permitBtn" type="button">밴 해제</button>
<%} %>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
<script>
$(function(){
	$("#banBtn").click(function(){
		$.ajax({
			type : "GET",
			url : "./memberBan.do?id=<%=id%>",
			error : function() {
				alert('통신실패!!');
			},
			success : function(data) {
				if(data == 1) {
					$("#state").text("밴 상태: true");
				} else {
					$("#state").text("밴 상태: false");
				}
			}
		});
	});
	$("#permitBtn").click(function(){
		$.ajax({
			type : "GET",
			url : "./memberPermit.do?id=<%=id%>",
			error : function() {
				alert('통신실패!!');
			},
			success : function(data) {
				if(data == 1) {
					$("#state").text("밴 상태: false");
				} else {
					$("#state").text("밴 상태: true");
				}
			}
		});
	});
});
</script>
</html>