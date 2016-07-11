package org.hackerrank.java.interview.jcp.interruption;

import org.hackerrank.java.interview.jcp.utils.ExceptionsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class JcpNioServer extends Thread {
    private static final Logger log = LoggerFactory.getLogger(JcpNioServer.class);

    // Static configuration
    private final int port;
    private volatile boolean shutdown;

    public JcpNioServer(int port){
        this.port = port;
    }

    public static void main(String... args) throws IOException {
        if (args.length < 0) {
            System.err.println("");
        }
    }

    @Override
    public void run() {
        try (Selector selector = Selector.open()) {
            try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
                // Create address
                InetSocketAddress addr = new InetSocketAddress(port);
                // Bind to local port and set a non-blocking mode
                ssc.bind(addr);
                ssc.configureBlocking(false);
                // Register selection keys
                SelectionKey sk = ssc.register(selector, ssc.validOps());

                while (!shutdown) {
                    log.info("i'm a server and i'm waiting for new connection and buffer select...");
                    // Selects a set of keys whose corresponding channels are ready for I/O operations
                    selector.select();

                    // token representing the registration of a SelectableChannel with a Selector
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();

                    while (it.hasNext()) {
                        SelectionKey myKey = it.next();

                        // Tests whether this key's channel is ready to accept a new socket connection
                        if (myKey.isAcceptable()) {
                            SocketChannel sc = ssc.accept();

                            // Adjusts this channel's blocking mode to false
                            sc.configureBlocking(false);

                            // Operation-set bit for read operations
                            sc.register(selector, SelectionKey.OP_READ);
                            log.debug(String.format("Connection Accepted: %s", sc.getLocalAddress()));

                            // Tests whether this key's channel is ready for reading
                        } else if (myKey.isReadable()) {

                            SocketChannel sc = (SocketChannel) myKey.channel();
                            ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
                            sc.read(crunchifyBuffer);
                            String result = new String(crunchifyBuffer.array()).trim();

                            log.debug(String.format("Message received: %s", result));

                            if (result.equals("Crunchify")) {
                                sc.close();
                                log.info("It's time to close connection as we got last company name 'Crunchify'");
                                log.info("Server will keep running. Try running client again to establish new connection");
                            }
                        }
                        it.remove();
                    }
                }
            }
        } catch (IOException e) {
            throw ExceptionsManager.launderThrowable(e);
        }
    }
}
