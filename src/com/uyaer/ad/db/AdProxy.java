package com.uyaer.ad.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.uyaer.ad.vo.AdVo;
import com.uyaer.ad.vo.GameAdListVo;
import com.uyaer.ad.vo.GameAdVo;
import com.uyaer.ad.vo.GameVo;
import com.uyaer.db.DBManager;

public class AdProxy {

	public static List<GameVo> getGameList() {
		List<GameVo> list = new ArrayList<GameVo>();

		Connection conn = DBManager.getConnection();
		// 创建SQL语句
		Statement st = null;
		try {
			st = (Statement) conn.createStatement();
			// 执行语句，返回结果
			ResultSet rs = (ResultSet) st
					.executeQuery("select * from ad_gamelist");
			while (rs.next()) {
				GameVo vo = new GameVo(rs.getInt(1), rs.getString(2));
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

	public static GameAdListVo getGameAdChannel(int gameid) {
		GameAdListVo gameAdListVo = new GameAdListVo();

		Connection conn = DBManager.getConnection();
		// 创建SQL语句
		Statement st = null;
		try {
			List<GameAdVo> list = new ArrayList<GameAdVo>();
			st = (Statement) conn.createStatement();
			// 执行语句，返回结果
			ResultSet rs = (ResultSet) st
					.executeQuery("select * from ad_game_adlist where gameid="
							+ gameid);
			while (rs.next()) {
				GameAdVo vo = new GameAdVo(rs.getInt(1), rs.getInt(2),
						rs.getInt(3), rs.getInt(4));
				list.add(vo);
			}
			// 获得游戏信息
			rs = (ResultSet) st
					.executeQuery("select * from ad_gamelist where id="
							+ gameid);
			if (rs.next()) {
				gameAdListVo.game = new GameVo(rs.getInt(1), rs.getString(2));
			}
			// 填充广告对象
			for (int i = 0; i < list.size(); i++) {
				GameAdVo vo = list.get(i);
				rs = (ResultSet) st
						.executeQuery("select * from ad_channel where id="
								+ vo.adid);
				if (rs.next()) {
					vo.ad = new AdVo(rs.getInt(1), rs.getString(2));
				}
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
		return gameAdListVo;
	}

	public static void changAdState(int id) {

	}

	public static boolean getGameAdState(int gameid, String tag) {
		Connection conn = DBManager.getConnection();
		// 创建SQL语句
		Statement st = null;
		try {
			st = (Statement) conn.createStatement();
			// 执行语句，返回结果
			ResultSet rs = (ResultSet) st
					.executeQuery("select * from ad_channel where tag='" + tag+"'");
			int adid = 1;
			if (rs.next()) {
				adid = rs.getInt("id");
			}
			// 获得游戏信息
			rs = (ResultSet) st
					.executeQuery("select * from ad_game_adlist where gameid="
							+ gameid + " and adid=" + adid);
			if (rs.next()) {
				return rs.getInt("isopen") == 1;
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

		return false;
	}
}
