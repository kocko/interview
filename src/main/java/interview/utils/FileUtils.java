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
	 * @return {@literal true}, if the output folder exists. {@literal false}, otherwise.
	 */
	public static boolean makeOutputDir() {
		File outputDir = new File(OUTPUT_FOLDER);
		if (!outputDir.exists()) {
			return outputDir.mkdir();
		}
		return true;
	}
	
	/**
	 * Returns a unique file name
	 * 
	 * @param prefix the name prefix
	 * @return A unique file name
	 */
	public static String createUniqueName(String prefix) {
		return prefix + "_" + System.currentTimeMillis();
	}
	
}
