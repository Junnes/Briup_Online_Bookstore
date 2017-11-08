 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("basePath", basePath);
%>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="杰普电子商务门户">
<title>杰普电子商务门户</title>
<LINK href="css/main.css" rel="stylesheet">
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
</head>
<body
	onLoad="MM_preloadImages('images/index_on.gif','images/reg_on.gif','images/order_on.gif','../images/top/topxmas/jp_on.gif','../images/top/topxmas/download_on.gif','../images/top/topxmas/bbs_on.gif','../images/top/topxmas/designwz_on.gif')"
	topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
		<!-- 引入head.jsp -->
	<%@ include file="head.jsp" %>

	<!--文件体开始-->
	<c:if test="${welcomeLogin != null }">
		<div style="text-align: center;color:red;font-size: 14pt;margin-bottom: 5px;">
			${welcomeLogin }
		</div>
		<c:remove var="welcomeLogin" scope="session" />
	</c:if>
	<table cellspacing="1" cellpadding="3" align="center" class="tableBorder2">
		<tr>
			<td height="25" valign="middle"><img src="images/Forum_nav.gif"
				align="middle"> <a href="index.jsp">杰普电子商务门户</a> → <img
				border="0" src="images/dog.gif" width="19" height="18"> 
				<c:if test="${customer != null }">
					<span><a style="color: green;" href="${basePath }user/userinfo.jsp">欢迎您:${customer.name }</a></span>
				</c:if>
				<c:if test="${customer == null }">
					<span><a href="${basePath }login.jsp?login=N" style="color: red;">您还未登陆，请登陆</a></span>
				</c:if>
			</td>
		</tr>
	</table>
	<br>
	<script type="text/javascript">
			$(function(){
				$("#search").keyup(function(){
					var bookName = $(this).val();
					if(bookName == ""){
						$("#showDiv").css("display","none");
						return;
					}
					var content = "";
					$.get("findBookByName",
							{"bookName":bookName},
							function(data){
								if(data.length > 0){
									for(var i = 0;i < data.length;i++){
										content += "<li><a style='color:blue;margin-top:30px;' href='findBookById?id="+data[i].id+"'>"+data[i].name+"</a></li>"
									}
									$("#showBook").html(content);
									$("#showDiv").css("display","block");
								}
							},
							"json"); 
				});
				
			})
		</script>
	<div style="width: 97%;margin: auto;margin-bottom: 5px; ">
		<span><input id="search" type="text" name="bookName" placeholder="输入查找的书名" value="${bookName }"></span>
		<span><input type="button" value="search" id="searchBtn" onclick="javascript:location.href='index.jsp?bookName='+document.getElementById('search').value" /></span>
		<span><input type="button" value="显示所有" onclick="javascript:location.href='index.jsp'" ></span>
		<div id="showDiv" style="width: 260px;border: 1px;background-color: #EFEFEF;display: none;position: absolute;">
			<ul style="margin-left: -20px;" id="showBook">
			</ul>
		</div>
	</div>
	
	
	<table border="1px" cellpadding="3" cellspacing="1" align="center" class="tableborder1" width="97%">
		<tr>
			<td valign="middle" align="center" height="25" background="images/bg2.gif"
				width=""><font color="#ffffff"><b>序号</b></font></td>
			<td valign="middle" align="center" height="25" background="images/bg2.gif"
				width=""><font color="#ffffff"><b>产品名称</b></font></td>
			<td valign="middle" align="center" height="25" background="images/bg2.gif"
				width=""><font color="#ffffff"><b>价格</b></font></td>
			<td valign="middle" align="center" height="25" background="images/bg2.gif"
				width=""><font color="#ffffff"><b>操作</b></font></td>
		</tr>
		<c:forEach items="${pageBean.list }" var="book" varStatus="status" >
			<tr>
				<td class="tablebody2" valign="middle" align="center" width="">${status.count }</td>
				<td class="tablebody1" valign="middle" width="60%">&nbsp;&nbsp;<a
					href="${pageContext.request.contextPath }/findBookById?id=${book.id }">
					${book.name }</a></td>
				<td class="tablebody2" valign="middle" align="center" width="">${book.price }</td>
				<td class="tablebody1" valign="middle" align="center" width="">
				<c:if test="${customer == null }">
					<a href="${pageContext.request.contextPath }/login.jsp"><img border="0" src="images/car_new.gif"
						width="97" height="18"></a>
				</c:if>
				<c:if test="${customer != null }">
					<a href="${pageContext.request.contextPath }/user/shopcart?id=${book.id }"><img border="0" src="images/car_new.gif"
						width="97" height="18"></a>
				</c:if>
				</td>
				
			</tr>
		</c:forEach>
		
		<!-- 分页 -->
		<tr align="center">
			<td colspan="4">
				<!-- <input type="button" id="btnFir" value="首页" > -->
				<c:if test="${pageBean.currentPage != 1 }">
					<input type="button" id="btnUp" value="上一页" >
				</c:if>
				<c:if test="${pageBean.currentPage != pageBean.totalPage  && pageBean.totalPage > 1 }">
					<input type="button" id="btnDown" value="下一页" >
				</c:if>
				<!-- <input type="button" id="btnLast" value="尾页" > -->
				<c:if test="${pageBean.totalPage > 1 }">
					<select>
						<c:forEach begin="1" end="${pageBean.totalPage }" var="page">
							<c:choose>
								<c:when test="${page == pageBean.currentPage }">
									<option selected="selected" value="${page }" >${page }页</option>
								</c:when>
								<c:otherwise>
									<option value="${page }">${page }页</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</c:if>
				<script type="text/javascript">
					$(function(){
						var path = "${pageContext.request.contextPath}/index.jsp?";
						var flag = "${bookName != null}";
						var currentPage = "${pageBean.currentPage }";
						if(flag){
							path = path + "bookName=${bookName }&"
						}
						$("#btnFir").click(function(){
							location.href= path + "currentPage=1";
						})
						$("#btnUp").click(function(){
							location.href= path + "currentPage=${pageBean.currentPage-1 }";
						})
						$("#btnDown").click(function(){
							location.href= path + "currentPage=${pageBean.currentPage+1 }";
						})
						$("#btnLast").click(function(){
							location.href= path + "currentPage=${pageBean.totalPage }";
						})
						$("option").click(function(){
							location.href= path + "currentPage="+$(this).val();
						})
					})
				</script>
			</td>
		</tr>
	</table>
	
	<!-- 引入footer.jsp -->
	<%@ include file="foot.jsp" %>
</body>

</html>




