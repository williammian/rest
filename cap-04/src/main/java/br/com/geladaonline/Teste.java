package br.com.geladaonline;

import javax.xml.bind.JAXB;

public class Teste {

	public static void main(String[] args) {
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setCpf("12345678909");
		pessoaFisica.setNome("Alexandre Saudate");
		
		Endereco endereco = new Endereco();
		endereco.setCep("12345-678");
		
		pessoaFisica.getEndereco().add(endereco);
		
		JAXB.marshal(pessoaFisica, System.out);
	}
	
}
