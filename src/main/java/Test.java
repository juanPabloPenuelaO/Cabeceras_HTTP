import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(value="/test") //el servlet responderá a la url o ruta de: /test
public class Test extends HttpServlet { //se manejarán las solicitudes del HttpServlet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, //el metodo doGet toma dos parametros para manejar lo que pida el Get,
            IOException {                                                                           // uno para lo que requiere y otro para la respuesta de este.
        resp.setContentType("text/html"); //la respuesta se va a interpretar como pagina html en el navegador.
        String metodoHttp = req.getMethod(); //obtiene el metodo http que se use como el get
        String requestUri = req.getRequestURI(); //obtiene la Uri de la solicitud.
        String requestUrl = req.getRequestURL().toString(); //obtiene la url completa de la solicitud.
        String contexPath = req.getContextPath(); //obtiene el contexto de la pagina web.
        String servletPath = req.getServletPath(); //obtiene la ruta del servlet
        String ipCliente = req.getRemoteAddr(); //obtiene la dirección IP del usuario
        String ip = req.getLocalAddr(); //obtiene la dirección IP del servidor.
        int port = req.getLocalPort(); //obtiene el puerto del servidor que recibe la petición.
        String scheme = req.getScheme(); //obtiene el esquema de la url.
        String host = req.getHeader("host"); //obtiene el valor del encabezado.
        String url = scheme + "://" + host + contexPath + servletPath; //hace la url con el esquema, host, contexto y ruta del servlet.
        String url2 = scheme + "://" + ip + ":" + port + contexPath + servletPath; //hace una segunda url usando el esquema, dirección IP, puerto, contexto y ruta del servlet.
        try (PrintWriter out = resp.getWriter()) { //se imprimen la respuesta que se enviará al Usuario que hace peticiones en el http.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Cabeceras HTTP Request</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Cabeceras HTTP Request!</h1>");
            out.println("<ul>");
            out.println("<li>metodo http: " + metodoHttp + "</li>");
            out.println("<li>request uri: " + requestUri + "</li>");
            out.println("<li>request url: " + requestUrl + "</li>");
            out.println("<li>context path: " + contexPath + "</li>");
            out.println("<li>servlet path: " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>ip cliente: " + ipCliente + "</li>");
            out.println("<li>puerto local: " + port + "</li>");
            out.println("<li>scheme: " + scheme + "</li>");
            out.println("<li>host: " + host + "</li>");
            out.println("<li>url: " + url + "</li>");
            out.println("<li>url2: " + url2 + "</li>");
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String cabecera = headerNames.nextElement();
                out.println("<li>"+ cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}