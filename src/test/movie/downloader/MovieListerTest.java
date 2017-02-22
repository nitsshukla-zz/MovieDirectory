package test.movie.downloader;

import static org.junit.Assert.*;

import org.junit.Test;

import com.movie.downloader.main.MovieLister;

public class MovieListerTest {
	MovieLister lister = new MovieLister();
	@Test
	public void test() {
		assertTrue(lister.fetchAndSaveMovieLinks());
	}

}
