import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WebServer {
    private HttpServer server;
    private int port;

    public WebServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        // Create HTTP server
        server = HttpServer.create(new InetSocketAddress(port), 0);

        // Create context for home page
        server.createContext("/", new HomePageHandler());

        // Start the server
        server.setExecutor(null); // creates a default executor
        server.start();

        System.out.println("Server is running on http://localhost:" + port);
        System.out.println("Press Ctrl+C to stop the server");
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
            System.out.println("Server stopped");
        }
    }

    static class HomePageHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = getHomePage();

            // Set response headers
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            // Send response
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private String getHomePage() {
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Home Page</title>\n" +
                    "    <style>\n" +
                    "        * {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "            box-sizing: border-box;\n" +
                    "        }\n" +
                    "        body {\n" +
                    "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
                    "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
                    "            min-height: 100vh;\n" +
                    "            display: flex;\n" +
                    "            justify-content: center;\n" +
                    "            align-items: center;\n" +
                    "            padding: 20px;\n" +
                    "        }\n" +
                    "        .container {\n" +
                    "            background: white;\n" +
                    "            border-radius: 20px;\n" +
                    "            padding: 60px 40px;\n" +
                    "            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);\n" +
                    "            max-width: 800px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        h1 {\n" +
                    "            color: #333;\n" +
                    "            font-size: 3em;\n" +
                    "            margin-bottom: 20px;\n" +
                    "        }\n" +
                    "        p {\n" +
                    "            color: #666;\n" +
                    "            font-size: 1.2em;\n" +
                    "            line-height: 1.6;\n" +
                    "            margin-bottom: 30px;\n" +
                    "        }\n" +
                    "        .button {\n" +
                    "            display: inline-block;\n" +
                    "            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);\n" +
                    "            color: white;\n" +
                    "            padding: 15px 40px;\n" +
                    "            border-radius: 50px;\n" +
                    "            text-decoration: none;\n" +
                    "            font-weight: bold;\n" +
                    "            transition: transform 0.3s ease, box-shadow 0.3s ease;\n" +
                    "            margin: 10px;\n" +
                    "        }\n" +
                    "        .button:hover {\n" +
                    "            transform: translateY(-3px);\n" +
                    "            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);\n" +
                    "        }\n" +
                    "        .features {\n" +
                    "            display: grid;\n" +
                    "            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));\n" +
                    "            gap: 20px;\n" +
                    "            margin-top: 40px;\n" +
                    "        }\n" +
                    "        .feature {\n" +
                    "            padding: 20px;\n" +
                    "            background: #f8f9fa;\n" +
                    "            border-radius: 10px;\n" +
                    "        }\n" +
                    "        .feature h3 {\n" +
                    "            color: #667eea;\n" +
                    "            margin-bottom: 10px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"container\">\n" +
                    "        <h1>Welcome to My Website</h1>\n" +
                    "        <p>This is a basic Java-powered web server with a beautiful home page!</p>\n" +
                    "        <p>Built with Java's built-in HTTP server capabilities.</p>\n" +
                    "        \n" +
                    "        <div>\n" +
                    "            <a href=\"#\" class=\"button\">Get Started</a>\n" +
                    "            <a href=\"#\" class=\"button\">Learn More</a>\n" +
                    "        </div>\n" +
                    "        \n" +
                    "        <div class=\"features\">\n" +
                    "            <div class=\"feature\">\n" +
                    "                <h3>🚀 Fast</h3>\n" +
                    "                <p>Lightning-fast performance</p>\n" +
                    "            </div>\n" +
                    "            <div class=\"feature\">\n" +
                    "                <h3>🎨 Beautiful</h3>\n" +
                    "                <p>Modern and responsive design</p>\n" +
                    "            </div>\n" +
                    "            <div class=\"feature\">\n" +
                    "                <h3>☕ Java-Powered</h3>\n" +
                    "                <p>Built with pure Java</p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";
        }
    }
}

