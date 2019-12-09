package model;

public class Model {
	
	private Integer id;
	
	public Model(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean isNew() {
		return id == null;
	} 
}
