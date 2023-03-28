package ru.netology.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable long id) {
        Optional<Post> currentPost = service.getById(id);

        return currentPost.map(post -> new ResponseEntity<>(post, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> removeById(@PathVariable long id) {
        Optional<Post> currentPost = service.removeById(id);

        return currentPost.map(post -> new ResponseEntity<>(post, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

//        return currentPost != null
//                ? new ResponseEntity<>(currentPost, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
