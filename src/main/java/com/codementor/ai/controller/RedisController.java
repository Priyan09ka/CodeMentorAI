package com.codementor.ai.controller;

import com.codementor.ai.service.RedisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService){
        this.redisService=redisService;
    }

    @PostMapping
    public String save(@RequestParam String key,
                       @RequestParam String value){
        redisService.save(key, value);
        return "Value saved successfully!";
    }

    @GetMapping("/{key}")
    public String get(@PathVariable String key){
        return redisService.get(key);
    }

    @DeleteMapping("/{key}")
    public String delete(@PathVariable String key){
        Boolean deleted=redisService.delete(key);

        return  Boolean.TRUE.equals(deleted)
                ? "Key deleted successfully!"
                : "Key not found!";
    }

    @PostMapping("/{key}/expire")
    public String saveWithExpiration(@PathVariable String key,
                                     @RequestParam String value,
                                     @RequestParam long seconds){
        if(seconds<=0){
            throw new IllegalArgumentException("Expiration time must be greater than 0");
        }
        redisService.saveWithExpiration(key, value, seconds);
        return "Value saved with expiration successfully!";
    }
}
