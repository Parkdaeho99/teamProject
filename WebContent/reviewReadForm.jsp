<%@page import="java.util.ArrayList"%>
<%@page import="vo.Reply"%>
<%@page import="vo.Reviews"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String imgPath = "/boardUpload/";
	Reviews review = (Reviews) session.getAttribute("review");
	System.out.println(review.getContent().get(0));
	String id = (String) session.getAttribute("id");
%>
<%
	String num = review.getNum().get(0);
%>
<%
	Reply reply = (Reply) session.getAttribute("reply");
%>
<%
	int recCount = 0;
	if(request.getAttribute("recCount") != null) {
		recCount = (int)request.getAttribute("recCount");
		System.out.println("현재 recCount값 >>"+recCount);
	}
%>
<%	int recDuplicationCheck = 0;
	if(request.getAttribute("recDuplicationCheck")!= null) {
		recDuplicationCheck = (int)request.getAttribute("recDuplicationCheck");
		System.out.println("중복체크"+recDuplicationCheck);
	} else {
		System.out.println("NULL입니다.");
	}		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<header class="container mt-5">
		<div class="col-2" id="logo" onclick="location.href='index.do'" style="cursor: pointer;"><img src="imgs/logo.PNG" alt="Logo"/></div>
		<div class="col-2 ml-5" id="board"><button onclick="location.href='boardList.do'">전체 리뷰</button></div>
		<form id="searchForm" method="post" action="totalSearch.do">
			<input name="keyword" id="search" class="col-5" type="text"
				placeholder="제품명 혹은 모델번호로 검색하세요." required />
		</form>
		<% if(id == null){ %>
			<div class="col-2" id="login">
				<b><a href="loginForm.do"> 로그인</a> / 
				<a href="joinForm.do"> 회원가입</a></b>
			</div>
		<% } else { %>
			<div class="col-2" id="login">
				<h6> <a href="selectMember.do?id=<%=id %>"><%=id%></a>님 환영합니다.</h6>
				<b><a href="logout.do"> 로그아웃</a></b>
			<b><%if(id.equals("admin")) { %><a style="float:right;" href="./memberSearchForm.do" onclick="window.open(this.href, '_blank', 'width=500px,height=300px,toolbars=no,scrollbars=no'); return false;">멤버 관리</a></b><%} %>
			</div>
		<% } %>
	</header>
	<div class="clearfix"></div>
	<hr/>
	
	
	<section class="container">
		<div class="mb-3" style="border-bottom:1px solid black;">
			<h1 style="display:inline-block;" class="col-9">
				제목: <%=review.getTitle().get(0) %>
			</h1>
				<h5 style="display:inline;">조회: <%=review.getViews().get(0) %></h5> │
				<h5 style="display:inline;">추천: <%if(request.getAttribute("recCount") == null) {%><%=review.getRecommend().get(0)%><%}else{%> <%=recCount %><%} %></h5> │
				<h5 style="display:inline;">댓글: <%if(reply!=null) { %><%=reply.getReply_content().size() %><%} else { %><%=0%><%} %></h5>			
		</div>
			<h5 class="ml-3" style="float:left;">작성자: <%=review.getUser().get(0) %></h5>
			<button id="deleteReview" class="mr-3 mb-3" style="float:right;">삭제</button>
			<button id="updateReview" class="mr-3 mb-3" style="float:right;">수정</button>
		<div id="clearfix"></div>
		
		<div class="col-5" style="padding: 10px; float:left; border:1px solid black; width:420px; height:420px; ">
					<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
			  <div class="carousel-inner">
				    <div class="carousel-item active">
				      <img src="<%=imgPath+review.getImgPath().get(0) %>" class="d-block w-100" alt="이미지 없음">
				    </div>
				    <% if(review.getImgPath().get(1) != null) { %>
				    <div class="carousel-item">
				      <img src="<%=imgPath+review.getImgPath().get(1) %>" class="d-block w-100" alt="이미지 없음">
				    </div>
				    <%} %>
				    <% if(review.getImgPath().get(2) != null) { %>
				    <div class="carousel-item ">
				      <img src="<%=imgPath+review.getImgPath().get(2) %>" class="d-block w-100" alt="이미지 없음">
				    </div>
				    <%} %>
