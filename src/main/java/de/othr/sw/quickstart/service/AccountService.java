package de.othr.sw.quickstart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private CustomerServiceIF customerService;
}
