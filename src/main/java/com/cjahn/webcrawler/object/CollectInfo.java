package com.cjahn.webcrawler.object;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName="collects", type="collect")

public class CollectInfo {
	@Id
	private Long id;
	private List<String> webPortalList;
    private List<String> keyWordList;
    private String crawlerStatus;
    private Long endDate;
    private Long collectCount;

    public CollectInfo() {
		this.id=System.currentTimeMillis();
		this.crawlerStatus = "crawling";
	}
    
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

	public String getCrawlerStatus() {
		return crawlerStatus;
	}

	public void setCrawlerStatus(String crawlerStatus) {
		this.crawlerStatus = crawlerStatus;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public Long getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(Long collectCount) {
		this.collectCount = collectCount;
	}
}
