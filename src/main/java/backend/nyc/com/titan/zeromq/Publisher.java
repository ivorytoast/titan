package backend.nyc.com.titan.zeromq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZThread;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Publisher implements ZThread.IAttachedRunnable {

    private ConcurrentLinkedDeque<String> updates;

    public void addToUpdates(int roomId, int value) {
        this.updates.add(String.format("%s-%05d", "Room-" + roomId, value));
    }

    public Publisher() {
        updates = new ConcurrentLinkedDeque<>();
        Random rand = new Random(1000);
        addToUpdates(1, rand.nextInt(100000));
        addToUpdates(2, rand.nextInt(100000));
        addToUpdates(3, rand.nextInt(100000));
        addToUpdates(1, rand.nextInt(100000));
        addToUpdates(2, rand.nextInt(100000));
        addToUpdates(3, rand.nextInt(100000));
        addToUpdates(1, rand.nextInt(100000));

        for (String item : updates) {
            System.out.println(item);
        }

        System.out.println("------");
    }

    @Override
    public void run(Object[] args, ZContext ctx, ZMQ.Socket pipe) {
        ZMQ.Socket publisher = ctx.createSocket(SocketType.PUB);
        publisher.bind("tcp://*:6000");

        while (!Thread.currentThread().isInterrupted()) {
            if (updates.size() > 0) {
                String string = updates.poll();
                if (!publisher.send(string)) {
                    System.out.println("Whoops...");
                    break; // Interrupted
                }
                try {
                    Thread.sleep(600);
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }
        publisher.close();
    }

}
