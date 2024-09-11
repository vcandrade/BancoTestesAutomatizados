package service;

import java.util.ArrayList;
import java.util.List;

public class BancoService {

	private List<ContaBancaria> listaContas;

	public BancoService() {

		this.inicializarContas();
	}

	private void inicializarContas() {

		this.listaContas = new ArrayList<ContaBancaria>();

		this.listaContas.add(new ContaBancaria(1234, 1111, 10.00, new Titular("123456", "João da Silva", "01/01/2001"), "senha123"));
		this.listaContas.add(new ContaBancaria(1234, 2222, 20.00, new Titular("987654", "Maria de Oliveira", "02/02/2002"), "senha123"));
		this.listaContas.add(new ContaBancaria(1234, 3333, 30.00, new Titular("159487", "Ricardo Carvalho", "03/03/2003"), "senha123"));
		this.listaContas.add(new ContaBancaria(1234, 4444, 40.00, new Titular("326159", "Márcio Gomes", "04/04/2004"), "senha123"));
		this.listaContas.add(new ContaBancaria(1234, 5555, 50.00, new Titular("753869", "Letícia Guimarães", "05/05/2005"), "senha123"));
	}

	public double obterSaldo(int numeroAgencia, int numeroConta, String senha) throws ContaInexistenteException, SenhaIncorretaException {

		ContaBancaria contaBancaria = this.encontrarContaBancaria(numeroAgencia, numeroConta);

		if (contaBancaria == null) {

			throw new ContaInexistenteException("Conta Bancária não encontrada.");
		}

		if (!this.verificarSenha(contaBancaria, senha)) {

			throw new SenhaIncorretaException("A senha informada está incorreta.");
		}

		return contaBancaria.getSaldo();
	}

	public String realizarSaque(int numeroAgencia, int numeroConta, String senha, double valorSaque) throws ContaInexistenteException, SenhaIncorretaException, ValorIncorretoException, SaldoInsuficienteException {

		ContaBancaria contaBancaria = this.encontrarContaBancaria(numeroAgencia, numeroConta);

		if (contaBancaria == null) {

			throw new ContaInexistenteException("Conta Bancária não encontrada.");
		}

		if (!this.verificarSenha(contaBancaria, senha)) {

			throw new SenhaIncorretaException("Senha informada está incorreta.");
		}
		
		if (!this.verificarValor(valorSaque)) {
			
			throw new ValorIncorretoException("Valor informado está incorreto.");
		}
		
		if (contaBancaria.getSaldo() - valorSaque < 0) {
			
			throw new SaldoInsuficienteException("Saldo insuficiente.");
		}
		
		contaBancaria.setSaldo(contaBancaria.getSaldo() - valorSaque);

		return "Saque efetuado com sucesso.";
	}
	
	public String depositar(int numeroAgencia, int numeroConta, double valorDeposito) throws ContaInexistenteException, ValorIncorretoException {
		
		ContaBancaria contaBancaria = this.encontrarContaBancaria(numeroAgencia, numeroConta);

		if (contaBancaria == null) {

			throw new ContaInexistenteException("Conta Bancária não encontrada.");
		}
		
		if (!this.verificarValor(valorDeposito)) {
			
			throw new ValorIncorretoException("Valor informado está incorreto.");
		}
		
		contaBancaria.setSaldo(contaBancaria.getSaldo() + valorDeposito);
		
		return "Depósito efetuado com sucesso.";
	}

	private ContaBancaria encontrarContaBancaria(int numeroAgencia, int numeroConta) {

		for (ContaBancaria contaBancaria : listaContas) {

			if (contaBancaria.getAgencia() == numeroAgencia && contaBancaria.getNumeroConta() == numeroConta) {

				return contaBancaria;
			}
		}

		return null;
	}
	
	private boolean verificarValor(double valor) {
		
		return (valor > 0.0) ? true : false;
	}

	private boolean verificarSenha(ContaBancaria contaBancaria, String senha) {

		return (contaBancaria.getSenha().equals(senha)) ? true : false;
	}
}
