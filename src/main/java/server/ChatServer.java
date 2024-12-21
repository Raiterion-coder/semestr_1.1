package server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final Logger logger = LoggerFactory.getLogger(ChatServer.class);
    private final int port;
    private final Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту {}", port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket, this)).start();
            }
        } catch (IOException e) {
            logger.error("Ошибка запуска сервера: {}", e.getMessage());
        }
    }

    public void broadcast(String message, ClientHandler sender) {
        clients.values().forEach(client -> {
            if (client != sender) {
                client.sendMessage(message);
            }
        });
    }

    public synchronized void addClient(String username, ClientHandler clientHandler) {
        clients.put(username, clientHandler);
        broadcast("Пользователь " + username + " присоединился к чату.", null);
        logger.info("Пользователь {} подключен", username);
    }

    public synchronized void removeClient(String username) {
        clients.remove(username);
        broadcast("Пользователь " + username + " покинул чат.", null);
        logger.info("Пользователь {} отключен", username);
    }

    public static void main(String[] args) {
        int port = 8080;
        new ChatServer(port).start();
    }
}