import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Create and start the web server on port 8000
            WebServer webServer = new WebServer(8000);
            webServer.start();
        } catch (IOException e) {
            System.err.println("Error starting web server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}