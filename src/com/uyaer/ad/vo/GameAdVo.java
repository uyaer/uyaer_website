package com.uyaer.ad.vo;

public class GameAdVo {
	public int id;
	public int gameid;
	public int adid;
	public GameVo game;
	public AdVo ad;
	public int isOpen;
	public GameAdVo(int id, int gameid, int adid, int isOpen) {
		super();
		this.id = id;
		this.gameid = gameid;
		this.adid = adid;
		this.isOpen = isOpen;
	}
	
	
}
