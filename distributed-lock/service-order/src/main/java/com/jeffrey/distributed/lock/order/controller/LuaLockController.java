package com.jeffrey.distributed.lock.order.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
public class LuaLockController {
    @Resource(name = "set")
    private DefaultRedisScript<Boolean> redisScript;
    @Resource(name = "del")
    private DefaultRedisScript<Boolean> redisScriptDel;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/lua")
    public ResponseEntity lua() {
        List<String> keys = Arrays.asList("testLua", "hello lua");
        Boolean execute = stringRedisTemplate.execute(redisScript, keys, "100");
        return null;
    }

    @GetMapping("/lua-del")
    public ResponseEntity luaDel() {
        List<String> keys = Arrays.asList("testLua");
        Boolean execute = stringRedisTemplate.execute(redisScriptDel, keys,"hello lua");
        return null;
    }
}
