<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.empauthcategory.model.*"%>

<%
	EmpAuthCategoryService eacSvc = new EmpAuthCategoryService();
List<EmpAuthCategoryVO> list = eacSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>

<head>
<title>權限功能管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/empauthcategory/css/css.css">
</head>
<header id="header" class=""> </header>
</head>

<body bgcolor='white'>


	<div id="mySidebar" class="sidebar">
		<div>
			<img id="logo"
				src="<%=request.getContextPath()%>/back-end/empauthcategory/image/Logo2 (2).png"
				class="img-circle" alt="User Image">
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
		</span> <span id="empBack">後台管理<br> <a href="#"> 員工管理 </a> <a
			href="#"> 權限管理 </a>
		</span>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/back-end/emp/EmpLogin.jsp"
			id="sidebarlogin"> 員工登入/登出 </a>
	</div>
	<div id=backSidebar></div>
	<div class="AllFeatureBlock">
		<button class="btn btn-danger"
			onclick="location.href='<%=request.getContextPath()%>/back-end/empauthcategory/addEmpAuthCategory.jsp'">新增權限</button>
		<br>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<table class="table  table-striped">
			<tr class="bg-danger">
				<th>權限編號</th>
				<th>權限功能</th>
				<th>修改</th>
				<th>刪除</th>

			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="eacVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${eacVO.auth_no}</td>
					<td>${eacVO.auth_name}</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/empauthcategory/empac.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" class="btn btn-danger"> <input
								type="hidden" name="auth_no" value="${eacVO.auth_no}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/empauthcategory/empac.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除" class="btn btn-danger"> <input
								type="hidden" name="auth_no" value="${eacVO.auth_no}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
</body>

</html>