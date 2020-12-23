package com.jeffrey.blog.examples.controller;

import com.jeffrey.blog.examples.service.IdBuilderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "id-builder")
public class IdBuilderController {
    @Resource
    private IdBuilderService idBuilderService;

    @GetMapping("increase-id")
    public Long increaseId(int code) {
        long result = idBuilderService.unionId(code);
        System.out.println(result);
        return result;
    }

    @GetMapping("increase-seq-id")
    public Long increaseSeqId(int code) {
        long result = idBuilderService.unionSeqId(code);
        System.out.println(result);
        return result;
    }

    @GetMapping("increase-seq-id-str")
    public String unionSeqIdStr(int code) {
        String result = idBuilderService.unionSeqIdStr(code);
        System.out.println(result);
        return result;
    }

    @GetMapping("increase-id-str")
    public String unionIdStr(int code) {
        String result = idBuilderService.unionIdStr(code);
        System.out.println(result);
        return result;
    }
}