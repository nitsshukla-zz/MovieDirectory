package com.movie.downloader.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class MovieReader {
	private String fileName;
	private BufferedReader reader=null; Reader fileReader=null;
	public MovieReader(String fileName){
		this.fileName=fileName;
	}
	public BufferedReader getReader() throws FileNotFoundException{
		FileReader fileReader = new FileReader(fileName);
		reader = new BufferedReader(fileReader);
		return reader;
	}
	public void closeReader() throws IOException{
		if(fileReader==null || reader == null)return;
		fileReader.close();
		reader.close();
	}
	
}
