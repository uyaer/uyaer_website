package com.uyaer.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uyaer.web.db.WebProxy;
import com.uyaer.web.vo.GameVo;

public class WebMyGameServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String lang = request.getParameter("lang");
		if(lang == null || "".equals(lang) || (!"zh".equals(lang) && !"en".equals(lang))){
			String clientLang = request.getHeader("Accept-Language");
			if(clientLang.indexOf("zh")!=-1){
				lang = "zh";
			}else{
				lang = "en";
			}
		}
		
		ServletContext application=this.getServletContext();
		@SuppressWarnings("unchecked")
		List<GameVo> gameList = (List<GameVo>) application.getAttribute("game_"+lang);
		if(gameList==null){
			gameList = WebProxy.getGameList("en".equals(lang));
			application.setAttribute("game_"+lang, gameList);
		}

	}
}
