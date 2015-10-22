/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author gugel
 */
public class ChatClient implements Runnable{

    int port;
    String host;
    String username;
    Thread thClient;
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    ChatClienteGUI chatClientGui;
    List<String> listaUsers = new ArrayList();
    
    JTextArea txtOut;
    JList txtOutClients;
    int contador = 0;
    
    public void setPorta(int porta) {
        this.port = porta;
    }

    public int getPorta() {
        return port;
    }
    
    public void setHost(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    } 
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public void setTxtOut(JTextArea txtOut) {
        this.txtOut = txtOut;
    }
    
    public void setTxtOutClients(JList txtOutClients) {
        this.txtOutClients = txtOutClients;
    }

    public void setChatClientGui(ChatClienteGUI chatClientGui) {
        this.chatClientGui = chatClientGui;
    }
        
    public void configClient(){
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            waitAcceptance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void startThreadClient(){
        thClient = new Thread(this);
        thClient.start();
    }

    public void getMessages(){
        try {
            String msg;
            while((msg = in.readLine()) != null){
                if (msg.substring(0, 1).equals("+")) {
                    updateUsersList(msg);
                } else {
                    txtOut.append(msg);
                    txtOut.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUsersList(String message) {
        int inicio = message.indexOf("+");
        int fim = message.lastIndexOf("+");
        int length = Integer.parseInt(message.substring((inicio + 1), fim));
        String username = message.substring((fim + 1), message.length());

        System.out.println(length);

        listaUsers.add(username);

        if (listaUsers.size() == length) {
            DefaultListModel model = new DefaultListModel();
            txtOutClients.setModel(model);

            for (String user : listaUsers) {
                model.addElement(user);
            }
            listaUsers.clear();
        }
    }

    public void getMessagesConnected(Boolean b, String text) throws IOException{
        
        if (!b) {
            JOptionPane.showMessageDialog(null, text);
            socket.close();
            chatClientGui.enableDisconnected();
        }           
    }
    
    public void sendMessage(String msg){
        out.println(msg);
        out.flush();
        System.out.println(msg);
    }

    public void waitAcceptance() {
        try {
            out.println(username);
            out.flush();

            String message = in.readLine();

            if(!message.equals("Conectado")) {
                txtOut.append(message);
                txtOut.append("\n");
                socket.close();
                chatClientGui.enableDisconnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //método que executa, conforme implementação de Runnable
    public void run() {
        configClient();
        getMessages();
    }
}
