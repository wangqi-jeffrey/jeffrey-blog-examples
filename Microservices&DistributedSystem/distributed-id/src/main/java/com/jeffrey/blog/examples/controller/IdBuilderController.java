package com.jeffrey.blog.examples.controller;

import com.jeffrey.blog.examples.service.IdBuilderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "id-builder")
public class IdBuilderController {
    @Resource
    private IdBuilderService idBuilderService;

    @GetMapping("increase-id")
    public Long increaseId(int code) {
        Long result = idBuilderService.unionId(code);
        log.info("-" + result);
        return result;
    }

    @GetMapping("increase-seq-id")
    public Long increaseSeqId(int code) {
        Long result = idBuilderService.unionSeqId(code);
        log.info("-" + result);
        return result;
    }

    @GetMapping("increase-seq-id-str")
    public String unionSeqIdStr(int code) {
        String result = idBuilderService.unionSeqIdStr(code);
        log.info(result);
        return result;
    }

    @GetMapping("increase-id-str")
    public String unionIdStr(int code) {
        String result = idBuilderService.unionIdStr(code);
        log.info(result);
        return result;
    }
}