package net.wasdev.servlet.nio;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlainServlet
 */
@WebServlet(urlPatterns = "/asyncServletNIO", asyncSupported = true)
public class AsyncServletNIO extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        // start async
        final AsyncContext ac = request.startAsync();

        // set up async listener
        ac.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("AsyncServletNIO onComplete() called");

            }

            @Override
            public void onError(AsyncEvent event) {
                System.out.println("AsyncServletNIO onError() " + event.getThrowable());
            }

            @Override
            public void onStartAsync(AsyncEvent event) {
                System.out.println("AsyncServletNIO onStartAsync()");
            }

            @Override
            public void onTimeout(AsyncEvent event) {
                System.out.println("AsyncServletNIO onTimeout()");
            }
        }, request, response);

        final ServletInputStream is = request.getInputStream();
        final ServletOutputStream os = response.getOutputStream();

        // Start NIO Mode!! Can not use regular servlet input stream read/write after this
        // Follow the code flow from AsyncReadListener (to read request) to
        // AsyncWriteListener (to write response)
        is.setReadListener(new AsyncReadListener(ac, is, os));
    }

}
