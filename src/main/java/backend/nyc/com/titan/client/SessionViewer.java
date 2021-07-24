package backend.nyc.com.titan.client;

import backend.nyc.com.titan.zeromq.Sub;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SessionViewer {

    public static void main(String[] args) {
        Executor taskExecutor = Executors.newSingleThreadExecutor();

        try {
            taskExecutor.execute(new Sub("696969"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
