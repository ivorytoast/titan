package backend.nyc.com.titan.zeromq;

import backend.nyc.com.titan.common.Utils;
import backend.nyc.com.titan.serializer.Serializer;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Sub implements Runnable {

    @Override
    public void run() {
        try (ZContext context = new ZContext()) {
            System.out.println("Starting a new client...");
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
//            subscriber.connect("tcp://proxy.titan-backend-nyc.com:5556");
            subscriber.connect("tcp://localhost:5556");
            String subscription = "test";
            subscriber.subscribe(subscription.getBytes(ZMQ.CHARSET));
            System.out.println("Listening for new packets...");
            while (true) {
                String topic = subscriber.recvStr();
                if (topic == null)
                    break;
                String data = subscriber.recvStr();
                assert (topic.equals(subscription));
                System.out.println("---");
                Utils.printBoard(Serializer.deserializeBoard(data));
                System.out.println("---");
            }
            System.out.println("Finished listening and shutting down...");
        }
        System.out.println("Shut down...");
    }

}