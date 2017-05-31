package net.wasdev.servlet.nio.it.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This tests that the details are returned for the various servlets
 */
@RunWith(Parameterized.class)
public class ServletIT {

    private String servlet;

    /**
     * @return The endpoints that are expected to return the details supplied on the form
     */
    @Parameters(name="{0}")
    public static Collection<String> servlets() {
        List<String> servlets = new ArrayList<>();
        servlets.add("plainServlet");
        servlets.add("asyncServlet");
        servlets.add("asyncServletNIO");
        servlets.add("asyncServletRunnable");
        return servlets;
    }

    public ServletIT(String servlet) {
        this.servlet = servlet;
    }

    @Test
    public void testServlet() {
        Client client = ClientBuilder.newClient();
        String port = System.getProperty("liberty.test.port");
        String url = "http://localhost:" + port + "/servlet-nio/" + servlet;
        System.out.println("Testing " + url);
        
        Form form = new Form();
        form.param("details", "abc");
        Invocation.Builder requestBuilder = client.target(url).request();
        Response getResponse = requestBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        String responseString = getResponse.readEntity(String.class);
        assertTrue(responseString.contains("details=abc"));
    }

}
