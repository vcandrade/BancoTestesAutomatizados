package service;

public class Titular {

	private String cpf;
	private String nome;
	private String dataNascimento;
	
	public Titular(String cpf, String nome, String dataNascimento) {
		
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public String getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
}
