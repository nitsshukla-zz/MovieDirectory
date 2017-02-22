package com.movie.downloader.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import com.movie.downloader.beans.Format;
import com.movie.downloader.beans.Link;
import com.movie.downloader.model.MovieFetcher;

public class MovieLister {
	public static void main(String[] args) throws Exception {
		MovieLister lister = new MovieLister();
		lister.fetchAndSaveMovieLinks();
//		Scanner scan = new Scanner(new File("movies.json"));
//		StringBuffer buf = new StringBuffer();
//		while(scan.hasNextLine())buf.append(scan.nextLine().replaceAll("[^\\x20-\\x7e]", ""));
//		scan.close();
//		BufferedWriter out = new BufferedWriter(new FileWriter("movies1.json"));
//		out.write(buf.toString());
//		out.close();
	}
	public List<Link> getMovieLinks(String movieName){
		return null;
	}
	public boolean fetchAndSaveMovieLinks(){
		MovieFetcher fetcher = new MovieFetcher("sites_links.txt",
					new HashSet<Format>(Arrays.asList(Format.VIDEO,Format.FOLDER)),
					0);
		try {
			fetcher.getUrls();
			List<Link> links = fetcher.getLinks();
			fetcher.saveLinks(links);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
