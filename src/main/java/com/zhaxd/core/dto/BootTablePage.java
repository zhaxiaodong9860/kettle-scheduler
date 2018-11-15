package com.zhaxd.core.dto;

import java.util.List;

/**
 * BootstrapTable表格规定
 * 返回数据格式
 */
public class BootTablePage {

	//总数量
	private long total;
	//每一页的数据
	private List<?> rows;
	
	public BootTablePage() {
	}
	public BootTablePage(long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
