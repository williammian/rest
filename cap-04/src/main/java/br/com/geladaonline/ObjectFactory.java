//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.07.25 at 03:16:12 PM GMT-03:00 
//


package br.com.geladaonline;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.geladaonline package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PessoaFisica_QNAME = new QName("http://brejaonline.com.br/pessoa/v1", "pessoaFisica");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.geladaonline
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PessoaFisica }
     * 
     */
    public PessoaFisica createPessoaFisica() {
        return new PessoaFisica();
    }

    /**
     * Create an instance of {@link Endereco }
     * 
     */
    public Endereco createEndereco() {
        return new Endereco();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PessoaFisica }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://brejaonline.com.br/pessoa/v1", name = "pessoaFisica")
    public JAXBElement<PessoaFisica> createPessoaFisica(PessoaFisica value) {
        return new JAXBElement<PessoaFisica>(_PessoaFisica_QNAME, PessoaFisica.class, null, value);
    }

}
