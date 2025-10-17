package com.dreadfiles.springboot.controller;

import com.dreadfiles.springboot.model.Person;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloToName(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam(name = "name", defaultValue = "Guest") String name) {
        return "Greetings, " + name + "!";
    }

    @PostMapping("/person")
    public String createPerson(@RequestBody Person person) {
        return "Created person: " + person.getName() + ", Age: " + person.getAge();
    }
}