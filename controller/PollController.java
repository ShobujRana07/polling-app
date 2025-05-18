package com.example.pollingapp.controller;

import com.example.pollingapp.model.Poll;
import com.example.pollingapp.repository.PollRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/polls")
public class PollController {
    private final PollRepository repository;

    public PollController(PollRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return repository.findAll();
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return repository.save(poll);
    }

    @GetMapping("/{id}")
    public Optional<Poll> getPoll(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/{id}/vote")
    public Poll vote(@PathVariable Long id, @RequestParam int optionIndex) {
        Poll poll = repository.findById(id).orElseThrow();
        List<Integer> votes = poll.getVotes();
        votes.set(optionIndex, votes.get(optionIndex) + 1);
        poll.setVotes(votes);
        return repository.save(poll);
    }
}
