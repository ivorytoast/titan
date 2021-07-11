package backend.nyc.com.titan.config;

import backend.nyc.com.titan.zeromq.Initializer;
import backend.nyc.com.titan.zeromq.Pub;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

@Log
@Configuration
public class TitanConfig {

    @Bean
    public CommandLineRunner pubSubInitializer(TaskExecutor executor) {
        return args -> executor.execute(new Pub());
    }

}
