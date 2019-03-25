package com.cjahn.webcrawler.object;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.util.Base64Utils;

/*
 * elasticsearch 이해
 * https://12bme.tistory.com/171
 * */
@Document(indexName="web_items", type="web_item")
public class ItemObject {
	@Id
	private Number id;
	private Number reqCollectId;
    private String title;
    private String link;
    @Field(type=FieldType.Text, analyzer="")
    private String description;
    private String keyWord;
    private String type;
    
    /*
     * elasticsearch에서 url format 검색이 안되므로 base64 encoding 하여 저장.
     * 추후 방법을 찾아봐야함 
     * */
    private String base64;
    /*
    public ItemObject() {
		//this.id=System.currentTimeMillis();
	}
    */
    public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public Number getReqCollectId() {
		return reqCollectId;
	}
	public void setReqCollectId(Number reqCollectId) {
		this.reqCollectId = reqCollectId;
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
    public final void setLink(String link) throws UnsupportedEncodingException {
    	this.setBase64(link);
        this.link = link;
    }
    public final String getDescription() {
        return description;
    }
    public final void setDescription(String description) {
        this.description = description;
    }
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBase64() {		
		return base64;
	}
	public void setBase64(String base64) throws UnsupportedEncodingException {
		this.base64 = new String(Base64Utils.encode(base64.getBytes()));
		this.base64 = URLEncoder.encode(this.base64, "utf-8");
	}
}
