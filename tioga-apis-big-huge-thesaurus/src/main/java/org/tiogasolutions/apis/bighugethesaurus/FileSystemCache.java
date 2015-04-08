package org.tiogasolutions.apis.bighugethesaurus;

import java.io.*;
import org.tiogasolutions.dev.common.IoUtils;
import org.tiogasolutions.dev.common.exceptions.ExceptionUtils;

public class FileSystemCache implements Cache {

  private final File cacheDir;

  public FileSystemCache(File cacheDir) throws IOException {
    ExceptionUtils.assertNotNull(cacheDir, "cacheDir");
    ExceptionUtils.assertIsDirectory(cacheDir);

    this.cacheDir = cacheDir;
  }

  @Override
  public String get(String word) {
    try {
      File file = getJsonFile(word);
      return file.exists() ? IoUtils.toString(file) : null;
    } catch (Throwable e) {
      throw new CacheException("Unexpected exception reading from the cache.", e);
    }
  }

  @Override
  public String put(String word, String json) {
    try {
      File file = getJsonFile(word);
      IoUtils.write(file, json);
      return json;
    } catch (Throwable e) {
      throw new CacheException("Unexpected exception writing to the cache.", e);
    }
  }

  public File getJsonFile(String word) {
    String fileName = word+".json";
    return new File(cacheDir, fileName);
  }

  public void clear() throws IOException {
    try {
      for (File file : IoUtils.list(cacheDir)) {
        IoUtils.deleteFile(file);
      }
    } catch (Throwable e) {
      throw new CacheException("Unexpected exception clearing the cache.", e);
    }
  }

}
