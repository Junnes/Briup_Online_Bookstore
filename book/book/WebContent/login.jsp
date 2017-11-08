<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="杰普电子商务门户">
<title>杰普电子商务门户</title>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>" />
<LINK href="css/main.css" rel=stylesheet>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body
	onLoad="MM_preloadImages('images/index_on.gif','images/reg_on.gif','images/order_on.gif','../images/top/topxmas/jp_on.gif','../images/top/topxmas/download_on.gif','../images/top/topxmas/bbs_on.gif','../images/top/topxmas/designwz_on.gif')"
	topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">

	<!-- 引入head.jsp -->
	<%@ include file="head.jsp" %>

	<!--文件体开始-->
	<c:if test="${customer == null && param.login != 'N'}">
		<div style="text-align: center;color:red;font-size: 14pt;margin-bottom: 5px;">
			${param.name == null ? "请先登录" : "注册成功，请登录" }
		</div>
	</c:if>
	
	<table cellspacing=1 cellpadding=3 align=center class=tableBorder2>
		<tr>
			<td height=25 valign=middle><img src="images/Forum_nav.gif"
				align="middle"> <a href=index.jsp>杰普电子商务门户</a> → 用户登录</td>
		</tr>
	</table>
	<br>
	<form action="${pageContext.request.contextPath }/login" method="post"
		name="login">
		<table border="1px" cellpadding=3 cellspacing=1 align=center
			class=tableborder1 width="97%">
			<tr>
				<td valign=middle colspan=2 align=center height=25
					background="images/bg2.gif"><font color="#ffffff"><b>输入您的用户名、密码登录</b></font></td>
			</tr>
			<tr>
				<td valign=middle class=tablebody1 width="45%" align="left">请输入您的用户名</td>
				<td valign=middle class=tablebody1><INPUT name=name type=text onblur="javascript:resetNameInfo1()" onmouseout="javascript:resetNameInfo1()" onfocus="javascript:document.getElementById('loginInfo').innerHTML=''"
					value=<%=request.getParameter("name") == null ? "" : request.getParameter("name")%>>
					<font color="#FF0000" id="nameInfo"></font>
				</td>
			</tr>
			<tr>
				<td valign=middle class=tablebody1 width="45%" align="left">请输入您的密码</td>
				<td valign=middle class=tablebody1><INPUT name=password type=password onblur="javascript:resetPasswordInfo1()" onmouseout="javascript:resetPasswordInfo1()" onfocus="javascript:document.getElementById('loginInfo').innerHTML=''" />
					<font color="#FF0000" id="passwordInfo"></font>
				</td>
			</tr>
			<tr>
				<td class=tablebody2 valign=middle colspan=2 align=center
					bordercolor="white"><span style="color: red; font-size: 13px;" id="loginInfo">
						${failLogin != null ? failLogin : "" } 
						<% session.removeAttribute("failLogin");%>
				</span></td>
			</tr>
			<tr>
				<td class=tablebody2 valign=middle colspan=2 align=center>
					<div>
						<span><input type="button" value="登 录"
							onclick="javascript:checkMe()"></span> <span
							style="margin-left: 10px;"><input type="button"
							value="注 册" onclick="javascript:location.href='register.jsp'"/></span>
					</div>
				</td>
			</tr>
		</table>
	</form>

	<!-- 引入footer.jsp -->
	<%@ include file="foot.jsp" %>
</body>
</html>