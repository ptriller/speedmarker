package de.soapwars.speedmarker.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ptriller on 26.02.2017.
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

  private int cacheSize;

  public LRUCache(int cacheSize) {
    super(16, 0.75f, true);
    this.cacheSize = cacheSize;
  }

  protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    return size() >= cacheSize;
  }
}
