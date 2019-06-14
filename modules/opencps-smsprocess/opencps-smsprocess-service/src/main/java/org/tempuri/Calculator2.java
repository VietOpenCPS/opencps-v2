
package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = "jaxws=true", service = Calculator2.class)

@WebService
public class Calculator2 {

    @WebMethod
    public int divide(int a, int b) {
        return a / b;
    }

    @WebMethod
    public int multiply(int a, int b) {
        return a * b;
    }

    @WebMethod
    public int subtract(int a, int b) {
        return a - b;
    }

    @WebMethod
    public int sum(int a, int b) {
        return a + b;
    }
}
