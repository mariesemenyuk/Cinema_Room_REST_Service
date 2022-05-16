package task;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Message> list = new ArrayList<>();

    @PostMapping("/message")
    public void postMessage(@RequestBody Message msg) {
        list.add(msg);
    }

    @GetMapping("/message")
    public Message getMessage() {
        return list.get(list.size() - 1);
    }
}
