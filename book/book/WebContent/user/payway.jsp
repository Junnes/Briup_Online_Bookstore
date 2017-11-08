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
<script language="JavaScript" src="js/main.js"></script>
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
		<c:if test="${orderSucessMsg != null }">
		<div style="text-align: center;color:red;font-size: 14pt;margin-bottom: 5px;">
			${orderSucessMsg }
		</div>
		<c:remove var="orderSucessMsg" scope="session" />
	</c:if>
	<c:if test="${customer != null }">
		<table cellspacing=1 cellpadding=3 align=center class=tableBorder2>
			<tr>
				<td height=25 valign=middle><img src="images/Forum_nav.gif"
					align="middle"> <a href=index.jsp>杰普电子商务门户</a> → <img
					border="0" src="images/dog.gif" width="19" height="18">
					支付页面</td>
			</tr>
		</table>
		<br>
		<center>
		<font color="red" size="3px">请选择支付方式</font>
		<br /><br />
		<form action="payServlet" method="post">
			<input type="hidden" name="orderid"  value="${param.orderid }">
			<input type="radio" name="pay" value="weixin"> <img alt="支付宝" src="images/weixin.png">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="pay" value="zhifubao"> <img alt="微信" src="images/zhifubao.png">
			<br /><br />
			<input type="submit" value="去付钱" >
		</form>
		</center>
	</c:if>

	<!-- 引入footer.jsp -->
	<c:import url="${basePath }foot.jsp"></c:import>
</body>
</html>