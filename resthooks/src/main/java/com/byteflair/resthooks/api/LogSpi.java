package com.byteflair.resthooks.api;

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
@RequestMapping("/logs")
public interface LogSpi {

    /**
     * Obtains a history of all logged events for the requesting client application.
     *
     * @return
     */
    @RequestMapping(method=RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<Log>> getResourceCollection();

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Log> getResource(@PathVariable("id") String id);

}
