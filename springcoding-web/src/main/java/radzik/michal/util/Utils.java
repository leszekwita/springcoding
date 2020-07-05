package radzik.michal.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class Utils {

	public static void writeFile(MultipartFile file, String fileName, File dir) throws Exception {

		boolean isDirCreated = true;
		
		if (!dir.exists()) {
			isDirCreated = dir.mkdirs();
		}
		
		if(!isDirCreated) {
			return;
		}

		File tbnFile = new File(dir + File.separator + fileName);
		FileOutputStream output;

		try {
			output = new FileOutputStream(tbnFile);
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int len = 0;
			InputStream is = file.getInputStream();

			while ((len = is.read(buffer)) != -1) {
				output.write(buffer, 0, len);
			}
			output.close();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}
}
