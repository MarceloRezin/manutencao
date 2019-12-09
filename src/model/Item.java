package model;

public class Item extends Model{
	
	private String descricao;
	
	public Item() {
		super(null);
	}
	
	public Item(Integer id, String descricao) {
		super(id);
		this.descricao = descricao;
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
