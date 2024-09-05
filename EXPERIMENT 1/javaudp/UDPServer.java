import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class UDPServer {
    private static final int PORT = 12345;
    private static final int BUFFER_SIZE = 1024;
    private static Set<ClientInfo> clients = new HashSet<>();

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            System.out.println("UDP Server is running...");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + received);

                // Add the client to the list if it's not already there
                ClientInfo clientInfo = new ClientInfo(packet.getAddress(), packet.getPort());
                clients.add(clientInfo);

                // Broadcast the message to all clients
                broadcast(socket, received, clientInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void broadcast(DatagramSocket socket, String message, ClientInfo sender) throws IOException {
        byte[] buffer = message.getBytes();
        for (ClientInfo client : clients) {
            // Do not send the message back to the sender
            if (!client.equals(sender)) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, client.address, client.port);
                socket.send(packet);
            }
        }
    }

    private static class ClientInfo {
        InetAddress address;
        int port;

        ClientInfo(InetAddress address, int port) {
            this.address = address;
            this.port = port;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            ClientInfo that = (ClientInfo) obj;
            return port == that.port && address.equals(that.address);
        }

        @Override
        public int hashCode() {
            return address.hashCode() * 31 + port;
        }
    }
}
