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
	
	<c:if test="${customer != null }">
		<table cellspacing=1 cellpadding=3 align=center class=tableBorder2>
			<tr>
				<td height=25 valign=middle><img src="images/Forum_nav.gif"
					align="middle"> <a href=index.jsp>杰普电子商务门户</a> → 用户信息修改</td>
			</tr>
		</table>
		<br>

	<c:if test="${sucessUpdate != null }">
			<div style="color:red;font-size:15pt;text-align: center">${sucessUpdate }</div>	
	</c:if>
		<form method="post" name="reg" action="${pageContext.request.contextPath }/user/updateCustomer">
			<table border=1 width="97%" cellpadding="3" cellspacing="1"
				align="center" class="tableborder1" id="table1">
				<tr>
					<td valign="middle" colspan="2" align="center" height="25"
						background="images/bg2.gif"><font color="#ffffff"><b>用户信息修改</b></font></td>
				</tr>
			<tr>
				<td width="40%" class="tablebody1"><b>用户名</b>：<br>
					注册用户名长度限制为0－16字节</td>
				<td width="60%" class="tablebody1">
					<input type="hidden" name="id" value="${customer.id }" />
					<input maxLength="8" type="text" size="32" id="userid" name="name" value="${customer.name }" readonly="readonly"
					style="font-family: Tahoma, Verdana, 宋体; font-size: 12px; line-height: 15px; color: #000000">
					<span id="span" style="color:gray;">用户名无法再修改</span>
				</td>
			</tr>
			<tr>
				<td width="40%" class="tablebody1"><b>密码(至少6位，至多8位)</b>：<br>
					请输入密码，区分大小写。<br> 请不要使用任何类似 \'*\'、\' \' 或 HTML 字符'</td>
				<td width="60%" class="tablebody1"><input type="password" value="${customer.password }"
					maxLength="8" size="32" name="password" id="passwordid" onblur="javascript:resetPasswordInfo()" onmouseout="javascript:resetPasswordInfo()"
					style="font-family: Tahoma, Verdana, 宋体; font-size: 12px; line-height: 15px; color: #000000">
					<font color="#FF0000" id="passwordInfo">*</font>
				</td>
			</tr>
			<tr>
				<td width="40%" class="tablebody1"><b>密码(至少6位，至多8位)</b>：<br>
					请再输一遍确认</td>
				<td class="tablebody1"><input type="password" maxLength="8" value="${customer.password }"
					size="32" name="repassword" id="repasswordid" onblur="javascript:resetRePasswordInfo()" onmouseout="javascript:resetRePasswordInfo()"
					style="font-family: Tahoma, Verdana, 宋体; font-size: 12px; line-height: 15px; color: #000000">
					<font color="#FF0000" id="repasswordInfo">*</font></td>
			</tr>
			<tr>
				<td class="tablebody1"><b>联系地址</b>：</td>
				<td class="tablebody1"><input type="text" size="64"
					maxlength="32" name="address" value="${customer.address }"
					style="font-family: Tahoma, Verdana, 宋体; font-size: 12px; line-height: 15px; color: #000000">
				</td>
			</tr>
			<tr>
				<td class="tablebody1"><b>邮编</b>：</td>
				<td class="tablebody1"><input type="text" size="32"
					maxlength="8" name="zip" value="${customer.zip }"
					style="font-family: Tahoma, Verdana, 宋体; font-size: 12px; line-height: 15px; color: #000000">
				</td>
			</tr>

			<tr>
				<td class="tablebody1"><b>手机</b>：</td>
				<td class="tablebody1"><input type="text" size="32" onblur="javascript:resetTelephoneInfo()" onmouseout="javascript:resetTelephoneInfo()"
					maxlength="16" name="telephone" value="${customer.telephone }"
					style="font-family: Tahoma, Verdana, 宋体; font-size: 12px; line-height: 15px; color: #000000">
					<font color="#FF0000" id="telephoneInfo"></font>
				</td>
			</tr>
			<tr>
				<td class="tablebody1"><b>Email地址</b>：<br> 请输入有效的邮件地址</td>
				<td class="tablebody1"><input type="text" maxLength="50" size="32" name="email" value="${customer.email }"
					onblur="javascript:resetEmailInfo()" onmouseout="javascript:resetEmailInfo()"
					style="font-family: Tahoma, Verdana, 宋体; font-size: 12px; line-height: 15px; color: #000000">
					<font color="#FF0000" id="emailInfo"></font>
				</td>
			</tr>
				<tr>
					<td colspan="2" align="center">
						<span style="color:red;font-size: 12pt;">
							${failUpdate == null ? "" : failUpdate}
						</span>
					</td>
				</tr>
				<tr>
					<td class="tablebody2" valign="middle" colspan="2" align="center">
						<input type="button" value="修 改" onclick="javascript:checkReg()" />
						<input type="reset"  value="放 弃"  onclick="javascript:clean()"/>
					</td>
				</tr>
			</table>
		</form>
	</c:if>

	<!-- 引入footer.jsp -->
	<c:import url="${basePath }foot.jsp"></c:import>
</body>
</html>