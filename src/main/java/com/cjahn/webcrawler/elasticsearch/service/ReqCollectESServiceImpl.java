package com.cjahn.webcrawler.elasticsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.elasticsearch.repository.ReqCollectRepository;
import com.cjahn.webcrawler.object.ReqCollect;

@Service
public class ReqCollectESServiceImpl implements ReqCollectESService{
	private ReqCollectRepository repository;
	
	@Autowired
	public void setReqCollectRepository(ReqCollectRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public ReqCollect save(ReqCollect reqCollect) {
		reqCollect.setId(System.currentTimeMillis());
		return repository.save(reqCollect);
	}

}
