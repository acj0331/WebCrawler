package com.cjahn.webcrawler.elasticsearch.service;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.List;
import java.util.Optional;

import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.cjahn.webcrawler.elasticsearch.repository.CollectRepository;
import com.cjahn.webcrawler.object.CollectInfo;

@Service
public class CollectESServiceImpl implements CollectESService{
	@Autowired
	private CollectRepository repository;
	@Autowired
	private ElasticsearchTemplate esTemplate;
	
//	@Autowired
//	public void setReqCollectRepository(CollectRepository repository) {
//		this.repository = repository;
//	}
	
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

	@Override
	public List<CollectInfo> findAllSortById(int page, int row) {
		// TODO Auto-generated method stub
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("collects")
			      .withTypes("collect").withQuery(matchAllQuery())
			      .withSort(new FieldSortBuilder("id").order(SortOrder.DESC))
			      .withPageable(PageRequest.of(page, row)).build();
		
		return esTemplate.queryForList(searchQuery, CollectInfo.class);
	}
	
	public Long getTotalCount() {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(matchAllQuery())
				.withIndices("collects")
				.withTypes("collect")
				.build();
		return esTemplate.count(searchQuery,CollectInfo.class);
	}
}
