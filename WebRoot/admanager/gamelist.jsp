<%@page import="com.uyaer.ad.db.AdProxy"%>
<%@page import="com.uyaer.ad.vo.GameVo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gamelist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
#left{
	width:200px;
}
#right{
float:left;
margin-left:220px;
}
</style>
<script type="text/javascript">
	function changeGame(gameid){
		window.document.getElementById("iframe").src = "admanager/game_ad_list.jsp?gameid="+gameid;
	//	window.location.href = "/game_ad_list.jsp?gameid="+gameid;
	}
</script>
  </head>
  
  <body>
  	<h1>游戏列表</h1>
  	<hr/>
  	<div id="left">
  	<ul>
    <%
    	List<GameVo> list = AdProxy.getGameList();
    	for(int i = 0 ; i < list.size(); i ++){
    		GameVo vo = list.get(i);
    %>
    		<li><a href="javascript:changeGame(<%=vo.id%>)"><%=vo.name %></a></li>
    <%
    	}
     %>
     </ul>
  	</div>
  	<div id="right">
  		<iframe id="iframe" frameborder="0" style="width: 100%;height: 100%" ></iframe>
  	</div>
  	
  </body>
</html>
