package com.cjahn.webcrawler.elasticsearch.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cjahn.webcrawler.object.CollectInfo;

public interface ReqCollectRepository extends ElasticsearchRepository<CollectInfo, Long>{

//	Optional<ReqCollect> findById(Long id);
	
}
