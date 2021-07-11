package backend.nyc.com.titan.zeromq;

import lombok.extern.java.Log;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZThread;

@Log
public class ClientInitializer implements Runnable {

    public ClientInitializer() {
        log.info("Starting Subscriber...");
    }

    @Override
    public void run() {
        try (ZContext ctx = new ZContext()) {
            ZMQ.Socket subscriberError = ZThread.fork(ctx, new Subscriber());

            if (subscriberError == null) {
                System.out.println("Error creating the subscriber thread...");
                return;
            }

            ZMQ.Socket subscriber = ctx.createSocket(SocketType.XSUB);
            subscriber.connect("tcp://localhost:6000");
        }
    }

}
