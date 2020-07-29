<%@page import="vo.Reviews"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	
	String imgPath = "/boardUpload/";
	int i=0;
	int []cate1 = new int[4];//패션
	int []cate2 = new int[4];//미용
	int []cate3 = new int[4];//유아동
	int []cate4 = new int[4];//식품
	int []cate5 = new int[4];//숙박
	int []cate6 = new int[4];//맛집
	int []cate7 = new int[4];//디지털
	int []cate8 = new int[4];//생활용품
	int []cate9 = new int[4];//앱리뷰
	int []cate10 = new int[4];//지역
	int []cate11 = new int[4];//웹서비스
	int []cate12 = new int[4];//기타
	String id = (String) session.getAttribute("id");
%>
<%
	Reviews reviews = (Reviews) request.getAttribute("reviews");
%>
<%
	if (reviews == null) {
%> 
	<jsp:forward page="index.do"></jsp:forward>
<%	} else {
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("패션")) {
				cate1[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("미용")) {
				cate2[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			} 
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("유아동")) {
				cate3[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("식품")) {
				cate4[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			} 
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("숙박")) {
				cate5[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("맛집")) {
				cate6[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("디지털")) {
				cate7[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("생활용품")) {
				cate8[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("앱리뷰")) {
				cate9[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("지역")) {
				cate10[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("웹서비스")) {
				cate11[i] = j;
				i++;
				if(i >= 4) {
					break;
				}
			}
		}
		i=0;
		for(int j=0; j<reviews.getCategory().size(); j++) {
			if(reviews.getCategory().get(j).equals("기타")) {
				cate12[i] = j;
				i++;
				if(i >= 4) {
					break;
				}	
			}
		}
		i=0;
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
	<div id = "popup">
		<div id = "pHead">
			공지사항
		</div>
		<div id = "pBody">
			타인의 권리를 침해하거나,<br/> 허위사실 유포, <br/>지나친 비난은 법적인 조치가<br/> 이루어질 수 있습니다.
		</div>
		<div id = "pFoot">
			<form name = "closePopup">
			<input type = "checkbox" name = "option" value = "checkbox"><small>오늘 하루 이 창을 열지 않음</small>		
			<a href="javascript:closeWin();">  <B>[닫기]</B> </a>
			</form>
		</div>
	</div>
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
	<section class="container" style="height: 300px; overflow: hidden;">
		<div id="carouselExampleControls" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="imgs/banner_01.jpg" class="d-block w-100" alt="a">
				</div>
				<div class="carousel-item">
					<img src="imgs/banner_02.jpg" class="d-block w-100" alt="b">
				</div>
				<div class="carousel-item">
					<img src="imgs/banner_03.jpg" class="d-block w-100" alt="c">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</section>

	<nav class="container mt-5">
		<h3>전체 카테고리</h3>
		<div class="card-group" id = "totalCate">
			<div class="card" onclick = "location.href = 'cateList.do?category='+'패션'">
				<img src="imgs/fashion_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">패션/뷰티</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'미용'">
				<img src="imgs/style_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">미용</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'유아동'">
				<img src="imgs/child_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">유아동</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'식품'">
				<img src="imgs/health_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">식품/건강</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'숙박'">
				<img src="imgs/hotel_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">숙박</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'맛집'">
				<img src="imgs/restaurant_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">맛집</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'디지털'">
				<img src="imgs/digital_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">디지털</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'생활용품'">
				<img src="imgs/life_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">생활용품</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'앱리뷰'">
				<img src="imgs/app_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">앱 리뷰</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'지역'">
				<img src="imgs/local_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">지역/문화</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'웹서비스'">
				<img src="imgs/service_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">웹 서비스</h5>
				</div>
			</div>
			<div class="card" onclick = "location.href = 'cateList.do?category='+'기타'">
				<img src="imgs/etc_on.svg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">기타</h5>
				</div>
			</div>
		</div>
	</nav>

	<section class="container mt-5 mb-5">
		<h3>최신 리뷰</h3>
		<!-- 	<./index.do -->
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item" role="presentation">
				<a id="menu1" class=" nav-link active" data-toggle="tab" href="#con1" role="tab">패션/뷰티</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu2" class=" nav-link" data-toggle="tab" href="#con2" role="tab">미용</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu3" class="nav-link" data-toggle="tab" href="#con3" role="tab">유아동</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu4" class="nav-link" data-toggle="tab" href="#con4" role="tab">식품/건강</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu5" class="nav-link" data-toggle="tab" href="#con5" role="tab">숙박</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu6" class="nav-link" data-toggle="tab" href="#con6" role="tab">맛집</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu7" class="nav-link" data-toggle="tab" href="#con7" role="tab">디지털</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu8" class="nav-link" data-toggle="tab" href="#con8" role="tab">생활용품</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu9" class="nav-link" data-toggle="tab" href="#con9" role="tab">앱 리뷰</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu10" class="nav-link" data-toggle="tab" href="#con10" role="tab">지역/문화</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu11" class="nav-link" data-toggle="tab" href="#con11" role="tab">웹 서비스</a>
			</li>
			<li class="nav-item" role="presentation">
				<a id="menu12" class="nav-link" data-toggle="tab" href="#con12" role="tab">기타</a>
			</li>
		</ul>
		<div class="container p-0 tab-content mt-1 mt-1" id="myTabContent1">
			<div class="tab-pane fade show active" id="con1" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate1[z]) == reviews.getNum().get(cate1[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate1[z]).equals("패션")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate1[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate1[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate1[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate1[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate1[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate1[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} %>
					<%} %>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent2">
			<div class="tab-pane fade show active" id="con2" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate2[z]) == reviews.getNum().get(cate2[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate2[z]).equals("미용")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate2[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate2[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate2[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate2[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate2[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate2[z]) %></b>
						</div>
					</div>
					<%}else{ %>
						<div class="card">
							<div class="card-body">
								<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
									등록된 리뷰가 없습니다.
								</h5>
							</div>
						</div>
					<%} %>
					<%} %>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent3">
			<div class="tab-pane fade show active" id="con3" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate3[z]) == reviews.getNum().get(cate3[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate3[z]).equals("유아동")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate3[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate3[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate3[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate3[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate3[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate3[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} %>
				<%} %>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent4">
			<div class="tab-pane fade show active" id="con4" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate4[z]) == reviews.getNum().get(cate4[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate4[z]).equals("식품")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate4[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate4[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate4[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate4[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate4[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate4[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} %>
				<%} %>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent5">
			<div class="tab-pane fade show active" id="con5" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate5[z]) == reviews.getNum().get(cate5[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate5[z]).equals("숙박")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate5[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate5[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate5[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate5[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate5[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate5[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} }%>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent6">
			<div class="tab-pane fade show active" id="con6" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate6[z]) == reviews.getNum().get(cate6[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate6[z]).equals("맛집")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate6[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate6[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate6[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate6[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate6[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate6[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} }%>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent7">
			<div class="tab-pane fade show active" id="con7" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate7[z]) == reviews.getNum().get(cate7[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate7[z]).equals("디지털")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate7[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate7[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate7[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate7[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate7[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate7[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} }%>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent8">
			<div class="tab-pane fade show active" id="con8" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate8[z]) == reviews.getNum().get(cate8[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate8[z]).equals("생활용품")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate8[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate8[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate8[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate8[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate8[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate8[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} }%>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent9">
			<div class="tab-pane fade show active" id="con9" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate9[z]) == reviews.getNum().get(cate9[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate9[z]).equals("앱리뷰")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate9[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate9[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate9[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate9[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate9[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate9[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} }%>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent10">
			<div class="tab-pane fade show active" id="con10" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate10[z]) == reviews.getNum().get(cate10[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate10[z]).equals("지역")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate10[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate10[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate10[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate10[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate10[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate10[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} }%>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent11">
			<div class="tab-pane fade show active" id="con11" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate11[z]) == reviews.getNum().get(cate11[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate11[z]).equals("웹서비스")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate11[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate11[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate11[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate11[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate11[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate11[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} }%>
				</div>
			</div>
		</div>
		<div class="container p-0 tab-content mt-1" id="myTabContent12">
			<div class="tab-pane fade show active" id="con12" role="tabpanel">
				<div class="card-deck">
				<%for(int z=0; z<4; z++){ %>
				<%if(z > 0 && reviews.getNum().get(cate12[z]) == reviews.getNum().get(cate12[0])){ %>
				<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
					<% } else if(reviews.getCategory().get(cate12[z]).equals("기타")){ %>
					<div class="card" onclick="location.href='reviewReadForm.do?board_num=<%=reviews.getNum().get(cate12[z])%>'">
						<img src="<%=imgPath+reviews.getImgPath().get(cate12[z]) %>" class="card-img-top" alt="왜 안나와">
						<div class="card-body">
							<small class="text-muted"><%="작성자: "+reviews.getUser().get(cate12[z]) %></small>
							<h5 style="white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
								<%=reviews.getTitle().get(cate12[z])%>
							</h5>
							<p class="card-text">
								<%=reviews.getContent().get(cate12[z]) %>
							</p>
						</div>
						<div class="card-footer">
							<b>카테고리: <%=reviews.getCategory().get(cate12[z]) %></b>
						</div>
					</div>
					<%}else{ %>
					<div class="card">
					<div class="card-body">
						<h5 style="line-height:400px; white-space: nowrap; overflow:hidden;" class="mt-2 card-title">
							등록된 리뷰가 없습니다.
						</h5>
					</div>
				</div>
				<%} } %>
				</div>
			</div>
		</div>
	</section>
	<footer id="copyright">		
		copyright &copy; by Egg All Right Reserves
	</footer>

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<script>
	
	$(function() {
		$(".tab-content").hide();
		$("#myTabContent1").show();
		
		<%for(int x=1; x<13; x++) {%>
		
		$("#menu<%=x%>").click(function(){
			$(".tab-content").hide();
			$("#myTabContent<%=x%>").show();
		});
		<%}%>
		
		$("#search").keydown(function(key){
			if(key.keyCode == 13) {
				$("#searchForm").submit();								
			}
		});	
	});
	
	</script>
		<script>
	cookiedata = document.cookie;   
	if ( cookiedata.indexOf("maindiv=done") < 0 ){     
	    document.all['popup'].style.visibility = "visible";
	    }
	    else {
	        document.all['popup'].style.visibility = "hidden";
	}
	function setCookie( name, value, expiredays ) {
	    var todayDate = new Date();
	        todayDate.setDate( todayDate.getDate() + expiredays );
	        document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	    }

	function closeWin() {
	    if ( document.closePopup.option.checked ){
	        setCookie( "maindiv", "done" , 1 );
	    }
	    document.all['popup'].style.visibility = "hidden";
	}
	$("#pFoot > button").click(function(){
		$("#popup").fadeOut();
	});
	</script>
	<script>
		if(self.name != 'reload') {
			self.name = 'reload';
			self.location.reload(true);
		} else self.name = '';
	</script>
</body>
</html>