package com.cjahn.webcrawler.elasticsearch.service;

import java.util.Optional;

import com.cjahn.webcrawler.object.CollectInfo;

public interface CollectESService {
	public CollectInfo save(CollectInfo reqCollect);

	Optional<CollectInfo> findById(Long id);

	public Iterable<CollectInfo> findAll();
}
