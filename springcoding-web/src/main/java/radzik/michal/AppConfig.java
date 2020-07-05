package radzik.michal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

	@Autowired
	private Environment env;
	
	public String getImagesDir() {
		return env.getProperty("dir.images");
	}
	
	public String getImagesPath() {
		return env.getProperty("dir.imagePath");
	}
	
	public String getServerLocation() {
		return env.getProperty("dir.serverLocation");
	}

	public String getServerPath() {
		return env.getProperty("dir.serverPath");
	}
}
