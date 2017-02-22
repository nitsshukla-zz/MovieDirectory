package com.movie.downloader.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import com.movie.downloader.beans.Format;
import com.movie.downloader.beans.HtmlLink;
import com.movie.downloader.beans.Link;

public class Utils {
	/**
	 * 
	 * @param link provide the url that you want to fetch format of
	 * @return the format
	 */
	public static Format getFormat(HtmlLink link){
		if(link.getLink().contains("../"))
			return Format.OTHER;
		if(link.getLinkText().trim().endsWith("/"))
			return Format.FOLDER;
		for(String format: Constants.formats)
			if(link.getLinkText().endsWith(format))
				return Format.VIDEO;
		return Format.OTHER;
	}
	/**
     * The method will return the search page result in a {@link String} object
     * 
     * @param path
     *            the google search query
     * @return the content as {@link String} object
     * @throws Exception
     */
    public static String getSearchContent(URL url) throws Exception {
        final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        final URLConnection connection = url.openConnection();
        /**
         * User-Agent is mandatory otherwise Google will return HTTP response
         * code: 403
         */
        connection.setRequestProperty("User-Agent", agent);
        final InputStream stream = connection.getInputStream();
        return getString(stream);
    }

    /**
     * Method to convert the {@link InputStream} to {@link String}
     * 
     * @param is
     *            the {@link InputStream} object
     * @return the {@link String} object returned
     */
    private static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();
 
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /** finally block to close the {@link BufferedReader} */
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
	public static JSONArray getJson(List<Link> links, Set<Format> formats) throws JSONException {
		JSONArray array = new JSONArray();
		for(Link link:links)
			if(formats.contains(link.getFormat()))
				array.put(link.toJson());
		return array;
	}
}
