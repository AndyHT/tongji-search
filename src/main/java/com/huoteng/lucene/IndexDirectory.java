package com.huoteng.lucene;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.File;

/**
 * Created by huoteng on 5/25/15.
 */
public class IndexDirectory {


    private static File indexFile;

    private static Directory directory;

    public static Directory getDirectory() {
        return directory;
    }

    public static void setDirectory(Directory directory) {
        IndexDirectory.directory = directory;
    }

    public static File getIndexFile() {
        return indexFile;
    }

    public static void setIndexFile(File indexFile) {
        IndexDirectory.indexFile = indexFile;
    }

}
