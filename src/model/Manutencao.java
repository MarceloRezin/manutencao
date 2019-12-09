package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Manutencao extends Model{
	
	private LocalDateTime dataHora;
	private BigDecimal quilometragem;
	private String descricao;
	private BigDecimal valor;
	private Veiculo veiculo;
	private List<Object> itens; //TODO itens
	
	public Manutencao() {
		super(null);
	}

	public Manutencao(Integer id, LocalDateTime dataHora, BigDecimal quilometragem, String descricao, BigDecimal valor, Veiculo veiculo, List<Object> itens) {
		super(id);
		this.dataHora = dataHora;
		this.quilometragem = quilometragem;
		this.descricao = descricao;
		this.valor = valor;
		this.veiculo = veiculo;
		this.itens = itens;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public BigDecimal getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(BigDecimal quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<Object> getItens() {
		return itens;
	}

	public void setItens(List<Object> itens) {
		this.itens = itens;
	}
}
