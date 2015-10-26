package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by samuel on 24/09/15.
 */
public class ConnectedClient implements Runnable{
    Socket socket;
    String username;
    Thread threadGetMessage;
    BufferedReader in;
    PrintWriter out;

    public ConnectedClient(Socket socket, String username) {
        configClient(socket);
        this.username = username;
    }

    public void configClient(Socket socket) {
        this.socket = socket;

        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            threadGetMessage = new Thread(this);
            threadGetMessage.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMessages() {
        try{
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);

                for (ConnectedClient client : MultiUserChatServer.clients) {
                    client.sendMessage(message);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

    @Override
    public void run() {
        getMessages();
    }
}
