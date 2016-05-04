package org.tiogasolutions.apis.bighugethesaurus;

import org.tiogasolutions.dev.common.EnvUtils;
import org.tiogasolutions.dev.common.IoUtils;
import org.tiogasolutions.dev.common.StringUtils;
import org.tiogasolutions.dev.common.json.JsonTranslator;
import org.tiogasolutions.dev.jackson.TiogaJacksonTranslator;

import java.io.File;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BigHugeThesaurusApisJobs {

    private File cacheDir;
    private File queueFile;
    private FileSystemCache cache;
    private final String apiKey = EnvUtils.requireProperty("tioga.test.bht.api.key");
    private BigHugeThesaurusApis thesaurusApis;

    public static void main(String... args) {
        try {
            BigHugeThesaurusApisJobs jobs = new BigHugeThesaurusApisJobs();
            jobs.download();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public BigHugeThesaurusApisJobs() throws Exception {
        cacheDir = new File("/dvlp/tioga/apis/tioga-apis-big-huge-thesaurus/data/cache");
        Files.createDirectories(cacheDir.toPath());

        cache = new FileSystemCache(cacheDir);

        queueFile = new File(cacheDir.getParentFile(), "queue.txt");

        JsonTranslator translator = new TiogaJacksonTranslator();
        thesaurusApis = new BigHugeThesaurusApis(translator, cache, apiKey);
    }

//  public void rebuildQueue() throws Exception {
//    Set<String> existing = new TreeSet<>();
//    Set<String> missing = new TreeSet<>();
//
//    for (File file : IoUtils.list(cacheDir)) {
//      if (file.getName().endsWith(".json.zip") == false) {
//        continue;
//      }
//      String word = StringUtils.substring(file.getName(), 0, -9);
//
//      try {
//        existing.add(word);
//        String json = thesaurusApis.getCache().get(word);
//        if (StringUtils.isNotBlank(json)) {
//          Result result = thesaurusApis.getTranslator().fromJson(Result.class, json);
//          missing.addAll(result.getAllWords());
//        }
//
//      } catch (Exception ex) {
//        String msg = String.format("Exception processing \"%s\" in %s.%n", word, file.getAbsolutePath());
//        throw new Exception(msg, ex);
//      }
//    }
//
//    missing.removeAll(existing);
//    String content = StringUtils.toDelineatedString("\n", missing);
//    IoUtils.write(queueFile, content);
//  }

    public void download() throws Exception {
        List<String> words = IoUtils.toList(queueFile);
        while (words.isEmpty() == false) {

            String next = words.remove(0).toLowerCase();
            Result result = thesaurusApis.lookup(next);
            Set<String> newWords = (result == null) ? Collections.emptySet() : result.getAllWords();

            for (String word : newWords) {
                word = word.toLowerCase();
                if (cache.getJsonFile(word).exists() == false) {
                    words.add(word); // The word does not yet exist
                }
            }

            String content = StringUtils.toDelineatedString("\r\n", new TreeSet<>(words));
            IoUtils.write(queueFile, content);
            words = IoUtils.toList(queueFile);
        }
    }
}