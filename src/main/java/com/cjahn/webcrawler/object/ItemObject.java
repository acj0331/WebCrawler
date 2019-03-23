package com.cjahn.webcrawler.object;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * elasticsearch 이해
 * https://12bme.tistory.com/171
 * */
@Document(indexName="webcrawler", type="web_summary")
public class ItemObject {
	@Id
	private Number id;
    private String title;
    @JsonIgnore
    private String link;
    private String description;
    
    /*
     * findByLink 함수 오동작으로 인해 String 변수 사용 
     * */
    private String url;
    
    public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public final String getTitle() {
        return title;
    }
    public final void setTitle(String title) {
        this.title = title;
    }
    public final String getLink() {
        return link;
    }
    public final void setLink(String link) {
    	this.setUrl(link);
        this.link = link;
    }
    public final String getDescription() {
        return description;
    }
    public final void setDescription(String description) {
        this.description = description;
    }
	public String getUrl() {
		return this.getLink();
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
