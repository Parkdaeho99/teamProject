<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
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
    <section  id = "loginFormArea">
	<h1>로그인</h1>
	<form action="login.do" method = "POST">
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
				<tr>
					<td class = "td_left">
					<label for = "passwd">비밀번호 : </label>
					</td>
					<td class = "td_right">
					<input type = "password" name = "passwd" id = "passwd"/>
					</td>
				</tr>
			</table>
			
			<input type = "submit" value = "로그인" id = "selectButton"/>
			<input type = "button" value = "회원가입" onclick = "location.href='joinForm.do'">
		</fieldset>
	</form>
	</section>
	<footer id="copyright">		
		copyright &copy; by Egg All Right Reserves
	</footer>
</body>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
</html>