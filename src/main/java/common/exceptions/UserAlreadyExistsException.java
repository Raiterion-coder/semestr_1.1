
package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                logger.info("Получено сообщение: {}", inputLine);
                // Обработка сообщений клиента
            }
        } catch (IOException e) {
            logger.error("Ошибка обработки клиента: {}", e.getMessage());
        } finally {
            try {
                clientSocket.close();
                logger.info("Подключение закрыто: {}", clientSocket.getRemoteSocketAddress());
            } catch (IOException e) {
                logger.error("Ошибка закрытия сокета: {}", e.getMessage());
            }
        }
    }
}
