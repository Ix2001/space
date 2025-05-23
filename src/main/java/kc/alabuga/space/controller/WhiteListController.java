package kc.alabuga.space.controller;

import kc.alabuga.space.service.WhiteListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/whitelist")
public class WhiteListController {
    public WhiteListController(WhiteListService service) {
        this.service = service;
    }

    private final WhiteListService service;

    @PostMapping("/add")
    public String add(@RequestBody String ip) {
        return service.add(ip);
    }

    @DeleteMapping("/remove")
    public String remove(@RequestBody String ip) {
        return service.remove(ip);
    }

    @GetMapping("/list")
    public List<String> list() {
        return service.list();
    }
}
