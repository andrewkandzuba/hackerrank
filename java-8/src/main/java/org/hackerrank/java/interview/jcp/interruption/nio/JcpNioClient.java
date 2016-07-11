package org.hackerrank.java.interview.jcp.interruption.nio;

import org.hackerrank.java.interview.jcp.utils.ExceptionsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class JcpNioClient implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(JcpNioServer.class);

    private final int port;

    private JcpNioClient(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 1111;
        if (args.length >= 1) {
            Scanner scanner = new Scanner(args[0]);
            port = scanner.nextInt();
        }
        JcpNioClient client = new JcpNioClient(port);
        Thread thread = new Thread(client);
        thread.setDaemon(true);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void run() {
        InetSocketAddress addr = new InetSocketAddress("localhost", 1111);
        SocketChannel client = null;
        try {
            client = SocketChannel.open(addr);
            log.info(String.format("Connecting to Server on port %s...", port));
            send(client, "Facebook");
            send(client, "Twitter");
            send(client, "IBM");
            send(client, "Google");
        } catch (IOException e) {
            throw ExceptionsManager.launderThrowable(e);
        } finally {
            if(client != null){
                try {
                    send(client, JcpNioServer.Commands.buy.toString());
                    client.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    private static void send(SocketChannel client, String companyName) throws IOException {
        byte[] message = companyName.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(message);
        client.write(buffer);

        log.info("sending: " + companyName);
        buffer.clear();
    }
}
