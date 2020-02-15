package com.orange.controller;

import com.orange.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UrlController {

    @Autowired
    UrlService urlService;
    @RequestMapping(value = {"/add_url"},produces = {"application/json"},method = {RequestMethod.POST})
    public ResponseEntity<String> addUrl(@RequestBody String originalUrl) {
        String shortUrl = urlService.saveUrl(originalUrl);

        return new ResponseEntity<String>(shortUrl,HttpStatus.OK);
    }

    @RequestMapping(value = {"/original_url/{shortUrl}"},produces = {"application/json"},method = {RequestMethod.GET})
    public ResponseEntity<String> getOriginalUrl(@PathVariable("shortUrl")String shortUrl) {
        String originalUrl = urlService.getOriginalUrlFromShort(shortUrl);
        if(originalUrl==null)
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<String>(originalUrl,HttpStatus.OK);
    }
}
