<%@page import="com.briup.bean.ShopCart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script language="JavaScript" src="js/main.js"></script>
</head>
<body
	onLoad="MM_preloadImages('images/index_on.gif','images/reg_on.gif','images/order_on.gif','images/top/topxmas/jp_on.gif','images/top/topxmas/download_on.gif','images/top/topxmas/bbs_on.gif','images/top/topxmas/designwz_on.gif')"
	topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
	
	<!-- 引入head.jsp -->
	<c:import url="${basePath }head.jsp"></c:import>

	<!--文件体开始-->
	<table width="97%" cellspacing=1 cellpadding=3 align=center
		class=tableBorder2>
		<tr>
			<td height=25 valign=middle><img src="images/Forum_nav.gif"
				align="middle"> <a href=index.jsp>杰普电子商务门户</a> → <img
				border="0" src="images/dog.gif" width="19" height="18">
				确认定单</td>
		</tr>
	</table>
	<br>

	<form name="order" method="post" action="${pageContext.request.contextPath }/user/saveOrder">
		<table border="1" width="97%" cellpadding="3" cellspacing="1"
			align="center" class="tableborder1" id="table1">
			<tr>
				<td valign="middle" colspan="2" align="center" height="25"
					background="images/bg2.gif"><font color="#ffffff"><b>用户信息</b></font><input
					type="button" value="修改"
					onclick="javascript:window.location='<%=basePath%>/user/userinfo.jsp';"></td>
			</tr>
			<tr>
				<td width="40%" class="tablebody2" align="right"><b>用户名</b>：</td>
				<td width="60%" class="tablebody1">${customer.name }</td>
			</tr>
			<tr>
				<td class="tablebody2" align="right"><b>联系地址</b>：</td>
				<td class="tablebody1">${customer.address }</td>
			</tr>
			<tr>
				<td class="tablebody2" align="right"><b>邮编</b>：</td>
				<td class="tablebody1">${customer.zip }</td>
			</tr>
			<tr>
				<td class="tablebody2" align="right"><b>手机</b>：</td>
				<td class="tablebody1">${customer.telephone }</td>
			</tr>
			<tr>
				<td class="tablebody2" align="right"><b>Email地址</b>：</td>
				<td class="tablebody1">${customer.email }</td>
			</tr>
		</table>
		<br>


		<!--文件尾开始-->
		<table border="1" width="97%" cellpadding="3" cellspacing="1"
			align="center" class="tableborder1" id="table2">
			<tr>
				<td valign="middle" colspan="2" align="center" height="25"
					background="images/bg2.gif"><font color="#FFFFFF"><b>付款方式</b></font></td>
			</tr>
			<tr>
				<td width="40%" class="tablebody2" align="right"></td>
				<td width="60%" class="tablebody1"><select name="payment">

						<option value="邮局汇款">邮局汇款</option>

						<option value="支付宝">支付宝</option>

						<option value="银行转帐">银行转帐</option>

				</select></td>
			</tr>
		</table>
		<br>
		<table width="97%" border="1" cellpadding=3 cellspacing=1 align=center class=tableborder1
			id="table3">
			<tr>
				<td valign=middle align=center height=25
					background="images/bg2.gif" colspan="5"><font
					color="#ffffff"><b>商品购物清单</b></font>
					<input type="button" value="修改"
					onclick="javascript:window.location='<%=basePath%>/user/shopcart.jsp';"></td>
			</tr>
			<c:forEach items="${shopcart.map}" var="entry" >
				<tr>
					<td class=tablebody2 valign=middle align=center width="7%">
						${entry.value.book.id }
					</td>
					<td class=tablebody1 valign=middle width="48%">
						&nbsp;&nbsp;<a href="productDetail.jsp?id=${entry.value.book.id }">${entry.value.book.name }</a>
					</td>
					<td class=tablebody2 valign=middle align=center width="6%">价格：${entry.value.book.price }</td>
					<td class=tablebody1 valign=middle align=center width="10%">数量：${entry.value.num }</td>
					<td class=tablebody2 valign=middle align=left width="12%" >&nbsp;小计：￥${entry.value.book.price * entry.value.num }
					</td>
				</tr>
			</c:forEach>

			<tr>
				<td class=tablebody1 valign=middle align=center colspan="4"></td>
				<td class=tablebody1 valign=middle align=left width="13%">&nbsp;合计：<font
					color="#ff0000"><b>￥<%=(ShopCart)session.getAttribute("shopcart") == null ? "0.0" : ((ShopCart)session.getAttribute("shopcart")).getCost()%></b></font></td>
			</tr>
		</table>
		<p align="center">
			请认真检查以上订单信息，确认无误后，点击 → <a
				onclick="javascript:document.order.submit();" style="cursor: hand"><img
				src="images/submit.gif"></a>
		</p>
	</form>

	<!-- 引入footer.jsp -->
	<c:import url="${basePath }foot.jsp"></c:import>
</body>
</html>