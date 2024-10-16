package models;

public class cart {
	private int count;
	private product sp;
	
	public cart(int count, product sp) {
		this.count = count;
		this.sp = sp;
	}
	
	public product getSp() {
		return sp;
	}
	public void setSp(product sp) {
		this.sp = sp;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
