package backend.nyc.com.titan.zeromq;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZThread;

public class Subscriber implements ZThread.IAttachedRunnable {

    @Override
    public void run(Object[] args, ZContext ctx, ZMQ.Socket pipe) {
        ZMQ.Socket subscriber = ctx.createSocket(SocketType.SUB);
        subscriber.connect("tcp://localhost:6001");
        subscriber.subscribe("Room-1".getBytes(ZMQ.CHARSET));
        subscriber.subscribe("Room-2".getBytes(ZMQ.CHARSET));

        while (true) {
            String string = subscriber.recvStr();
            if (string == null)
                break;
            System.out.println("Subscriber: " + string);
        }
        subscriber.close();
    }

}
