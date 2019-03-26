package com.cjahn.webcrawler.elasticsearch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.elasticsearch.repository.CollectRepository;
import com.cjahn.webcrawler.object.CollectInfo;

@Service
public class CollectESServiceImpl implements CollectESService{
	private CollectRepository repository;
	
	@Autowired
	public void setReqCollectRepository(CollectRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public CollectInfo save(CollectInfo collectInfo) {
		//reqCollect.setId(System.currentTimeMillis());
		return repository.save(collectInfo);
	}

	@Override
	public Optional<CollectInfo> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Iterable<CollectInfo> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	
}
