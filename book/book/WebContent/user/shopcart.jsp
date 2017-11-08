<%@page import="com.briup.bean.ShopCart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<LINK href="css/main.css" rel=stylesheet>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body
	onLoad="MM_preloadImages('images/index_on.gif','images/reg_on.gif','images/order_on.gif','images/top/topxmas/jp_on.gif','images/top/topxmas/download_on.gif','images/top/topxmas/bbs_on.gif','images/top/topxmas/designwz_on.gif')"
	topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">

	<!-- 引入head.jsp -->
	<c:import url="${basePath }head.jsp"></c:import>

	<!--文件体开始-->
	<c:if test="${customer == null }">
		<jsp:forward page="/login.jsp"></jsp:forward>
	</c:if>
	<c:if test="${orderFail != null }">
		<div style="text-align: center;color:red;font-size: 14pt;margin-bottom: 5px;">
			${orderFail }
		</div>
		<c:remove var="orderFail" scope="session" />
	</c:if>
	<c:if test="${customer != null }">
		<table cellspacing=1 cellpadding=3 align=center class=tableBorder2>
			<tr>
				<td height=25 valign=middle><img src="images/Forum_nav.gif"
					align="middle"> <a href=index.jsp>杰普电子商务门户</a> → <img
					border="0" src="images/dog.gif" width="19" height="18"> 购物清单</td>
			</tr>
		</table>
		<br>
		<c:if test="${shopcart.size == 0 }">
			<div style="text-align: center;font-size: 14pt;margin-bottom: 5px;"><a style="color: red;" href="index.jsp">购物车空空，快去购物吧</a></div>
		</c:if>
			<table border="1" width="97%" cellpadding=3 cellspacing=1
				align=center class=tableborder1>
				<tr>
					<td valign=middle align=center height=25
						background="images/bg2.gif" width=""><font color="#ffffff"><b>序号</b></font></td>
					<td valign=middle align=center height=25
						background="images/bg2.gif" width=""><font color="#ffffff"><b>产品名称</b></font></td>
					<td valign=middle align=center height=25
						background="images/bg2.gif" width=""><font color="#ffffff"><b>价格</b></font></td>
					<td valign=middle align=center height=25
						background="images/bg2.gif" width=""><font color="#ffffff"><b>数量</b></font></td>
					<td valign=middle align=center height=25
						background="images/bg2.gif" width=""><font color="#ffffff"><b>合计</b></font></td>
					<td valign=middle align=center height=25
						background="images/bg2.gif" width=""><font color="#ffffff"><b>操作</b></font></td>
				</tr>
				<c:forEach items="${shopcart.map}" var="entry" varStatus="status" >
					<tr>
						<td class=tablebody2 valign=middle align=center width="7%">
							${status.count }
						</td>
						<td class=tablebody1 valign=middle width="48%">
							&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/findBookById?id=${entry.value.book.id }">${entry.value.book.name }</a>
						</td>
						<td class=tablebody2 valign=middle align=center width="6%">${entry.value.book.price }</td>
						<td class=tablebody1 valign=middle align=center width="10%">
							<input id="${entry.value.book.id }num" type="text" name="num" value="${entry.value.num }" size="4" 
								onblur="javascript:
									if(this.value < 1){
										alert('对不起，产品数量不能小于 1');
										this.value='${entry.value.num }';
										document.getElementById('btn').setAttribute('disabled','disabled');
									}else if (!Number.isInteger(Number(this.value))){
										alert('请输入正确的数量');
										this.value='${entry.value.num }';
										document.getElementById('btn').setAttribute('disabled','disabled');
									}"
								onfocus="javascript:document.getElementById('btn').removeAttribute('disabled');"
							/>
						</td>
						<td class=tablebody2 valign=middle align=center width="12%">
							￥${entry.value.book.price * entry.value.num }
						</td>
						<td class=tablebody1 valign=middle align=center width="">
							<input id="btn" type="button" value="保存" onclick="javascript:location.href='${pageContext.request.contextPath}/user/updateLine?id=${entry.value.book.id }&num='+document.getElementById('${entry.value.book.id }num').value">
							<input type="button" value="移除" onclick="javascript:if(confirm('确认要移除吗?'))location.href='${pageContext.request.contextPath}/user/removeLine?id=${entry.value.book.id }'">
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td class=tablebody1 valign=middle align=center colspan="4"></td>
					<td class=tablebody1 valign=middle align=center width=""><font
						color="#ff0000"><b>
						<%-- ￥${shopcart.cost == null ? "0.0" : shopcart.cost } --%>
						￥<%=(ShopCart)session.getAttribute("shopcart") == null ? "0.0" : ((ShopCart)session.getAttribute("shopcart")).getCost()%>
						</b></font></td>
					<td class=tablebody1 valign=middle align=center width=""></td>
				</tr>
				<tr>
					<td class=tablebody2 valign=middle align=center colspan="6">
						<input type="button" value="继续购物" onclick="javascript:window.location='<%=basePath%>index.jsp';">
						<c:if test="${shopcart.size != 0 }">
							<input type="button" value="清空购物车" onclick="javascript:if(confirm('确认要清空购物车吗?'))location.href='<%=basePath%>user/removeAllLine'">
							<input type="button" value="提交订单" onclick="javascript:location.href='<%=basePath%>user/confirmOrder.jsp';">
						</c:if>
					</td>
				</tr>
			</table>
	</c:if>


	<!-- 引入footer.jsp -->
	<c:import url="${basePath }foot.jsp"></c:import>
</body>
</html>