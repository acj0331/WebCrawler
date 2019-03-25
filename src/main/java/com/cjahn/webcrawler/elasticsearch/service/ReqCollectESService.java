package com.cjahn.webcrawler.elasticsearch.service;

import java.util.Optional;

import com.cjahn.webcrawler.object.ReqCollect;

public interface ReqCollectESService {
	public ReqCollect save(ReqCollect reqCollect);

	Optional<ReqCollect> findById(Long id);
}
