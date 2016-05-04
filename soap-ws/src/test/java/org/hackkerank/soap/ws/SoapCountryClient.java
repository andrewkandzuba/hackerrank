package org.hackkerank.soap.ws;

import org.hackerrank.soap.ws.domain.GetCountryRequest;
import org.hackerrank.soap.ws.domain.GetCountryResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SoapCountryClient extends WebServiceGatewaySupport {
    public GetCountryResponse findCountryByName(String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        return (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback("http://localhost:8080/ws/getStudentResponse"));
    }
}
