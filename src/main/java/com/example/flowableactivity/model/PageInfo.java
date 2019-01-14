package com.example.flowableactivity.model;

public class PageInfo<T> {

	private int pagesize;
	private int pagecount;
	private int recordcount;
	private int start;
	private T rows;

	public PageInfo() {
		super();
	}

	public PageInfo(int pagesize, int pagecount, int recordcount, int start, T rows) {
		super();
		this.pagesize = pagesize;
		this.pagecount = pagecount;
		this.recordcount = pagecount;
		this.start = start;
		this.rows = rows;
	}

	public PageInfo(int pagesize, int recordcount, int start, T rows) {
		super();
		this.pagesize = pagesize;
		this.recordcount = recordcount;
		this.start = start;
		this.rows = rows;

		if (this.start < 0) {
			this.start = 0;
		}
		else if (this.start >= this.recordcount) {
			this.start = this.recordcount - 1;
		}
		
		if (pagesize == 0) {
			this.pagecount = 1;
		} else {
			if (recordcount > 0) {
				if ((recordcount % pagesize) == 0) {
					this.pagecount = recordcount / pagesize;
				} else {
					this.pagecount = recordcount / pagesize + 1;
				}
			} else {
				this.pagecount = 0;
			}
		}
		
	}

	public PageInfo(int pagesize, int recordcount, int start) {
		this(pagesize, recordcount, start, null);
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getRecordcount() {
		return recordcount;
	}

	public void setRecordcount(int recordcount) {
		this.recordcount = recordcount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public T getRows() {
		return rows;
	}

	public void setRows(T rows) {
		this.rows = rows;
	}

}
