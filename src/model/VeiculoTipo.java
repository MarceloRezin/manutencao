package model;

public enum VeiculoTipo {

	MOTO("Moto"),
	CARRO("Carro")
	;
	
	private String descricao;

	VeiculoTipo(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
