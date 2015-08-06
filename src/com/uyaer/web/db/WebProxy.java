package com.uyaer.web.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.uyaer.db.DBManager;
import com.uyaer.web.vo.GameVo;

public class WebProxy {

	public static List<GameVo> getGameList(boolean isEn) {
		List<GameVo> list = new ArrayList<GameVo>();

		Connection conn = DBManager.getConnection();
		// 创建SQL语句
		Statement st = null;
		try {
			st = (Statement) conn.createStatement();
			String sql = "select * from web_index where isen=false order by sort desc,id";
			if(isEn){
				sql = "select * from web_index where isen=true order by sort desc,id";
			}
			// 执行语句，返回结果
			ResultSet rs = (ResultSet) st
					.executeQuery(sql);
			while (rs.next()) {
				GameVo vo = new GameVo();
				vo.id = rs.getInt("id");
				vo.sort = rs.getInt("sort");
				vo.name = rs.getString("name");
				vo.gamesize = rs.getString("gamesize");
				vo.gameos = rs.getString("gameos");
				vo.updatetime = rs.getString("updatetime");
				vo.version = rs.getString("version");
				vo.info = rs.getString("info");
				vo.bgcolor = rs.getString("bgcolor");
				vo.bgimg = rs.getString("bgimg");
				vo.bigicon = rs.getString("bigicon");
				vo.icon = rs.getString("icon");
				vo.downloadurl = rs.getString("downloadurl");
				vo.navicon = rs.getString("navicon");
				vo.isen = isEn;
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				st = null;
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
