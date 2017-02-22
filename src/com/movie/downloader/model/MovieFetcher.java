package com.movie.downloader.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONArray;

import com.movie.downloader.beans.Format;
import com.movie.downloader.beans.HtmlLink;
import com.movie.downloader.beans.Link;
import com.movie.downloader.util.HTMLLinkExtractor;
import com.movie.downloader.util.Utils;

public class MovieFetcher {
	HTMLLinkExtractor extractor;
	private List<URL> pageUrls = null;
	private String fileName;
	private Set<Format> allowedFormats;
	private int recursion_depth;
	public MovieFetcher(String fileName, Set<Format> formats, int rec) {
		extractor = new HTMLLinkExtractor();
		this.fileName=fileName;
		this.allowedFormats = formats;
		this.recursion_depth = rec;
	}
	public List<URL> getUrls() throws Exception{
		MovieReader reader = new MovieReader(fileName);
		BufferedReader fileReader = reader.getReader();
		List<URL> urls = new ArrayList<URL>();
		while(fileReader.ready()){
			String urlT = fileReader.readLine();
			URL url = new URL(urlT);
			urls.add(url);
		}
		reader.closeReader();
		pageUrls = urls;
		return urls;
	}
	public List<Link> getLinks() throws Exception{
		List<Link> links = new ArrayList<Link>();
		for(URL url:pageUrls){
			links.addAll(getLinks(url,0));
		}
		return links;
	}
	public List<Link> getLinks(URL url, int rec) throws Exception{
		List<Link> links = new ArrayList<Link>();
		System.out.println(url);
		try{
			String content = Utils.getSearchContent(url);
			List<HtmlLink> htmlLinks = extractor.grabHTMLLinks(content);

			for(HtmlLink htmlLink : htmlLinks){
				Link link = new Link(htmlLink, url);
				if(link.getFormat()==Format.FOLDER && rec<recursion_depth)
					links.addAll(getLinks(link.getUrl(),rec+1));
				if(allowedFormats.contains(link.getFormat()))
					links.add(link);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return links;
	}

	private String getCache() throws FileNotFoundException {
		Scanner scan = new Scanner(new File("cache.html"));
		String s="";while(scan.hasNext())s+=scan.nextLine();
		scan.close();
		return s;
	}
	public void saveLinks(List<Link> links) throws Exception{
		MovieWriter movieWriter = new MovieWriter("movies1.json");
		JSONArray array = Utils.getJson(links, allowedFormats);
		movieWriter.write(array.toString());
		movieWriter.closeWriter();
	}
}
