package models;

public class product {
	private int code;
	private String description;
	private int cost;
	
	public product() {}
	
	public product(int code, String description, int cost) {
		this.code = code;
		this.description = description;
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String toString() {
		return "Product{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
	}
}
