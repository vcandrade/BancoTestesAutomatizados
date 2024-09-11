package service;

public class ContaBancaria {

	private int agencia;
	private int numeroConta;
	private double saldo;
	private Titular titular;
	private String senha;
	
	public ContaBancaria(int agencia, int numeroConta, double saldo, Titular titular, String senha) {
	
		this.agencia = agencia;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
		this.titular = titular;
		this.senha = senha;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public Titular getTitular() {
		return titular;
	}

	public String getSenha() {
		return senha;
	}
}
