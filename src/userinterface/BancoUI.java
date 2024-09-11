package userinterface;

import java.util.Scanner;

import service.BancoService;
import service.ContaInexistenteException;
import service.SaldoInsuficienteException;
import service.SenhaIncorretaException;
import service.ValorIncorretoException;

public class BancoUI {

	private static int numeroAgencia;
	private static int numeroConta;
	private static String senha;
	private static double valor;
	private static Scanner input;

	public static void main(String[] args) {

		BancoService banco = new BancoService();
		input = new Scanner(System.in);
		 
		int opcao = 0;
		
		do {
			
			try {
				
				System.out.println("1 - Obter Saldo");
				System.out.println("2 - Realizar Saque");
				System.out.println("3 - Realizar Depósito");
				System.out.println("0 - Finalizar o Aplicativo");
				
				System.out.print("Opção: ");
				opcao = input.nextInt();
				
				switch(opcao) {
				
					case 1:
						obterDadosConta();
						obterSenha();
						
						System.out.println("Saldo: R$ " + String.format("%.2f", banco.obterSaldo(numeroAgencia, numeroConta, senha)));
						break;
						
					case 2:
						obterDadosConta();
						obterSenha();
						obterValor();
						
						System.out.println(banco.realizarSaque(numeroAgencia, numeroConta, senha, valor));
						break;
					
					case 3:
						obterDadosConta();
						obterValor();
						
						System.out.println(banco.depositar(numeroAgencia, numeroConta, valor));
						break;
						
					case 0:
						System.out.println("Finalizado.");
						continue;
						
					default:		
						System.err.println("Opção inválida.");;
				}
				
			} catch (ContaInexistenteException | SenhaIncorretaException | ValorIncorretoException | SaldoInsuficienteException e) {
				
				System.err.println(e.getMessage());
			}
			
		} while (opcao != 0);
		
		input.close();
	}
	
	public static void obterDadosConta() {
		
		System.out.print("Informe o número da Agência: ");
		numeroAgencia = input.nextInt();
		
		System.out.print("Informe o número da Conta Bancária: ");
		numeroConta = input.nextInt();
	}
	
	public static void obterSenha() {
		
		System.out.print("Informe a senha: ");
		senha = input.next();
	}
	
	public static void obterValor() {
		
		System.out.print("Informe o valor: R$");
		valor = input.nextDouble();
	}
}
