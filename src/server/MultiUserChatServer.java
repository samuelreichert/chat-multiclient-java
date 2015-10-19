package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by samuel on 24/09/15.
 */
public class MultiUserChatServer {
    ServerSocket serverSocket;
    Socket socketNewClient;
    static List<ConnectedClient> clients = new ArrayList<>();
    static List<String> usernameClients = new ArrayList<>();
    Scanner keyboardIn = new Scanner(System.in);
    int porta;

    public void configServer() {
        try {
            System.out.print("Digite a porta que será usada pelo servidor: ");
            porta = keyboardIn.nextInt();

            serverSocket = new ServerSocket(porta);

            System.out.println("Servidor pronto para receber conexões!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void waitClients() {
        try{
            while(true) {
                socketNewClient = serverSocket.accept();
                usernameInUse(socketNewClient);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void usernameInUse(Socket socketNewClient) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socketNewClient.getInputStream()));
            PrintWriter out = new PrintWriter(socketNewClient.getOutputStream());
            String username = in.readLine();

            if (!usernameClients.contains(username)) {
                addClient(socketNewClient, username);
                out.println("Conectado");
            } else {
                out.println("Não conectado. Username já está em uso.");
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addClient(Socket socketNewClient, String username) {
        ConnectedClient newClient = new ConnectedClient(socketNewClient);
        usernameClients.add(username);
        clients.add(newClient);
    }
}
