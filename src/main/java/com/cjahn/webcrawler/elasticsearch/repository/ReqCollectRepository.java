package com.cjahn.webcrawler.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cjahn.webcrawler.object.ReqCollect;

public interface ReqCollectRepository extends ElasticsearchRepository<ReqCollect, Long>{

}
