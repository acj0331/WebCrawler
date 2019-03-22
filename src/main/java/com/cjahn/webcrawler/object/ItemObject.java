package com.cjahn.webcrawler.object;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
/*
 * elasticsearch 이해
 * https://12bme.tistory.com/171
 * */
@Document(indexName="webcrawler", type="web_summary")
public class ItemObject {
	@Id
	private Integer id;
    private String title;
    private String link;
    private String description;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
        this.link = link;
    }
    public final String getDescription() {
        return description;
    }
    public final void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("{\"description\":%s,\"link\":%s,\"title\":%s}", this.description, this.link, this.title);
    }
}
