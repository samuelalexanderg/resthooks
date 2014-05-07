package com.byteflair.resthooks.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

/**
 * Created by dcerecedo on 24/04/14.
 */
@Controller
@RequestMapping(value="/callback", headers="Accept=application/json", produces="application/json")
public class CallbackController {


    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity acknowledge(@RequestBody URI uri) {
        synchronized(this) {

        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
