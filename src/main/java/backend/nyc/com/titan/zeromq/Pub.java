package backend.nyc.com.titan.zeromq;

import backend.nyc.com.titan.model.BoardUpdate;
import lombok.extern.java.Log;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.concurrent.ConcurrentLinkedDeque;

@Log
public class Pub implements Runnable {

    public ConcurrentLinkedDeque<BoardUpdate> updates;

    public Pub() {
        updates = new ConcurrentLinkedDeque<>();
        System.out.println("Starting publisher...");
    }

    public void addToUpdates(BoardUpdate updateObject) {
        this.updates.add(updateObject);
    }

    @Override
    public void run() {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5556");

            while (!Thread.currentThread().isInterrupted()) {
                if (updates.size() > 0) {
                    log.info("Received updated board!");
                    BoardUpdate update = updates.poll();
                    if (update != null) {
                        String message = update.getBoard();
                        publisher.send("*", ZMQ.SNDMORE);
                        publisher.send(message);
                        log.info("Published: [ *, " + message + "]");
                    } else {
                        log.warning("Nothing to publish since the object was null in the queue...");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
