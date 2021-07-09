package backend.nyc.com.titan.zeromq;

import lombok.extern.java.Log;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZThread;

@Log
public class Initializer implements Runnable {

    public Initializer() {
        log.info("Starting ZeroMQ Initializer");
    }

    @Override
    public void run() {
        try (ZContext ctx = new ZContext()) {
            ZMQ.Socket publisherError = ZThread.fork(ctx, new Publisher());
            ZMQ.Socket subscriberError = ZThread.fork(ctx, new Subscriber());

            if (publisherError == null) {
                System.out.println("Error creating the publisher thread...");
                return;
            }

            if (subscriberError == null) {
                System.out.println("Error creating the subscriber thread...");
                return;
            }

            ZMQ.Socket subscriber = ctx.createSocket(SocketType.XSUB);
            subscriber.connect("tcp://localhost:6000");

            ZMQ.Socket publisher = ctx.createSocket(SocketType.XPUB);
            publisher.bind("tcp://*:6001");

            ZMQ.Socket listener = ZThread.fork(ctx, new Listener());

            ZMQ.proxy(subscriber, publisher, listener);

            System.out.println("interrupted");
        }
    }

}
