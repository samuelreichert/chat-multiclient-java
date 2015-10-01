package server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by samuel on 24/09/15.
 */
public class MultiUserChatServer {
    ServerSocket serverSocket;
    Socket socketNewClient;

    public void configServer() {
        try{
            serverSocket = new ServerSocket(8080);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void waitClients() {
        try{
            while(true) {
                socketNewClient = serverSocket.accept();
                ConnectedClient newClient = new ConnectedClient(socketNewClient);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}