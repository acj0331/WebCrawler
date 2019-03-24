package com.cjahn.webcrawler.elasticsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.elasticsearch.repository.WebSummaryRepository;
import com.cjahn.webcrawler.object.ItemObject;

@Service
public class WebSummaryESServiceImpl implements WebSummaryESService{
	
    private WebSummaryRepository repository;
    
    @Autowired
    public void setWebSummaryRepository(WebSummaryRepository repository){
        this.repository = repository;
    }
    
    @Override
    public ItemObject save(ItemObject item) {
//    	List<ItemObject> obj = this.findByBase64(item.getBase64());
//    	if(obj.size()>0)
//    		return null;
    	
    	PageImpl<ItemObject> tes = (PageImpl<ItemObject>) this.findAll();
		item.setId(tes.getTotalElements());
		
		
        return repository.save(item);
    }

    @Override
    public void delete(ItemObject item) {
        repository.delete(item);
    }

    @Override
    public Iterable<ItemObject> findAll() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

    @Override
    public List<ItemObject> findByUrl(String url) {
        // TODO Auto-generated method stub
        return repository.findByLink(url);
    }

	@Override
	public List<ItemObject> findByTitle(String title) {
		// TODO Auto-generated method stub
		return repository.findByTitle(title);
	}

	@Override
	public List<ItemObject> findByBase64(String base64) {
		// TODO Auto-generated method stub
		return repository.findByBase64(base64);
	}
    
    
}
