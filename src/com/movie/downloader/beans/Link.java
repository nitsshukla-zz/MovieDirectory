package com.movie.downloader.beans;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.json.JSONException;
import org.json.JSONObject;

import com.movie.downloader.util.Utils;

public class Link {
	public Link(HtmlLink htmlLink, URL parentUrl) throws MalformedURLException {
		this.format=Utils.getFormat(htmlLink);
		this.url =  setUrl(htmlLink.link, parentUrl);
		this.linkText=htmlLink.getLinkText();
	}
	private URL setUrl(String link, URL parentUrl) throws MalformedURLException {
		URL url=null;
		try{
			url = new URL(link);
		}catch(Exception e){
			url = new URL(parentUrl+link);
		}
		return url;
	}
	String linkText;
	URL url;
	Format format;
	URL parentUrl;
	public String getLinkText() {
		return linkText;
	}
	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	public URL getParentUrl() {
		return parentUrl;
	}
	public void setParentUrl(URL parentUrl) {
		this.parentUrl = parentUrl;
	}
	public JSONObject toJson() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("link", url.toString());
		object.put("linkText", this.linkText);
		return object;
	}
	
}
