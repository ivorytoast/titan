package backend.nyc.com.titan.zeromq;

import org.zeromq.ZContext;
import org.zeromq.ZFrame;
import org.zeromq.ZMQ;
import org.zeromq.ZThread;

public class Listener implements ZThread.IAttachedRunnable {

    @Override
    public void run(Object[] args, ZContext ctx, ZMQ.Socket pipe) {
        while (true) {
            ZFrame frame = ZFrame.recvFrame(pipe);
            if (frame == null)
                break;
            frame.destroy();
        }
    }

}
