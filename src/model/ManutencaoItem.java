package model;

import java.math.BigDecimal;

public class ManutencaoItem extends Model{
	
	private Manutencao manutencao;
	private Item item;
	private BigDecimal valor;
	
	public ManutencaoItem() {
		super(null);
	}
	
	public ManutencaoItem(Integer id, Manutencao manutencao, Item item, BigDecimal valor) {
		super(id);
		this.manutencao = manutencao;
		this.item = item;
		this.valor = valor;
	}

	public Manutencao getManutencao() {
		return manutencao;
	}

	public void setManutencao(Manutencao manutencao) {
		this.manutencao = manutencao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
