package com.movie.downloader.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class MovieWriter {
	private String fileName;
	private BufferedWriter fileWriter=null; Writer writer=null;
	public MovieWriter(String fileName) throws IOException{
		this.fileName=fileName;
		getWriter();
	}
	public BufferedWriter getWriter() throws IOException{
		writer = new FileWriter(fileName);
		fileWriter = new BufferedWriter(writer);
		return fileWriter;
	}
	public void closeWriter() throws IOException{
		if(fileWriter==null || writer == null)return;
		fileWriter.close();
		writer.close();
	}
	public void write(String content) throws IOException {
		fileWriter.write(content);
	}
}
