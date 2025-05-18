package com.example.pollingapp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ElementCollection
    private List<String> options;

    @ElementCollection
    private List<Integer> votes;

    public Poll() {}

    public Poll(String question, List<String> options) {
        this.question = question;
        this.options = options;
        this.votes = options.stream().map(o -> 0).toList();
    }

    // Getters and setters omitted for brevity
}
