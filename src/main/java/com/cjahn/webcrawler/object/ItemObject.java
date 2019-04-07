package com.cjahn.webcrawler.object;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import com.google.common.hash.Hashing;

/*
 * elasticsearch 이해
 * https://12bme.tistory.com/171
 * */
@Document(indexName="web_items", type="web_item")
@Setting(settingPath = "/settings/web_item_setting.json")
public class ItemObject {
	@Id
	private Number id;
	private Number collectId;
    @Field(type=FieldType.Text, analyzer="korean", fielddata=true)
    private String title;
    @Field(type=FieldType.Text)
    private String link;
    @Field(type=FieldType.Text, analyzer="korean", fielddata=true)
    private String description;
    @Field(type=FieldType.Text)
    private String keyWord;
    @Field(type=FieldType.Text)
    private String type;
    @Field(type=FieldType.Text, analyzer="korean", fielddata=true)
    private String htmlText;
    /*
     * elasticsearch에서 url format 검색이 안되므로 base64 encoding 하여 저장.
     * 추후 방법을 찾아봐야함 
     * */
    @Field(type=FieldType.Text)
    private String hash;
    
    public Number getId() {
		return id;
	}
	public void setId(Number id) {
		this.id = id;
	}
	public Number getCollectId() {
		return collectId;
	}
	public void setCollectId(Number collectId) {
		this.collectId = collectId;
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
    	this.setHash(link);
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
	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	public String getHtmlText() {
		return htmlText;
	}
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}
	
	public final String getHash() {
		return hash;
	}
	public final void setHash(String hash) {
		String sha256hex = Hashing.sha256().hashString(hash, StandardCharsets.UTF_8).toString();
		this.hash = sha256hex;
	}
	/*
	public String getBase64() {		
		return base64;
	}
	public void setBase64(String base64) throws UnsupportedEncodingException {
		this.base64 = new String(Base64Utils.encode(base64.getBytes()));
		this.base64 = URLEncoder.encode(this.base64, "utf-8");
	}
	*/
}
