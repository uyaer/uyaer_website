<%@page import="com.uyaer.ad.vo.GameAdVo"%>
<%@page import="com.uyaer.ad.vo.GameAdListVo"%>
<%@page import="com.uyaer.ad.db.AdProxy"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'game_ad_list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<%
		int gameid = Integer.parseInt(request.getParameter("gameid"));
		GameAdListVo gameAdListVo = AdProxy.getGameAdChannel(gameid);
	%>
	<%=gameAdListVo.game.name%>
	<hr />
	<table width="100%">
		<tr>
			<td width="50%">广告商</td>
			<td width="50%">开启状态</td>
		</tr>
		<%
			int len = gameAdListVo.adlist.size();
			for (int i = 0; i < len; i++) {
				GameAdVo gameAdVo = gameAdListVo.adlist.get(i);
		%>
		<tr>
			<td><%=gameAdVo.ad.name%></td>
			<td>
				<%
					if (gameAdVo.isOpen == 0) {
							out.print("关闭");
						} else {
							out.print("打开");
						}
				%>
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
