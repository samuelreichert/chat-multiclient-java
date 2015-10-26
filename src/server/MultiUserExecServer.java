package server;

/**
 * Created by samuel on 24/09/15.
 */
public class MultiUserExecServer {
    public static void main(String[] args) {
        MultiUserChatServer multiUserChatServer = new MultiUserChatServer();
        multiUserChatServer.configServer();
        multiUserChatServer.waitClients();
    }
}
