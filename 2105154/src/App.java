import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class App extends Application {
    private ServerSocket serverSocket;
    private HashMap<String, SocketWrapper> clientMap;

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rootscene.fxml"));
            Parent root = loader.load();
            Image iconImage = new Image(getClass().getResourceAsStream("khaikhai.png"));
            primaryStage.getIcons().add(iconImage);
            Scene scene = new Scene(root, 750, 600);
            primaryStage.setTitle("KHaiKhai.com");
            primaryStage.setScene(scene);
            primaryStage.show();
            clientMap = new HashMap<>();
            startServerSocket();
        } catch (IOException e) {
            System.err.println("Error initializing the server: " + e.getMessage());
            e.printStackTrace();
            // Handle the error gracefully or exit the application.
        }
    }

    private void startServerSocket() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(3333);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    serve(clientSocket);
                }
            } catch (IOException e) {
                System.err.println("Error accepting client connections: " + e.getMessage());
            } finally {
                try {
                    if (serverSocket != null && !serverSocket.isClosed()) {
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error closing server socket: " + e.getMessage());
                }
            }
        }).start();
    }

    private void serve(Socket clientSocket) {
        try {
            SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
            String clientName = (String) socketWrapper.read();
            clientMap.put(clientName, socketWrapper);
            new ReadThreadServer(clientMap, socketWrapper);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error serving client: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
