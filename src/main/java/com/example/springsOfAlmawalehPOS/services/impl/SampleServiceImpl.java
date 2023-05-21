package com.example.springsOfAlmawalehPOS.services.impl;

import com.example.springsOfAlmawalehPOS.services.SampleService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
    @Override
    public String test() {
        return "Hello";
    }
}
