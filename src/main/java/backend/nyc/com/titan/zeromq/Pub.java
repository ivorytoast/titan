package backend.nyc.com.titan.zeromq;

import lombok.extern.java.Log;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.concurrent.ConcurrentLinkedDeque;

@Log
public class Pub implements Runnable {

    public ConcurrentLinkedDeque<String> updates;

    public Pub() {
        updates = new ConcurrentLinkedDeque<>();
        System.out.println("Starting publisher...");
    }

    public void addToUpdates(String updatedBoard) {
        this.updates.add(updatedBoard);
    }

    @Override
    public void run() {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5556");

            while (!Thread.currentThread().isInterrupted()) {
                if (updates.size() > 0) {
                    log.info("Received updated board!");
                    String boardUpdate = updates.poll();
                    publisher.send("test", ZMQ.SNDMORE);
                    publisher.send(boardUpdate);
                    log.info("Sent out updated board!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
