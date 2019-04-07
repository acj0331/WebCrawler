package com.cjahn.webcrawler.elasticsearch.service;

import java.util.List;
import java.util.Optional;

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
    Optional<ItemObject> findByHash(String hash);

	public Long getTotalCount(Long id);
}
