package com.orange.service;

import com.orange.model.URL;
import com.orange.repository.UrlRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    public String  generateShortUrlFromOriginal(String originalUrl){
        String shortUrl = encodeUrlToBase64(originalUrl);
        Optional<URL> exist = urlRepository.findById(shortUrl);
        while(exist.isPresent()) {
            shortUrl = shuffle(shortUrl);
            exist = urlRepository.findById(shortUrl);
        }
        return shortUrl;
    }
    public String getOriginalUrlFromShort(String shortUrl){
        Optional<URL> originalUrl = urlRepository.findById(shortUrl);
        if(!originalUrl.isPresent())
            return null;
        return originalUrl.get().getLongUrl();
    }
    public boolean deleteShortUrl(String shortUrl){
        return true;
    }
    public String saveUrl(String originalUrl){
        String shortUrl = generateShortUrlFromOriginal(originalUrl);
        URL url = new URL();
        url.setLongUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setNumberOfHits(0);
        urlRepository.save(url);
        return shortUrl;
    }
    public String encodeUrlToBase64(String originalUrl){
        String encodedString = Base64.getEncoder().encodeToString(originalUrl.getBytes());
        return encodedString.substring(encodedString.length()-12,(encodedString.length()-12)+6);
    }
    public String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }
}
