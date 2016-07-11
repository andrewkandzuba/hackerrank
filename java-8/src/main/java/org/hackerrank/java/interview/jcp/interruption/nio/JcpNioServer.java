package org.hackerrank.java.interview.jcp.interruption.nio;

import org.hackerrank.java.interview.jcp.interruption.concurrent.ExecutorServiceShutdown;
import org.hackerrank.java.interview.jcp.utils.ExceptionsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JcpNioServer extends Thread {
    private static final Logger log = LoggerFactory.getLogger(JcpNioServer.class);

    enum Commands {
        buy("buy");

        private final String command;

        Commands(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return command;
        }
    }

    private final int port;

    private JcpNioServer(int port) {
        this.port = port;
    }

    public static void main(String... args) throws IOException {
        int port = 1111;
        if (args.length >= 1) {
            Scanner scanner = new Scanner(args[0]);
            port = scanner.nextInt();
        }

        JcpNioServer server = new JcpNioServer(port);
        Thread serverThread = new Thread(server);
        serverThread.start();

        ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(1);
        ses.schedule(() -> {
            log.info("Interrupts server thread");
            serverThread.interrupt();
        }, 10000, TimeUnit.MILLISECONDS);

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        ses.shutdown();
        ExecutorServiceShutdown.shutdownGracefully(ses);
    }

    @Override
    public void run() {
        log.info("Server is started");
        try (Selector selector = Selector.open()) {
            try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
                ssc.bind(new InetSocketAddress(port));
                ssc.configureBlocking(false);
                ssc.register(selector, ssc.validOps());

                while (!Thread.currentThread().isInterrupted()) {
                    log.debug("i'm a server and i'm waiting for new connection and buffer select...");
                    // Selects a set of keys whose corresponding channels are ready for I/O operations
                    // This is an only blocking method in this thread.
                    selector.select();

                    // token representing the registration of a SelectableChannel with a Selector
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();

                    while (it.hasNext()) {
                        SelectionKey myKey = it.next();
                        try {
                            // Tests whether this key's channel is ready to accept a new socket connection
                            if (myKey.isAcceptable()) {
                                // This method throws ClosedByInterruptException exception.
                                SocketChannel sc = ssc.accept();

                                // Adjusts this channel's blocking mode to false
                                sc.configureBlocking(false);

                                // Operation-set bit for read operations
                                sc.register(selector, SelectionKey.OP_READ);
                                log.info(String.format("Connection Accepted: %s", sc.getLocalAddress()));

                                // Tests whether this key's channel is ready for reading
                            } else if (myKey.isReadable()) {

                                SocketChannel sc = (SocketChannel) myKey.channel();
                                ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
                                sc.read(crunchifyBuffer);
                                String result = new String(crunchifyBuffer.array()).trim();

                                log.info(String.format("Message received: %s", result));

                                if (result.equals(Commands.buy.toString())) {
                                    sc.close();
                                    log.info(String.format("It's time to close connection as we got the session finalization command: %s", result));
                                    log.info("Server will keep running. Try running client again to establish new connection");
                                }
                            }
                        } catch (ClosedByInterruptException e) {
                            // If the thread has been interrupted then we have to exit the loop now
                            log.error(e.getMessage(), e);
                            break;
                        } finally {
                            it.remove();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw ExceptionsManager.launderThrowable(e);
        }
        log.info("Server is stopped");
    }
}
