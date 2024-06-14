package PochingLao.Controller;

import PochingLao.entity.Messages;
import PochingLao.Repo.MessagesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesRepository messagesRepository;

    @Autowired
    public MessagesController(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @PostMapping("/addMessages")
    public ResponseEntity<Messages> addMessages(@RequestBody Messages message) {
        Messages savedMessage = messagesRepository.save(message);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<Messages>> getMessages() {
        List<Messages> messagesList = messagesRepository.findAll();
        return new ResponseEntity<>(messagesList, HttpStatus.OK);
    }

    @GetMapping("/getMessages/{id}")
    public ResponseEntity<Messages> getMessageById(@PathVariable Long id) {
        Optional<Messages> optionalMessage = messagesRepository.findById(id);
        return optionalMessage.map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
