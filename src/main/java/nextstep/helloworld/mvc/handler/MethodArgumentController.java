package nextstep.helloworld.mvc.handler;

import nextstep.helloworld.mvc.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/method-argument")
public class MethodArgumentController {

    @GetMapping("/users")
    public ResponseEntity<List<User>> requestParam(@RequestParam("userName") String userName) {
        List<User> users = Arrays.asList(
                new User(userName, "email"),
                new User(userName, "email")
        );
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/users/body")
    public ResponseEntity requestBody(@RequestBody User other) {
        User newUser = new User(1L, other.getName(), other.getEmail());
        return ResponseEntity.created(URI.create("/users/" + newUser.getId())).body(newUser);
    }

}