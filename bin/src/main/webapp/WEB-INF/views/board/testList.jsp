<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<my:testNavBar></my:testNavBar>
	<div class="container-md">
		<div class="row">
			<div class="col">
			<c:if test="${not empty message }">
				<div class="alert alert-success">
					${message }
				</div>
			</c:if>
			
				<h3>연습 게시판 목록</h3>
				<table class="table">
					<thead>
						<tr>
							<th>#</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardList}" var="board">
							<tr>
								<td>${board.id }</td>
								<td><c:url value="/board/testGet" var="getLink">
										<c:param name="id" value="${board.id }"></c:param>
									</c:url> <a href="${getLink}"> ${board.title } </a></td>
								<td>${board.writer }</td>
								<td>${board.inserted }</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				
				<!-- pagination -->
			<div class="row">
				<div class="col">
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
					
						<c:if test="${pageInfo.currentPageNumber ne 1 }">
							<c:url value="/board/testList" var="listLink">
								<c:param name="page" value="1" />
							</c:url>
							<li class="page-item"><a class="page-link"
								href="${listLink }" aria-label="Previous"> <span
									aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<c:forEach begin="1" end="${testPageInfo.lastPageNumber }" var="pageNumber">
							<c:url value="/board/testList" var="listLink" >
								<c:param name="page" value="${pageNumber}"/>
							</c:url>
							<li class="page-item"><a class="page-link" href="${listLink}">${pageNumber }</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
				</div>
			</div>
			</div>
		</div>
	</div>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<scipt>
</scipt>
</body>
</html>