package org.tiogasolutions.apis.bighugethesaurus;

import java.io.*;
import java.util.zip.*;
import org.tiogasolutions.dev.common.*;
import org.tiogasolutions.dev.common.exceptions.ExceptionUtils;

public class ZipFileSystemCache implements Cache {

  private final File cacheDir;

  public ZipFileSystemCache(File cacheDir) throws IOException {
    ExceptionUtils.assertNotNull(cacheDir, "cacheDir");
    ExceptionUtils.assertIsDirectory(cacheDir);

    this.cacheDir = cacheDir;
  }

  @Override
  public String get(String word) {
    try {

      File file = getZipFile(word);
      if (file.exists() == false) {
        return null;
      }

      try (FileInputStream fis = new FileInputStream(file)) {
        ZipInputStream zis = new ZipInputStream(fis);
        byte[] data = IoUtils.toBytes(zis);
        return new String(data);
      }

    } catch (Throwable e) {
      throw new CacheException("Unexpected exception reading from the cache.", e);
    }
  }

  @Override
  public String put(String word, String json) {
    try {

      File file = getZipFile(word);

      try (FileOutputStream fos = new FileOutputStream(file)) {
        try (ZipOutputStream out = new ZipOutputStream(fos)) {
          ZipEntry entry = new ZipEntry(word + ".json");
          out.putNextEntry(entry);
          out.write(json.getBytes());
        }
      }

      return json;

    } catch (Throwable e) {
      throw new CacheException("Unexpected exception writing to the cache.", e);
    }
  }

  public File getZipFile(String word) {
    String fileName = word+".json.zip";
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
