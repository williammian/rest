package br.com.geladaonline.parsers;

import br.com.geladaonline.modelo.PessoaFisica;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class XStreamParser {

	public static void main(String[] args) {
		PessoaFisica pessoaFisica = Parser.criarPessoaFisicaTeste();
		
		JettisonMappedXmlDriver jettisonMappedXmlDriver = new JettisonMappedXmlDriver();
		
		
		XStream xStream = new XStream(new JettisonMappedXmlDriver());
		
		
		System.out.println(xStream.toXML(pessoaFisica));

	}
	
}
