package org.hackerrank.java.interview.jcp.synchronization;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CachedFactorizer extends HttpServlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;
        synchronized (this) {
            if (i.equals(lastNumber)) {
                factors = lastFactors.clone();
            }
        }
        if (factors == null) {
            factors = factor(i.intValue());
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors.clone();
            }
        }
        encodeIntoResponse(resp, factors);
    }

    private BigInteger[] factor(int val) {
        List<Integer> factors  = new ArrayList<>();
        for(int i=1; i <= val/2; i++)
        {
            if(val % i == 0)
            {
                factors.add(i);
            }
        }
        return (BigInteger[]) factors.toArray();
    }

    BigInteger extractFromRequest(ServletRequest request) {
        return new BigInteger(request.getParameter("i"));
    }

    void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {
    }
}
