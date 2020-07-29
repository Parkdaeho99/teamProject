<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>미 구현 페이지</title>
    <link rel = "stylesheet" href = "css/login.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"/>
</head>
<body>
	<header class="container mt-5">
		<div class="col-2" id="logo" onclick="location.href='index.do'" style="cursor: pointer;"><img src="imgs/logo.PNG" alt="Logo"/></div>
		<div class="col-2 ml-5" id = "category"><button onclick="location.href='reviewWriteForm.do'">리뷰 작성</button></div>
		<form id="searchForm" method="post" action="totalSearch.do">
			<input name="keyword" id="search" class="col-5" type="text"
				placeholder="제품명 혹은 모델번호로 검색하세요." required />
		</form>
			<div class="col-2" id="login">
				<b>
				<a href="loginForm.do"> 로그인</a> / 
				<a href="joinForm.do"> 회원가입</a>
				</b>
			</div>
	</header>
	<div class="clearfix"></div>
	<hr/>
    <section>
    	<div class="jumbotron jumbotron-fluid">
  			<div class="container">
  				  <h1 class="display-4" style="text-align: center">미 구현 페이지 입니다.</h1>
   				 <p class="lead" style="text-align: center">추후 업데이트를 통하여 구현될 페이지이니 기다려주시면 감사하겠습니다.</p>
 			 </div>
		</div>
	</section>
	<footer id="copyright">		
		copyright &copy; by Egg All Right Reserves
	</footer>
</body>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
</html>