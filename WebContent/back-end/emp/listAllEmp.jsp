<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<!-- 此頁練習採用 EL 的寫法取值 -->

<%
	EmpService empSvc = new EmpService();
List<EmpVO> list = empSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>

<head>
<title>員工資料</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/emp/css/css.css">
<style>
.lissAllEmpBlock {
	width: 85%;
	margin-left: 250px;
	margin-top: 20px;
}
</style>

</head>

<body bgcolor='white'>
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
		</span> <span id="empBack">後台管理<br> <a href="#"> 員工管理 </a> <a
			href="#"> 權限管理 </a>
		</span>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/back-end/emp/EmpLogin.jsp"
			id="sidebarlogin"> 員工登入/登出 </a>
	</div>
	<div id=backSidebar></div>
	<div class="lissAllEmpBlock">
		<button class="btn btn-danger">
			<a href="<%=request.getContextPath()%>/back-end/emp/addEmp.jsp">新增員工</a>
		</button>
		<button class="btn btn-danger">
			<a href="">修改員工權限</a>
		</button>
		<span>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
				style="display: inline;">
				<b>選擇員工編號:</b> <select size="1" name="emp_id" style="width: 100px">
					<c:forEach var="empVO" items="${empSvc.getAll()}">
						<option value="${empVO.emp_id}">${empVO.emp_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出" class="btn btn-danger">
			</FORM>
		</span> <span>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
				style="display: inline;">
				<b>選擇員工姓名:</b> <select size="1" name="emp_id" style="width: 100px">
					<c:forEach var="empVO" items="${empSvc.all}">
						<option value="${empVO.emp_id}">${empVO.emp_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出" class="btn btn-danger">
			</FORM>
		</span> <br>
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
				<th>員工編號</th>
				<th>員工姓名</th>
				<th>帳號</th>
				<th>密碼</th>
				<th>到職日</th>
				<th>員工狀態</th>
				<th>員工照片</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">

				<tr>
					<td>${empVO.emp_id}</td>
					<td>${empVO.emp_name}</td>
					<td>${empVO.emp_account}</td>
					<td>${empVO.emp_password}</td>
					<td>${empVO.emp_date}</td>
					<td>${empVO.emp_status}</td>
					<td><img width=100 height=100
						src="<%=request.getContextPath()%>/back-end/emp/emp.do?emp_id=${empVO.emp_id}&action=getEmpPic"></td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="修改" class="btn btn-danger"> <input
								type="hidden" name="emp_id" value="${empVO.emp_id}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="刪除" class="btn btn-danger"> <input
								type="hidden" name="emp_id" value="${empVO.emp_id}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	<div align="center"></div>
</body>

</html>