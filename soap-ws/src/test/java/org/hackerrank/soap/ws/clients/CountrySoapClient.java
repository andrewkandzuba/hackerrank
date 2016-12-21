package org.hackerrank.soap.ws.clients;

import org.hackerrank.soap.ws.domain.GetCountryRequest;
import org.hackerrank.soap.ws.domain.GetCountryResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountrySoapClient extends WebServiceGatewaySupport {

    public GetCountryResponse findCountryByName(String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        return (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}