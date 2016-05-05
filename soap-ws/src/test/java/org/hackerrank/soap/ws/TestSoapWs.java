package org.hackerrank.soap.ws;

import org.hackerrank.soap.ws.clients.CountrySoapClient;
import org.hackerrank.soap.ws.domain.GetCountryResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Starter.class)
@WebIntegrationTest("server.port:0")
public class TestSoapWs {
    @Value("${local.server.port}")
    private int port;

    @Test
    public void testFindCountryByName() throws Exception {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("org.hackerrank.soap.ws.domain");
        CountrySoapClient countrySoapClient = new CountrySoapClient();
        countrySoapClient.setMarshaller(marshaller);
        countrySoapClient.setUnmarshaller(marshaller);
        countrySoapClient.setDefaultUri("http://localhost:" + port + "/ws/countries");

        GetCountryResponse response = countrySoapClient.findCountryByName("Spain");
        Assert.assertNotNull(response);
    }
}
