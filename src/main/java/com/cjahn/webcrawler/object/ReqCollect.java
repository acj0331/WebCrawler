package com.cjahn.webcrawler.object;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName="req_collects", type="req_collect")
public class ReqCollect {
	@Id
	private Long id;
	private List<String> webPortalList;
    private List<String> keyWordList;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public final List<String> getKeyWordList() {
        return keyWordList;
    }

    public final void setKeyWordList(List<String> keyWordList) {
        this.keyWordList = keyWordList;
    }

	public List<String> getWebPortalList() {
		return webPortalList;
	}

	public void setWebPortalList(List<String> webPortalList) {
		this.webPortalList = webPortalList;
	}
}
