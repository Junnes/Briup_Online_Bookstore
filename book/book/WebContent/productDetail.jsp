<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="杰普电子商务门户">
<title>杰普电子商务门户</title>
<LINK href="css/main.css" rel=stylesheet>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body
	onLoad="MM_preloadImages('images/index_on.gif','images/reg_on.gif','images/order_on.gif','../images/top/topxmas/jp_on.gif','../images/top/topxmas/download_on.gif','../images/top/topxmas/bbs_on.gif','../images/top/topxmas/designwz_on.gif')"
	topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0">
	
	<!-- 引入head.jsp -->
	<%@ include file="head.jsp" %>

	<!--文件体开始-->
	<table cellspacing=1 cellpadding=3 align=center class=tableBorder2>
		<tr>
			<td height=25 valign=middle><img src="images/Forum_nav.gif"
				align="middle"> <a href=index.jsp>杰普电子商务门户</a> → 产品明细</td>
		</tr>
	</table>
	<br>

	<table border="1" cellpadding=3 cellspacing=1 align=center class=tableborder1 width="97%">
		<tr>
			<td valign=middle align=center height=25 background="images/bg2.gif" colspan="2">
				<font color="#ffffff"><b>${book.name }</b></font>
			</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="20%">【
				作&nbsp; 者 】</td>
			<td class=tablebody1 valign=middle width="80%">&nbsp;&nbsp;${book.writer }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="20%">【
				价&nbsp; 格 】</td>
			<td class=tablebody1 valign=middle width="80%">&nbsp;&nbsp;${book.price }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">【出 版 社】</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;${book.publish }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">【
				书&nbsp; 号 】</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;${book.id }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">【
				页&nbsp; 码 】</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;${book.pages }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width="">【所属类别】</td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;${book.category.name }</td>
		</tr>
		<tr>
			<td class=tablebody1 align=center width="" valign="top">【
				简&nbsp; 介 】</td>
			<td class=tablebody1 valign=middle width="">${book.description }</td>
		</tr>
		<tr>
			<td class=tablebody1 valign=middle align=center width=""></td>
			<td class=tablebody1 valign=middle width="">&nbsp;&nbsp;<img
				border="0" src="${book.image }" width="127" height="180"></td>
		</tr>
		<tr>
			<td class=tablebody2 valign=middle align=center colspan="2"><a
				href="${pageContext.request.contextPath }/user/shopcart?id=${book.id }"> <img border="0" src="images/buycar.gif"
					width="92" height="21"></a></td>
		</tr>
	</table>

	<!-- 引入footer.jsp -->
	<%@ include file="foot.jsp" %>
	
</body>
</html>