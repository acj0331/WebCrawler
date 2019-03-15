package com.cjahn.webcrawler.elasticsearch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cjahn.webcrawler.object.ItemObject;
/*
 * https://kimseunghyun76.tistory.com/444
 * */
public interface WebSummaryService {
    public ItemObject save(ItemObject item);
    
    public void delete(ItemObject item);
    
//    public ItemObject findItem(String link);
    
    Iterable<ItemObject> findAll();
    
    Page<ItemObject> findByLink(String link, PageRequest pageRequest);
}
