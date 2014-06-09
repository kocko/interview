package interview.utils;

import java.io.File;

/**
 * Utility methods for working with files.
 * 
 * @author Konstantin Yovkov
 * 
 */
public final class FileUtils {
	
	public static final String OUTPUT_FOLDER = "output";

	/**
	 * Checks if the output folder exist and if it doesn't - creates it.
	 * 
	 * @return true, if the output folder exists. false, otherwise.
	 */
	public static boolean makeOutputDir() {
		File outputDir = new File(OUTPUT_FOLDER);
		if (!outputDir.exists()) {
			return outputDir.mkdir();
		}
		return true;
	}
	
}
