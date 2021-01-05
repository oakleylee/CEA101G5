<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>

<%
RestaurantService restSvc = new RestaurantService();
List<RestaurantVO> list = restSvc.getAll();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/restaurant/css/css.css">
</head>
<header id="header" class=""> </header>
<!-- /header -->

<body>
	<div id="mySidebar" class="sidebar">
		<div>
			<img id="logo" src="./image/Logo2 (2).png" class="img-circle"
				alt="User Image">
		</div>
		<br> <br> <br> <br> <br> <span id="empFront">首頁<br>
			<a href="#"> FAQ</a> <a href="#"> 評論審核</a> <a href="#"> 最新消息管理</a>
		</span> <span id="empShop">商城管理<br> <a href="#"> 商品管理</a> <a
			href="#">訂單處理 </a> <a href="#">回覆買家留言 </a> <a href="#">廣告設置管理 </a> <a
			href="#">促銷活動設置 </a>
		</span> <span id="empStore">餐廳管理<br> <a
			href="<%=request.getContextPath()%>/back-end/storeChar/listAllStoreChar.jsp">
				餐廳分類管理 </a> <a href="#"> 餐廳資訊管理 </a> <a href="#"> 餐廳申請審核 </a>
		</span> <span id="empMem">會員管理<br> <a
			href="<%=request.getContextPath()%>/back-end/member/listAllMem.jsp">
				會員資料管理 </a> <a href="#"> 商家註冊審核 </a>
		</span> <span id="empBack">後台管理<br> <a href="#"> 員工管理 </a>
		<a href="#"> 權限管理 </a>
		</span>
	</div>
	<div>

		<a href="#" id="login"> 員工登入/登出 </a>
	</div>
	<div id=backSidebar></div>
	<div class="main">
	<div class="listAllStoreBlock">
		<table id="listAllStore" class="table table-striped">
			<tr class="bg-danger">
				<th>餐廳編號</th>
				<th>餐廳名稱</th>
				<th>餐點地址</th>
				<th>餐點電話</th>
				<th>餐廳分類</th>
				<th>開始營業時間</th>
				<th>結束營業時間</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${restaurantVO.storeId}</td>
					<td>${restaurantVO.storeName}</td>
					<td>${restaurantVO.storeAddress}</td>
					<td>${restaurantVO.storePhone}</td>
					<td>${restaurantVO.storeChar}</td>
					<td>${restaurantVO.storeOpenTime}</td>
					<td>${restaurantVO.storeCloseTime}</td>
					<td>
					<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" class="btn btn-danger"> 
							<input type="hidden" name="memPhone" value="${restaurantVO.storeId}">
							<input type="hidden" name="action" value="">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除" class="btn btn-danger"> 
							<input type="hidden" name="memPhone" value="${restaurantVO.storeId}">
							<input type="hidden" name="action" value="">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	</div>
</body>

</html>