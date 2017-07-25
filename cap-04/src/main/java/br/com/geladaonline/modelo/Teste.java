package br.com.geladaonline.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;

public class Teste {

	public static void main(String[] args) {
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setCpf("12345678909");
		pessoaFisica.setNome("Alexandre Saudate");
		
		Endereco endereco = new Endereco();
		endereco.setCep("12345-678");
		
		List<Endereco> enderecos = pessoaFisica.getEndereco();
		if(enderecos == null)enderecos = new ArrayList<Endereco>();
		enderecos.add(endereco);
		
		pessoaFisica.setEndereco(enderecos);
		
		JAXB.marshal(pessoaFisica, System.out);
	}
	
}
