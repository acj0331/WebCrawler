package com.cjahn.webcrawler.elasticsearch.repository;

import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cjahn.webcrawler.object.CollectInfo;

public interface CollectRepository extends ElasticsearchRepository<CollectInfo, Long>{

//	Optional<ReqCollect> findById(Long id);
	
}
