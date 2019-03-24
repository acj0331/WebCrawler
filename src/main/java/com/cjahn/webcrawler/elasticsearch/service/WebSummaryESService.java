package com.cjahn.webcrawler.elasticsearch.service;

import java.util.List;

import com.cjahn.webcrawler.object.ItemObject;
/*
 * https://kimseunghyun76.tistory.com/444
 * */
public interface WebSummaryESService {
    public ItemObject save(ItemObject item);
    
    public void delete(ItemObject item);
    
//    public ItemObject findItem(String link);
    
    Iterable<ItemObject> findAll();
    
    List<ItemObject> findByUrl(String url);
    List<ItemObject> findByTitle(String title);
    List<ItemObject> findByBase64(String base64);
}
