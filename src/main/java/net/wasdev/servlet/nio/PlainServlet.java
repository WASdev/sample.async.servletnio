package net.wasdev.servlet.nio;

import java.io.IOException;

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
@WebServlet("/plainServlet")
public class PlainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String header = "<html lang=\"en-US\" xmlns=\"http://www.w3.org/1999/xhtml\"><body>";
    private static final String footer = "</body></html>";

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream is = request.getInputStream();
        ServletOutputStream os = response.getOutputStream();

        System.out.println(request.getContentLength());
        System.out.println(request.getContentType());
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

        // Echo input stream to output stream
        os.println(header);
        os.print(new String(data, 0, offset));
        os.println();
        os.println(footer);
        
        response.setContentType("text/html");
        
        is.close();
        os.close();
    }

}
