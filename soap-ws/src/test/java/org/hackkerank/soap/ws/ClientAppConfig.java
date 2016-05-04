package org.hackkerank.soap.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
@Configuration
public class ClientAppConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("resources/countries.wsdl");
        return marshaller;
    }

    @Bean
    public SoapCountryClient soapCountryClient(Jaxb2Marshaller marshaller) {
        SoapCountryClient client = new SoapCountryClient();
        client.setDefaultUri("http://localhost:8080/ws/countries.wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}