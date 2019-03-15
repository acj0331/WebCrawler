package com.cjahn.webcrawler.elasticsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.elasticsearch.repository.WebSummaryRepository;
import com.cjahn.webcrawler.object.ItemObject;

@Service
public class WebSummaryServiceImpl implements WebSummaryService{
    private WebSummaryRepository repository;
    
    @Autowired
    public void setWebSummaryRepository(WebSummaryRepository repository){
        this.repository = repository;
    }
    
    @Override
    public ItemObject save(ItemObject item) {
        return repository.save(item);
    }

    @Override
    public void delete(ItemObject item) {
        repository.delete(item);
    }

    @Override
    public Iterable<ItemObject> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<ItemObject> findByLink(String link, PageRequest pageRequest) {
        // TODO Auto-generated method stub
        return null;
    }

}
