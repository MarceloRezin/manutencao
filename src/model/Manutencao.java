package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Manutencao extends Model{
	
	private LocalDateTime dataHora;
	private BigDecimal quilometragem;
	private String descricao;
	private BigDecimal valor;
	private Veiculo veiculo;
	private List<ManutencaoItem> itens;
	
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public Manutencao() {
		super(null);
	}

	public Manutencao(LocalDateTime dataHora) {
		super(null);
		this.dataHora = dataHora;
	}

	public Manutencao(Integer id, LocalDateTime dataHora, BigDecimal quilometragem, String descricao, BigDecimal valor, Veiculo veiculo, List<ManutencaoItem> itens) {
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
	
	public String getDataHoraFormatada() {
		return dataHora.format(FORMATTER);
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

	public List<ManutencaoItem> getItens() {
		return itens;
	}

	public void setItens(List<ManutencaoItem> itens) {
		this.itens = itens;
	}
	
	public int getQuantidadeItens() {
		return itens == null ? 0 : itens.size();
	}
}
