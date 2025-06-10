package alliex.com.backoffice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Backoffice is running!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Backoffice!";
    }
}
