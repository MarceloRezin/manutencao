package model;

public class Item extends Model{
	
	private Integer id;
	private String observacao;
	
	public Item() {
		super(null);
	}
	
	public Item(Integer id, String observacao) {
		super(id);
		this.id = id;
		this.observacao = observacao;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
