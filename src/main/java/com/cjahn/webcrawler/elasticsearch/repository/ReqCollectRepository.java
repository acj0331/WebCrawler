package com.cjahn.webcrawler.elasticsearch.repository;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cjahn.webcrawler.object.ReqCollect;

public interface ReqCollectRepository extends ElasticsearchRepository<ReqCollect, Long>{

//	Optional<ReqCollect> findById(Long id);
	
}