<%-- 			  <%} %> --%>
			  </div>
			  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
		</div>
		<textarea class="col-7" style=" margin-left: 42px; float:left; border:1px solid black; height: 420px; overflow:scroll;" readonly><%=review.getContent().get(0) %></textarea>
		<div style="clear:both;"></div>
		<div class="mt-5" style="width: 200px; margin: 0 auto;"><button id="recommendBtn">추천하기</button> <span id="recCount">추천수: <%if(request.getAttribute("recCount") == null) {%><%=review.getRecommend().get(0)%><%}else{%> <%=recCount %><%} %></span></div>
		<div class="mt-5"style="width: 100%; border-bottom:2px solid black;">전체 리플 <span style="color:red"><%if(reply!=null){%><%=reply.getReply_content().size() %><%}else {%>0<%} %>개</span></div>
		<div style="margin-top:15px;">
		<% int j; %>

		<%if(request.getParameter("page") == null) { j=0; } else { j = Integer.parseInt(request.getParameter("page"))-1; } %>
		<%if(reply != null) { %>
		<%for(int i = j*10; i<j*10+10; i++) { %>
			<div style="float:left; "class="col-2"><%=reply.getReply_user().get(i) %></div>
			<div style="float:left; word-wrap: break-word;"class="col-7"><%=reply.getReply_content().get(i) %></div>
			<div style="float:left;" class="col-2"><%=reply.getReply_date().get(i) %></div>
			<%if(id != null) { %>
			<% if(id.equals(reply.getReply_user().get(i)) || id.equals("admin")) { %>
				<div id="replyDelete" style="float:right; border:1px solid black; cursor:pointer;" class="btn-primary" onclick="location='./replyDelete.do?reply_num=<%=reply.getBoard_num().get(i)%>&&board_num=<%=num%>'">x</div>
			<%} %>
			<%} %>
			<div style="clear:both;">
			</div>
			<hr>	
			<%if(i == reply.getReply_content().size()-1) break; %>
		<%} %>
		</div>
		<%} %>
		<%if(reply!=null) { %>    
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
			    <%for(double i=0; i<= (((double)reply.getReply_content().size()) / 10)+0.9-1; i++) { %>
		    <li id="pageNum<%=(int)(i+1) %>" class="page-item <%if(i==0) { %> active <% }%>">
			    <a style="float:left; cursor:pointer;" class="page-link"><%=(int)(i+1)%></a>
		    </li>
			    <% } %>
		  </ul>
		</nav>
		<%} %>
		<div class="mt-5" style="border-top:2px solid black; border-bottom:2px solid black;">
		
			<form id="replyForm" class="mt-5" method="post" action="replyWrite.do?board_num=<%=num%>">
		 		<textarea id="replyText" maxlength="500" <%if(id==null) { %> <%="disabled"%> <%} %> name="reply_content" style="width:90%; float:left; height:100px;"
		 		required placeholder=<% if(id==null) { %> "로그인 후 댓글 등록이 가능합니다." <%}else { %> "타인의 권리를 침해하거나 명예를 훼손하는 댓글은 운영원칙 및 관련 법률에 제재를 받을 수 있습니다.&#13;&#10;Shift+Enter 키를 동시에 누르면 줄바꿈이 됩니다."<%} %>></textarea>
				<input <%if(id==null) { %> <%="disabled"%> <%} %> style="width:10%; float:left;" type="submit" value="등록"/>
		 	</form>
		</div>
	</section>
	<div id=clearfix></div>
	<footer class="mt-5" id="copyright">		
		copyright &copy; by Designart All Right Reserves
	</footer>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
	$(function(){
		$("#deleteReview").click(function(){
			if(confirm("삭제하시겠습니까?")) {
				<%if(session.getAttribute("id") == null) { %>
					alert("로그인 후 가능합니다.");
				<% } else if(review.getUser().get(0).equals(session.getAttribute("id")) || session.getAttribute("id").equals("admin")) { %>
					location.href="./deleteReview.do?board_num=<%=num%>";
				<% } else { %>
					alert("작성자만 삭제 가능합니다.");
				<% } %>
			}
		})
		$("#updateReview").click(function(){
			<%if(session.getAttribute("id") == null) { %>
				alert("로그인 후 가능합니다.");
			<% } else if(review.getUser().get(0).equals(session.getAttribute("id"))) { %>
				location.href="reviewUpdateForm.jsp?board_num=<%=num%>";
			<% } else { %>
				alert("작성자만 수정 가능합니다.");
			<% } %>
		})
		$("#replyText").keydown(function(key){
			if(key.keyCode == 13 && key.shiftKey) {
			} else if(key.keyCode == 13) {
				$("#replyForm").attr("action","replyWrite.do?board_num=<%=num%>").submit();
				
			}
		});
		$("#search").keydown(function(key){
			if(key.keyCode == 13) {
				$("#searchForm").submit();								
			}
		});
<%if(reply != null) { %>
<%for(double i=0; i<= (((double)reply.getReply_content().size()) / 10)+0.9-1; i++) { %>
	$("#pageNum<%=(int)(i+1)%>").click(function(){
		$.ajax({
			type : "GET",
			url : "./reviewReadForm.do?board_num=<%=num %>&&page=<%=(int)(i+1)%>",
			dataType : "html",
			error : function() {
				alert('통신실패!!');
				},
			success : function(data) {
				$('body').empty();
	            $('body').html(data);
	            $('.page-item').removeClass('active');
	            $("#pageNum<%=(int)(i+1)%>").addClass('active');
			}
		});
	});
	<%}%>
	<%}%>
	$("#recommendBtn").click(function(){
		<%if(id!=null) {%>
			<%if(recDuplicationCheck==0) { %>
			$.ajax({
				type : "GET",
				url : "./recommendReview.do?board_num=<%=num%>",
				error : function() {
					alert('통신실패!!');
					},
				success : function(data) {
					console.log(">"+data);
					$var2 = data.charAt(0);					
					if($var2 == 1) {
						recCount();						
					}else{
						alert('계정당 한번만 추천 가능합니다.');						
					}
				}
			});
			<%}%>
		<%}else{%>
			alert('로그인 후 추천 가능합니다.');
		<%}%>
	});
	function recCount(){
		$.ajax({
			type : "GET",
			url : "./recCount.do?board_num=<%=num%>",
			error : function() {
				alert('통신실패!!');
				},
			success : function(data) {
				console.log(">>"+data);
				$var1 = $(data).find("#recCount").text();
				$("#recCount").html($var1);
			}
		});	
	}
});
	
</script>
</body>
</html>