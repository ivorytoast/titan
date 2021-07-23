package backend.nyc.com.titan.zeromq;

import backend.nyc.com.titan.common.BoardUtils;
import backend.nyc.com.titan.serializer.Serializer;
import lombok.extern.java.Log;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Log
public class Sub implements Runnable {

    private final String topic;

    public Sub(String topic) {
        this.topic = topic;
    }

    private String getTopic() {
        return this.topic;
    }

    @Override
    public void run() {
        try (ZContext context = new ZContext()) {
            System.out.println("Starting a new client listening to: [" + getTopic() + "]");
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            subscriber.connect("tcp://proxy.titan-backend-nyc.com:5556");
//            subscriber.connect("tcp://localhost:5556");
            subscriber.subscribe(topic.getBytes(ZMQ.CHARSET));
            System.out.println("Listening for new packets...");
            while (true) {
                String topic = subscriber.recvStr();
                if (topic == null)
                    break;
                if (topic.equalsIgnoreCase(getTopic())) {
                    String data = subscriber.recvStr();
                    System.out.println("---");
                    BoardUtils.PrintBoard(Serializer.deserializeBoard(data));
                    System.out.println("---");
                } else {
                    log.info("The topic: [" + topic + "] is not what this session is listening for...");
                }
            }
            System.out.println("Finished listening and shutting down...");
        }
        System.out.println("Shut down...");
    }

}