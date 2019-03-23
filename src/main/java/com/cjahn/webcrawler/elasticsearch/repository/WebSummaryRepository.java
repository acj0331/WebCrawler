package com.cjahn.webcrawler.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.cjahn.webcrawler.object.ItemObject;

public interface WebSummaryRepository extends ElasticsearchRepository<ItemObject, Long>{
	List<ItemObject> findByLink(String link);

	List<ItemObject> findByTitle(String title);
}
