package cn.zlin.demo.domain;

import java.io.Serializable;

public class Pages implements Serializable{
	private int page;
	private int limit;
	final static private int pageNum = 1;
	final static private int limitNum = 10;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page < pageNum ? pageNum : page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit < pageNum ? limitNum : limit ;
	}
	
}
