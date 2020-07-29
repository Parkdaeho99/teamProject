<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
    <link rel = "stylesheet" href = "css/main.css">
    <title>회원 가입 페이지</title>
  </head>
  <body>
	<header class="container mt-5">
		<div class="col-2" id="logo" onclick="location.href='index.do'" style="cursor: pointer;"><img src="imgs/logo.PNG" alt="Logo"/></div>
		<div class="col-2 ml-5" id = "board"><button onclick="location.href='boardList.do'">전체 리뷰</button></div>
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
  	<form name = "joinForm" action = "joinAction.do" method = "post">
    <div class = "container">
		<h2 class = "text-center mt-5">회원 가입 페이지</h2>
		<div>
			<table class="table table-bordered">
				<tbody>
					<tr>
						<th scope="row">* 이름</th>
						<td>
							<div class="form-group">
								<input type="text" class="form-control col-6" name = "name" id="name" required="required">
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">* 회원 아이디(이메일)</th>
						<td>
							<div class="form-inline">
								<input type="email" class="form-control col-6" name = "id" id="id" required="required">
								<button type="button" class="btn btn-secondary col-2 ml-1" name = "idCheck" id = "idCheck">중복확인</button>
								<small class="form-text text-muted ml-3"> * 이용 중이신 이메일 주소를 입력해 주세요.</small>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">* 비밀번호</th>
						<td>
							<div class="form-inline">
								<input type="password" class="form-control col-6 pas" name = "pass" id="pass" required="required">
								<small class="form-text text-muted ml-3"> 영문 또는 영문/숫자의 조합 (6~12자 이내)</small>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">* 비밀번호 확인</th>
						<td>
							<div class="form-inline">
								<input type="password" class="form-control col-6 pas" id="passT" name="passT" required="required">
								<small class="form-text text-muted ml-3"> 비밀번호 확인을 위해 다시 한번 입력해주세요.</small>
								<div id="passDanger">비밀번호가 일치하지 않습니다.</div>
								<div id="passSuccess">비밀번호가 일치합니다.<br></div>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">* 이동통신사</th>
						<td>
							<select class="custom-select col-1" name="phone" id="phone" required="required">
								<option value="SKT">SKT</option>
								<option value="LG">LG</option>
								<option value="KT">KT</option>
								<option value="others">알뜰폰</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">* 휴대전화번호</th>
						<td>
							<div class = "form-inline">
								<select class="custom-select col-2" name="pcall1" id="call1" required="required">
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="017">017</option>
									<option value="050">050</option>
								</select>&nbsp;-&nbsp; 
								<input type="text" class="form-control col-3" name = "pcall2" id="call2" maxlength = "4"required="required">&nbsp;-&nbsp;
								<input type="text" class="form-control col-3" name = "pcall3" id="call3" maxlength = "4"required="required">
								<button type="submit" id = "phoneCheck" class="btn btn-secondary col-2 ml-1">인증번호 받기</button>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">* 성별</th>
						<td>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender" id="gender1" value="남" checked="checked">
								<label class="form-check-label" for="gender1">남</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="gender" id="gender2" value="여">
								<label class="form-check-label" for="gender2">여</label>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">* 생년월일</th>
						<td>
								<input type = "date" name = "birth"/>
						</td>
					</tr>
					<tr>	
						<th scope="row">* 이메일 수신동의</th>
						<td>
							<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="eAgree" id="agree1" value="agree" checked="checked">
									<label class="form-check-label" for="agree1">수신함</label>
								</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="eAgree" id="disagree1" value="disagree">
								<label class="form-check-label" for="disagree1">수신안함</label>
							<small class="form-text text-muted ml-3"> * 가입하신 Email로 학습정보 등 정보성 메일이 발송됩니다.</small>
							</div>
						</td>
					</tr>
					<tr>	
						<th scope="row">* SMS 수신동의</th>
						<td>
							<div class="form-check form-check-inline">
									<input class="form-check-input" type="radio" name="sAgree" id="agree2" value="agree" checked="checked">
									<label class="form-check-label" for="agree2">수신함</label>
								</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="sAgree" id="disagree2" value="disagree">
								<label class="form-check-label" for="disagree2">수신안함</label>
							</div>
						</td>
					</tr>
				
				</tbody>
				</table>
		</div>
		<button id = "submit" type = "submit" class="btn btn-primary btn-lg float-right">회원가입</button>
	</div>
	</form>
  </section>
  <footer id="copyright">		
		copyright &copy; by Egg All Right Reserves
	</footer>
  </body>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
	<script>
		var btnIdCheck = document.getElementById("idCheck");
		btnIdCheck.onclick = function(){
			var memid = document.getElementById("id");
			location.href = 'idCheck.do?id=' + memid.value;
		}
		
		$("#phoneCheck").click(function(){
			location.href="unimplemented.jsp";
		});
	</script>
	<script>
		$(function() {
			$("#passSuccess").hide();
			$("#passDanger").hide();
			
			$(".pas").keyup(function() {
				var pass1 = $("#pass").val();
				var pass2 = $("#passT").val();
				if(pass1 == pass2) {
					console.log("비밀번호 일치");
					$("#submit").removeAttr("disabled");
					$("#passDanger").hide();
					$("#passSuccess").show().css("color","blue");
				} else {
					console.log("비밀번호 불일치");
					$("#submit").attr("disabled","disabled");
					$("#passSuccess").hide();
					$("#passDanger").show().css("color","red");
				}
			});
		
		});
	</script>
</html>