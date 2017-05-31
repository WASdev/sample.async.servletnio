package net.wasdev.servlet.nio;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PlainServlet
 */
@WebServlet(urlPatterns = "/asyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String header = "<html lang=\"en-US\" xmlns=\"http://www.w3.org/1999/xhtml\"><body>";
    private static final String footer = "</body></html>";

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // start async
        AsyncContext ac = request.startAsync();

        // set up async listener
        ac.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                System.out.println("AsyncServlet onComplete() called");

            }

            @Override
            public void onError(AsyncEvent event) {
                System.out.println("AsyncServlet onError() " + event.getThrowable().toString());
            }

            @Override
            public void onStartAsync(AsyncEvent event) {
                System.out.println("AsyncServlet onStartAsync()");
            }

            @Override
            public void onTimeout(AsyncEvent event) {
                System.out.println("AsyncServlet onTimeout()");
            }
        }, request, response);

        System.out.println(Thread.currentThread().getName());

        ServletInputStream is = request.getInputStream();
        ServletOutputStream os = response.getOutputStream();

        int clength = request.getContentLength();
        byte[] data = new byte[clength];
        int offset = 0;
        do {
            int rc = is.read(data, offset, data.length - offset);
            if (-1 == rc) {
                break;
            }
            offset += rc;
        } while (offset < data.length);
        is.close();

        // Echo input stream to output stream
        os.println(header);
        os.print(new String(data, 0, offset));
        os.println();
        os.println(footer);
        
        response.setContentType("text/html");
        
        //Call ac.complete here since we are expecting the exception and we want to set a null readListener 
        ac.complete();
    }

}
