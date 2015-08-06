package com.uyaer.ad.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uyaer.ad.db.AdProxy;

public class AdServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int gameid = Integer.parseInt(request.getParameter("gameid"));
		String tag = request.getParameter("tag");
		
		boolean isOpen = AdProxy.getGameAdState(gameid, tag);
		String json = "{\"isOpen\":"+isOpen+"}";
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
