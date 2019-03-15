package com.cjahn.webcrawler.elasticsearch.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cjahn.webcrawler.object.ItemObject;

public interface WebSummaryRepository extends ElasticsearchRepository<ItemObject, Integer>{
    Page<ItemObject> findByLink(String link, PageRequest pageRequest);
    
    
    
    
//    List<ItemObject> findBySize(int from, int size);
}
