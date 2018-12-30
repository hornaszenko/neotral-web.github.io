package pl.lodz.uni.math.Communicator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Application runner.
 *
 * @author Piotr Krzyminski
 */
@SpringBootApplication
@EntityScan(basePackages = {"pl.lodz.uni.math.model"})
public class CommunicatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunicatorApplication.class, args);
    }

}

