package org.hackkerank.soap.ws;

import org.hackerrank.soap.ws.domain.GetCountryResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Starter.class)
@WebIntegrationTest("server.port:0")
public class TestSoapWs {
    @Value("${local.server.port}")
    private int port;

    @Autowired
    private SoapCountryClient soapCountryClient;

    @Test
    public void testFindCountryByName() throws Exception {
        GetCountryResponse response = soapCountryClient.findCountryByName("Spane");
        Assert.assertNotNull(response);
    }
}
