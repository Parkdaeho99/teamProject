<%@page import="vo.PageInfo"%>
<%@page import="java.util.*"%>
<%@page import="vo.Reviews"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	String category = null;
	if(request.getParameter("category") != null) {
		category = request.getParameter("category");
	}
	String id = (String)session.getAttribute("id");
   String name = (String)session.getAttribute("name");
   Reviews articleList = (Reviews)request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
	int listCount=pageInfo.getListCount();
	int nowPage=pageInfo.getPage();
	int maxPage=pageInfo.getMaxPage();
	int startPage=pageInfo.getStartPage(); 
	int endPage=pageInfo.getEndPage(); 
 %> 
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="css/main.css">
	<title>Title</title>
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
				<b><a href="logout.do"> 로그아웃</a>
			<%if(id.equals("admin")) { %><a style="float:right;" href="./memberSearchForm.do" onclick="window.open(this.href, '_blank', 'width=500px,height=300px,toolbars=no,scrollbars=no'); return false;">멤버 관리</a></b><%} %>
			</div>
		<% } %>
	</header>
	<div class="clearfix"></div>
	<hr/>
	<div class="container"><h5>리뷰 게시물 검색결과</h5></div>
	<section class = "container" id="listForm">
		<p><%=category %> 리뷰<small>(<%=listCount %>개)</small></p>
		<div class="card-group">
		
			<%
			if(articleList != null ){
			%>
			<%
			for(int i= 0, j = 1 ; j<=articleList.getContent().size();j++, i++){
				if(j % 4 == 0){ %>
			<div id="cardList" class="p-0 card col-3" onclick="location.href='reviewReadForm.do?board_num=<%=articleList.getNum().get(i) %>'" style="cursor:pointer;">
				<img src="/boardUpload/<%=articleList.getImage().get(i) %>" class="card-img-top" alt="...">
				<div class="card-body">
					<small class="text-muted">작성자: <%=articleList.getUser().get(i) %></small>
					<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title"><%=articleList.getTitle().get(i)%></h5>
					<p class="card-text"><%=articleList.getContent().get(i)%></p>
				</div>
				<div class="card-footer">
					<b>카테고리: <%=articleList.getCategory().get(i) %></b><br/>
				</div>
			</div>
			</div>
			<div class = "card-group">	
			<%		
				} else { 
			%>
			<div id="cardList" class="p-0 card col-3" onclick="location.href='reviewReadForm.do?board_num=<%=articleList.getNum().get(i) %>'" style="cursor:pointer;">
				<img src="/boardUpload/<%=articleList.getImage().get(i) %>" class="card-img-top" alt="...">
				<div class="card-body">
					<small class="text-muted">작성자: <%=articleList.getUser().get(i) %></small>
					<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title"><%=articleList.getTitle().get(i)%></h5>
					<p class="card-text"><%=articleList.getContent().get(i)%></p>
				</div>
				<div class="card-footer">
					<b>카테고리: <%=articleList.getCategory().get(i) %></b><br/>
				</div>
			</div>
			<%} }%>
			<%
		    }else{
				out.println("등록된 글이 없습니다.");
			}
			%>
		</div>
	</section>
	<section class = "container" id="pageList">
		<%if(nowPage<=1){ %>
		[이전]&nbsp;
		<%}else{ %>
		<a href="cateList.do?page=<%=nowPage-1 %>&&category=<%=category%>">[이전]</a>&nbsp;
		<%} %>

		<%for(int a=startPage;a<=endPage;a++){
				if(a==nowPage){%>
		[<%=a %>]
		<%}else{ %>
		<a href="cateList.do?page=<%=a %>&&category=<%=category%>">[<%=a %>]
		</a>&nbsp;
		<%} %>
		<%} %>

		<%if(nowPage>=maxPage){ %>
		[다음]
		<%}else{ %>
		<a href="cateList.do?page=<%=nowPage+1 %>&&category=<%=category%>">[다음]</a>
		<%} %>
	</section>
	<footer class="mt-5" id="copyright">		
		copyright &copy; by Designart All Right Reserves
	</footer>
	
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
<script>
$(function(){
	$("#search").keydown(function(key){
		if(key.keyCode == 13) {
			$("#searchForm").submit();								
		}
	});
});
</script>
<script>
		if(self.name != 'reload') {
			self.name = 'reload';
			self.location.reload(true);
		} else self.name = '';
</script>
</html>