<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
	MemService memSvc = new MemService();
List<MemVO> list = memSvc.getAll();
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
	href="<%=request.getContextPath()%>/back-end/emp/css/css.css">
<style>
.main img {
	margin-left: 350px;
	width: 50%;
	filter: grayscale(100%);
}

.main p {
	margin-left: 460px;
	font-size: 36px;
	color: gray;
}
</style>
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
		</span>
	</div>
	<div>
		<script>
			
		</script>
		<a href="<%=request.getContextPath()%>/back-end/emp/EmpLogin.jsp"
			id="login"> 員工登入/登出 </a>
	</div>
	<div id=backSidebar></div>
	<div class="main">
		<img
			src="<%=request.getContextPath()%>/back-end/emp/image/cryface.png">
		<p>您沒有權限，請洽您的上級主管開通權限</p>
	</div>


</body>

</html>