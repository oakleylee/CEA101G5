<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/emp/css/css.css">
<title>員工資料新增</title>

<style>
.addEmpBlock {
	width: 30%;
	margin-left: 750px;
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
			href="<%=request.getContextPath()%>/back-end/empauthcategory/listAllEmpAuthCategory.jsp">
				權限管理 </a>
		</span>
	</div>
	<div>
		<a href="<%=request.getContextPath()%>/back-end/emp/EmpLogin.jsp"
			id="sidebarlogin"> 員工登入/登出 </a>
	</div>
	<div id=backSidebar></div>
	<div class="addEmpBlock">
		<%-- 錯誤表列 --%>
		<div align="center">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
		<FORM align="center" METHOD="post"
			ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
			name="form1" enctype="multipart/form-data">
			<table align="center" class="table table-striped">
				<td class="bg-danger" colspan="2"
					style="color: white; text-align: center;">員工新增</td>
				<tr>
					<td>員工姓名:</td>
					<td><input type="TEXT" name="emp_name" size="45"
						value="<%=(empVO == null) ? " 吳永志 " : empVO.getEmp_name()%>" /></td>
				</tr>
				<tr>
					<td>帳號:</td>
					<td><input type="TEXT" name="emp_account" size="45"
						value="<%=(empVO == null) ? " PETER " : empVO.getEmp_account()%>" /></td>
				</tr>
				<tr>
					<td>密碼:</td>
					<td><input type="TEXT" name="emp_pwd" size="45"
						value="<%=(empVO == null) ? " 123456 " : empVO.getEmp_password()%>" /></td>
				</tr>
				<tr>
					<td>雇用日期:</td>
					<td><input name="emp_date" id="f_date1" type="text"></td>
				<tr>
					<td>員工狀態</td>
					<td><select>
							<option>員工狀態</option>
							<option value="0" name="emp_status">在職中</option>
							<option value="1" name="emp_status">已離職</option>
							<option value="2" name="emp_status">留職停薪</option>
					</select>
				</tr>
				<tr>
					<td>圖片</td>
					<td><input type="file" name="emp_image" size="45"></td>
				</tr>

			</table>
			<br> <input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增" class="btn btn-danger"> <a
				href="<%=request.getContextPath()%>/back-end/back-empselect.jsp"
				class="btn btn-danger">回前頁</a>
		</FORM>
	</div>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date emp_date = null;
try {
	emp_date = empVO.getEmp_date();
} catch (Exception e) {
	emp_date = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px;
	/* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
	/* height:  151px; */
}
</style>

<script>
                            $.datetimepicker.setLocale('zh');
                            $('#f_date1').datetimepicker({
                                theme: '', //theme: 'dark',
                                timepicker: false, //timepicker:true,
                                step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
                                format: 'Y-m-d', //format:'Y-m-d H:i:s',
                                value: '<%=emp_date%>', // value:   new Date(),
                                //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
                                //startDate:	            '2017/07/10',  // 起始日
                                //minDate:               '-1970-01-01', // 去除今日(不含)之前
                                //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
                            });

                            // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

                            //      1.以下為某一天之前的日期無法選擇
                            //      var somedate1 = new Date('2017-06-15');
                            //      $('#f_date1').datetimepicker({
                            //          beforeShowDay: function(date) {
                            //        	  if (  date.getYear() <  somedate1.getYear() || 
                            //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
                            //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                            //              ) {
                            //                   return [false, ""]
                            //              }
                            //              return [true, ""];
                            //      }});

                            //      2.以下為某一天之後的日期無法選擇
                            //      var somedate2 = new Date('2017-06-15');
                            //      $('#f_date1').datetimepicker({
                            //          beforeShowDay: function(date) {
                            //        	  if (  date.getYear() >  somedate2.getYear() || 
                            //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
                            //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                            //              ) {
                            //                   return [false, ""]
                            //              }
                            //              return [true, ""];
                            //      }});

                            //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
                            //      var somedate1 = new Date('2017-06-15');
                            //      var somedate2 = new Date('2017-06-25');
                            //      $('#f_date1').datetimepicker({
                            //          beforeShowDay: function(date) {
                            //        	  if (  date.getYear() <  somedate1.getYear() || 
                            //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
                            //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                            //		             ||
                            //		            date.getYear() >  somedate2.getYear() || 
                            //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
                            //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                            //              ) {
                            //                   return [false, ""]
                            //              }
                            //              return [true, ""];
                            //      }});
                        </script>

</html>