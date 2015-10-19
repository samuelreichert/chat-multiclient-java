/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JTextArea;

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
    
    JTextArea txtOut;
    JTextArea txtOutClients;
    
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
    
    public void setTxtOutClients(JTextArea txtOutClients) {
        this.txtOutClients = txtOutClients;
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
                txtOut.append(msg);
                txtOut.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
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

            if(message.equals("Conectado")) {
                getMessages();
            } else {
                txtOut.append(message);
                txtOut.append("\n");
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //método que executa, conforme implementação de Runnable
    public void run() {
        configClient();
    }
}
