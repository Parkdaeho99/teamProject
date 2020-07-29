<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String id = (String)session.getAttribute("id");
	if(id == null) {
		out.println("<script>");
		out.println("alert('로그인 후 작성 가능합니다.');");
		out.println("history.back();");
		out.println("</script>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/main.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
			<a style="float:right;" href="./memberSearchForm.do" onclick="window.open(this.href, '_blank', 'width=500px,height=300px,toolbars=no,scrollbars=no'); return false;">멤버 관리</a></b>
			</div>
		<% } %>
	</header>
	<div class="clearfix"></div>
	<hr/>
	<section class = "container">
		<form name = "boardWrite" action = "boardWritePro.do" method = "post" enctype="multipart/form-data">
			<div class="form-group">
				<select class="custom-select custom-select-lg mb-3" name = "category" id = "category">
					<option value="패션">패션/뷰티</option>
					<option value="미용">미용</option>
					<option value="유아동">유아동</option>
					<option value="식품">식품/건강</option>
					<option value="숙박">숙박</option>
					<option value="맛집">맛집</option>
					<option value="디지털">디지털</option>
					<option value="생활용품">생활용품</option>
					<option value="앱리뷰">앱 리뷰</option>
					<option value="지역">지역/문화</option>
					<option value="웹서비스">웹 서비스</option>
					<option value="기타">기타</option>
				</select>
			</div>
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" class="form-control" name = "title" id="title" required maxlength="30">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" name = "content" id="content" rows="3" style="height:300px;" required maxlength="2000"></textarea>
			</div>
			<div class="form-group">
				<label for="image">이미지 파일 첨부</label><br/>
				<input type="file" style="width:500px;" name= "image"  id = "image" accept="image/*" multiple onchange="fileInfo(this)" required/><br>
				<input type="file" style="width:500px;" name= "image2"  id = "image" accept="image/*" multiple onchange="fileInfo(this)" /><br>
				<input type="file" style="width:500px;" name= "image3"  id = "image" accept="image/*" multiple onchange="fileInfo(this)" /><br>
				<div id="img_box"></div>
			</div>
			<button type="submit" class="btn btn-primary" >리뷰 작성</button>
		</form>
	</section>
	
  	<footer class="mt-5" id="copyright">		
		copyright &copy; by Designart All Right Reserves
	</footer>
</body>
<script>
	
	function fileInfo(f){
		var file = f.files; // files 를 사용하면 파일의 정보를 알 수 있음

		// 파일의 갯수만큼 반복
		for(var i=0; i<file.length; i++){
			$("#img_box").empty();
			var reader = new FileReader(); // FileReader 객체 사용
			reader.onload = function(rst){
				$('#img_box').append('<img src="' + rst.target.result + '">'); // append 메소드를 사용해서 이미지 추가
				// 이미지는 base64 문자열로 추가
				// 이 방법을 응용하면 선택한 이미지를 미리보기 할 수 있음
			}
			reader.readAsDataURL(file[i]); // 파일을 읽는다

		}
	}
	

</script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</html>