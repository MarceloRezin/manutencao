package model;

public class Veiculo extends Model{
	
	private int ano;
	private String placa;
	private VeiculoTipo tipo;
	private String descricao;
	
	public Veiculo() {
		super(null);
	}
	
	public Veiculo(Integer id, int ano, String placa, VeiculoTipo tipo, String descricao) {
		super(id);
		this.ano = ano;
		this.placa = placa;
		this.tipo = tipo;
		this.descricao = descricao;
	}
	
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public VeiculoTipo getTipo() {
		return tipo;
	}
	public void setTipo(VeiculoTipo tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
