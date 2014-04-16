package com.byteflair.resthooks.api;

import com.byteflair.rest.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dcerecedo on 1/24/14.
 */
@Controller
@RequestMapping(value="/logs", headers="Accept=application/json", produces="application/json")
public interface LogSpi {

    /**
     * Obtains a history of all logged events for the requesting client application.
     *
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<List<Log>> getResourceCollection();

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Log> getResource(@PathVariable("id") String id) throws ResourceNotFoundException;

}
