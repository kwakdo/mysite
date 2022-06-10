<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
						<table class="tbl-ex">				
							<tr>
								<th colspan="2">글보기</th>
							</tr>
							<tr>
								<td class="label">제목</td>
								<td>${boardVo.title }</td>
							</tr>
							<tr>
								<td class="label">내용</td>
								<td>
									<div class="view-content">
										${fn:replace(boardVo.contents, newLine, "<br>") }
									</div>
								</td>
							</tr>
						</table>
						<div class="bottom">
							<a href="${pageContext.request.contextPath }/board">글목록</a>
							<c:choose>
								<c:when test="${empty authUser }"></c:when>
								<c:when test="${authUser.no eq vo.userNo }">
									<a href="${pageContext.request.contextPath }/board/modifyform/${vo.no}">글수정</a>
									<a href="${pageContext.request.contextPath }/board/writeform/${vo.no}&groupNo=${vo.groupNo}&orderNo=${vo.orderNo}&depth=${vo.depth}">답글달기</a>
								</c:when>
								<c:otherwise>
									<a href="${pageContext.request.contextPath }/board/writeform/${vo.no}&groupNo=${vo.groupNo}&orderNo=${vo.orderNo}&depth=${vo.depth}">답글달기</a>																							
								</c:otherwise>
							</c:choose>
						</div>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
</body>
</html>