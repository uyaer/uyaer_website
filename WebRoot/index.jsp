<%@page import="com.uyaer.web.db.WebProxy"%>
<%@page import="com.uyaer.web.vo.GameVo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

		String lang = request.getParameter("lang");
		if(lang == null || "".equals(lang) || (!"zh".equals(lang) && !"en".equals(lang))){
			String clientLang = request.getHeader("Accept-Language");
			if(clientLang.indexOf("zh")!=-1){
				lang = "zh";
			}else{
				lang = "en";
			}
		}
		
		List<GameVo> gameList = (List<GameVo>) application.getAttribute("game_"+lang);
		//gameList = null;
		if(gameList==null){
			gameList = WebProxy.getGameList("en".equals(lang));
			application.setAttribute("game_"+lang, gameList);
		}
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
	<title>Uyaer,All My Game List!</title>
	<meta http-equiv="keywords" content="重返糖果岛,back to candyland,笨笨飞机,小球快跑,别踩红块儿,别踩百块,android,游戏,android游戏">
	<meta http-equiv="description" content="Uyaer,All My Game List!">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/jquery.easing.min.js"> </script>
	<script src="js/app.js"></script>

  </head>
  
  
<body>
	<div id="nav">
    	<div id="nav_top"></div>
    <%
    	for(int i = 0 ; i < gameList.size(); i ++){
    		GameVo vo = gameList.get(i);
    	
    %>
        <div class="nav_big_dot" style="background-color: <%=vo.bgcolor%>" data-icon="<%=vo.icon %>"></div>
        <div class="nav_white_dot"></div>
        <div class="nav_white_dot"></div>
        <div class="nav_white_dot"></div>
        <div class="nav_white_dot"></div>
	<% }%>
        <div class="nav_bottom">Top</div>
    </div>
    
    
    
    <div id="top">
    	<div id="top_box">
        	<div id="top_logo"><img src="images/logo.png" width="130" height="63" alt="uyear" /></div>
            <div id="top_right">
            <% if("zh".equals(lang)){ %>
            	<a href="?lang=en">English</a>
            <%}else{ %>
            	<a href="?lang=zh">中文版</a>
            <%} %>
            </div>
        </div>
    </div>
    
    <div id="banner" class="banner_bg_one">
    	<div id="banner_box"><a href="javascript:;"><img src="images/banner_img1.jpg" width="1260" height="440" alt="重返糖果岛" /></a></div>
    </div>
    
    <%
    	for(int i = 0 ; i < gameList.size(); i ++){
    		GameVo vo = gameList.get(i);	
     %>
    <!--one-->
    <div class="game" style="background-color: <%=vo.bgcolor%>">
    	<div class="game_box">
    		<%if(i%2==0){ %>
        	<div class="gamebox_left" ><img src="images/<%=vo.bgimg %>" /><img class="bigicon" src="images/<%=vo.bigicon %>" width="228" height="432"></div>
        	<%} %>
            <div class="gamebox_right" style=" margin-left:30px;">
            	<div class="gbr_title <%if("en".equals(lang)){%>gbr_title_en<%} %>" style="background-image:url(images/<%=vo.icon%>)"><%=vo.name %></div>
                <div class="gbr_info">
                	<ul >
               		<%if("zh".equals(lang)){ %>
                    	<li>游戏大小：<span><%=vo.gamesize %></span></li>
                        <li>适应系统：<span><%=vo.gameos %></span></li>
                        <li>上传时间：<span><%=vo.updatetime %></span></li>
                        <li>游戏版本：<span><%=vo.version %></span></li>
                    <%}else{ %>
                    	<li>size：<span><%=vo.gamesize %></span></li>
                        <li>os：<span><%=vo.gameos %></span></li>
                        <li>update time：<span><%=vo.updatetime %></span></li>
                        <li>version：<span><%=vo.version %></span></li>
                    <%} %>
                    </ul>
                   <div class="clear"></div>
                </div>
                <div class="gbr_intro">
                	<div class="gbr_in_onetop"></div>
                    <div class="gbr_in_onecenter"><p><%=vo.info %></p></div>
                    <div class="gbr_in_onefoot"></div>
                </div>
                <div id="btn_one"><a href="<%=vo.downloadurl%>">
                <%if("zh".equals(lang)){ %>
                	Android下载
                <%}else{ %>
                	Download
                <%} %>
                </a></div>
                
                
            </div>
            
            <%if(i%2==1){ %>
        	<div class="gamebox_left" ><img src="images/<%=vo.bgimg %>" /><img class="bigicon" src="images/<%=vo.bigicon %>" width="228" height="432"></div>
        	<%} %>
            <div class="clear"></div>
            
            
            
            
        </div>
    </div>
    
     <%  }%>
    
    <div id="foot">
    	<div id="foot_box"><img src="images/logo_foot.jpg" width="160" height="40" alt="uyear" /></div>
        <div id="foot_text">有梦就有希望,有爱就有幸福！</div>
    </div>
</body>
</html>
