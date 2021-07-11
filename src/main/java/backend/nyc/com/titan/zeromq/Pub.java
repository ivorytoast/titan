package backend.nyc.com.titan.zeromq;

import backend.nyc.com.titan.model.Session;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Random;

public class Pub implements Runnable {

    public Pub() {
        System.out.println("Starting publisher...");
    }

    @Override
    public void run() {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5556");

            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(8000);
                publisher.send("test", ZMQ.SNDMORE);
                publisher.send("<!5~2~F~B~4~4~E~T~5~5~B~F!>@<!2~2~2~2~0~0~1~1~1~1!>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
