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
<script type="text/javascript">
	window.onload = function() {
		var status = document.getElementsByName("status");
		for (var i = 0; i < status.length; i++) {
			var span = document.getElementById("span"+i);
			if(span.innerHTML == "已付款") {
				var button = document.getElementById("pay"+i);
				button.type = "hidden";
			}
		}
	}
</script>
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
					订单列表</td>
			</tr>
		</table>
		<br>
		<table border="1" width="97%" cellpadding=3 cellspacing=1 align=center class=tableborder1>
			<tr>
				<td valign=middle align=center height=25
					background="images/bg2.gif" width=""><font color="#ffffff"><b>序号</b></font></td>
				<td valign=middle align=center height=25
					background="images/bg2.gif" width=""><font color="#ffffff"><b>订单编号</b></font></td>
				<td valign=middle align=center height=25
					background="images/bg2.gif" width=""><font color="#ffffff"><b>订单金额</b></font></td>
				<td valign=middle align=center height=25
					background="images/bg2.gif" width=""><font color="#ffffff"><b>订单状态</b></font></td>
				<td valign=middle align=center height=25
					background="images/bg2.gif" width=""><font color="#ffffff"><b>付款方式</b></font></td>
				<td valign=middle align=center height=25
					background="images/bg2.gif" width=""><font color="#ffffff"><b>操作</b></font></td>
			</tr>

			<c:forEach items="${orderList }" var="order" varStatus="status" >
				<tr>
					<td class=tablebody2 valign=middle align=center width="6%">${status.count }</td>
					<td class=tablebody1 valign=middle align=center width="10%"><a
						href="${pageContext.request.contextPath }/user/findOrderById?id=${order.id }">${order.id }</a></td>
					<td class=tablebody2 valign=middle align=center width="18%">￥${order.cost }</td>
					<td class=tablebody1 valign=middle align=center width="" name="status">
						<c:choose>
							<c:when test="${order.status == 0 }">
								<span id="span${status.index }">待付款</span>
							</c:when>
							<c:otherwise>
								<span id="span${status.index }">已付款</span>
							</c:otherwise>
						</c:choose>
					
					</td>
					<td class=tablebody2 valign=middle align=center width="15%"><%-- ${order.payment } --%>支付宝
					</td>
					<td class=tablebody1 valign=middle align=center width="">
						<input type="button" value="删除" onclick="javascript:if(confirm('确认要删除吗?'))location.href='${pageContext.request.contextPath }/user/removeLinesByOrderId?id=${order.id }';">&nbsp;&nbsp;
						<input type="button" value="明细" onclick="javascript:location.href='${pageContext.request.contextPath }/user/findOrderById?id=${order.id }';">
						<input type="button" id="pay${status.index }" value="去付款" onclick="javascript:location.href='${pageContext.request.contextPath }/user/payway.jsp?orderid=${order.id }';">
					<!--&nbsp;<input type="button" value="修改"/>--></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<!-- 引入footer.jsp -->
	<c:import url="${basePath }foot.jsp"></c:import>
</body>
</html>