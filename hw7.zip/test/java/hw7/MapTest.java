package hw7;

import hw7.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SuppressWarnings("All")
public abstract class MapTest {

  protected Map<String, String> map;

  @BeforeEach
  public void setup() {
    map = createMap();
  }

  protected abstract Map<String, String> createMap();

  @Test
  public void newMapIsEmpty() {
    assertEquals(0, map.size());
  }

  @Test
  public void insertOneElement() {
    map.insert("key1", "value1");
    assertEquals(1, map.size());
    assertTrue(map.has("key1"));
    assertEquals("value1", map.get("key1"));
  }

  @Test
  public void insertMultipleElement() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    assertEquals(3, map.size());
    assertTrue(map.has("key1"));
    assertTrue(map.has("key2"));
    assertTrue(map.has("key3"));
    assertEquals("value1", map.get("key1"));
    assertEquals("value2", map.get("key2"));
    assertEquals("value3", map.get("key3"));
  }

  @Test
  public void insertDuplicatedKey() {
    try {
      map.insert("key1", "value1");
      map.insert("key1", "value2");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void insertNullKey() {
    try {
      map.insert(null, "value1");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void insertDuplicatedValue() {
    map.insert("key1", "value1");
    map.insert("key2", "value1");
    assertEquals(2, map.size());
  }

  @Test
  public void insertNullValue() {
    map.insert("null", null);
    assertEquals(1, map.size());
  }

  @Test
  public void removeOneElement() {
    map.insert("key1", "value1");
    assertEquals("value1", map.remove("key1"));
    assertEquals(0, map.size());
  }

  @Test
  public void removeMultipleElements() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    assertEquals("value1", map.remove("key1"));
    assertEquals("value3", map.remove("key3"));
    assertEquals(1, map.size());
    assertFalse(map.has("key1"));
    assertTrue(map.has("key2"));
    assertFalse(map.has("key3"));
    assertEquals("value2", map.get("key2"));
  }

  @Test
  public void removeNull() {
    try {
      map.remove(null);
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void removeNoSuchElement() {
    try {
      map.remove("key1");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void updateValue() {
    map.insert("key1", "value1");
    map.put("key1", "value2");
    assertEquals(1, map.size());
    assertEquals("value2", map.get("key1"));
  }

  @Test
  public void updateMultipleValues() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.put("key1", "updated1");
    map.put("key3", "updated3");
    assertEquals(3, map.size());
    assertEquals("updated1", map.get("key1"));
    assertEquals("value2", map.get("key2"));
    assertEquals("updated3", map.get("key3"));
  }

  @Test
  public void updateMultipleTimes() {
    map.insert("key1", "value1");
    map.put("key1", "value2");
    map.put("key1", "value3");
    map.put("key1", "value4");
    assertEquals(1, map.size());
    assertEquals("value4", map.get("key1"));
  }

  @Test
  public void updateNullValue() {
    map.insert("key1", "value1");
    map.put("key1", null);
    assertEquals(1, map.size());
    assertNull(map.get("key1"));
  }

  @Test
  public void updateNullKey() {
    try {
      map.put(null, "value");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void updateKeyNotMapped() {
    try {
      map.put("key", "value");
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void getKeyNull() {
    try {
      map.get(null);
      fail("Failed to throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void iteratorEmptyMap() {
    for (String key : map) {
      fail("Empty map!");
    }
  }

  @Test
  public void iteratorMultipleElements() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.insert("key4", "value4");
    map.insert("key5", "value5");
    map.insert("key6", "value6");
    int counter = 0;
    for (String key : map) {
      counter++;
      assertTrue(map.has(key));
    }
    assertEquals(6, counter);
  }

  @Test
  public void iteratorMultipleElements2() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.insert("key4", "value4");
    map.insert("key5", "value5");
    map.insert("key6", "value6");
    int counter = 0;
    for (String key : map) {
      counter++;
      assertTrue(map.has(key));
    }
    assertEquals(6, counter);
    assertEquals(6, map.size());
    for (String key : map) {
      map.remove(key);
      counter--;
      assertEquals(counter, map.size());
      assertFalse(map.has(key));
    }
    assertEquals(0, counter);
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.insert("key4", "value4");
    map.insert("key5", "value5");
    map.remove("key3");
    map.insert("key3", "value3");
    assertEquals(5, map.size());
  }

  @Test
  public void iteratorMultipleElements3() {
    map.insert("key1", "value1");
    map.insert("key2", "value2");
    map.insert("key3", "value3");
    map.insert("key4", "value4");
    map.insert("key5", "value5");
    map.insert("key6", "value6");
    map.insert("key7", "value1");
    map.insert("key8", "value2");
    map.insert("key9", "value3");
    map.insert("key10", "value4");
    map.insert("key11", "value5");
    map.insert("key12", "value6");
    map.insert("key13", "value1");
    map.insert("key23", "value2");
    map.insert("key33", "value3");
    map.insert("key43", "value4");
    map.insert("key53", "value5");
    map.insert("key63", "value6");
    map.insert("key73", "value1");
    map.insert("key83", "value2");
    map.insert("key93", "value3");
    map.insert("key103", "value4");
    map.insert("key113", "value5");
    map.insert("key123", "value6");
    map.insert("key15", "value1");
    map.insert("key25", "value2");
    map.insert("key35", "value3");
    map.insert("key45", "value4");
    map.insert("key55", "value5");
    map.insert("key65", "value6");
    map.insert("key75", "value1");
    map.insert("key85", "value2");
    map.insert("key95", "value3");
    map.insert("key105", "value4");
    map.insert("key115", "value5");
    map.insert("key125", "value6");
    map.insert("key135", "value1");
    map.insert("key235", "value2");
    map.insert("key335", "value3");
    map.insert("key435", "value4");
    map.insert("key535", "value5");
    map.insert("key635", "value6");
    map.insert("key735", "value1");
    map.insert("key835", "value2");
    map.insert("key935", "value3");
    map.insert("key1035", "value4");
    map.insert("key1135", "value5");
    map.insert("key1235", "value6");
    map.insert("key100", "value1");
    map.insert("key200", "value2");
    map.insert("key300", "value3");
    map.insert("key400", "value4");
    map.insert("key500", "value5");
    map.insert("key600", "value6");
    map.insert("key700", "value1");
    map.insert("key800", "value2");
    map.insert("key900", "value3");
    map.insert("key1000", "value4");
    map.insert("key1100", "value5");
    map.insert("key1200", "value6");
    map.insert("key1300", "value1");
    map.insert("key2300", "value2");
    map.insert("key3300", "value3");
    map.insert("key4300", "value4");
    map.insert("key5300", "value5");
    map.insert("key6300", "value6");
    map.insert("key7300", "value1");
    map.insert("key8300", "value2");
    map.insert("key9300", "value3");
    map.insert("key10300", "value4");
    map.insert("key11300", "value5");
    map.insert("key12300", "value6");
    map.insert("key1500", "value1");
    map.insert("key2500", "value2");
    map.insert("key3500", "value3");
    map.insert("key4500", "value4");
    map.insert("key5500", "value5");
    map.insert("key6500", "value6");
    map.insert("key7500", "value1");
    map.insert("key8500", "value2");
    map.insert("key9500", "value3");
    map.insert("key10500", "value4");
    map.insert("key11500", "value5");
    map.insert("key12500", "value6");
    map.insert("key13500", "value1");
    map.insert("key23500", "value2");
    map.insert("key33500", "value3");
    map.insert("key43500", "value4");
    map.insert("key53500", "value5");
    map.insert("key63500", "value6");
    map.insert("key73500", "value1");
    map.insert("key83500", "value2");
    map.insert("key93500", "value3");
    map.insert("key103500", "value4");
    map.insert("key113500", "value5");
    map.insert("key123500", "value6");
    map.insert("key199", "value1");
    map.insert("key299", "value2");
    map.insert("key399", "value3");
    map.insert("key499", "value4");
    map.insert("key599", "value5");
    map.insert("key699", "value6");
    map.insert("key799", "value1");
    map.insert("key899", "value2");
    map.insert("key999", "value3");
    map.insert("key1990", "value1");
    map.insert("key2990", "value2");
    map.insert("key3990", "value3");
    map.insert("key4990", "value4");
    map.insert("key5990", "value5");
    map.insert("key6990", "value6");
    map.insert("key7990", "value1");
    map.insert("key8990", "value2");
    map.insert("key9990", "value3");
    map.insert("key10990", "value1");
    map.insert("key20990", "value2");
    map.insert("key30990", "value3");
    map.insert("key40990", "value4");
    map.insert("key50990", "value5");
    map.insert("key60990", "value6");
    map.insert("key70990", "value1");
    map.insert("key80990", "value2");
    map.insert("key90990", "value3");
    int counter = 0;
    for (String key : map) {
      counter++;
      assertTrue(map.has(key));
    }
    assertEquals(123, counter);
  }

  // Ideally we should also check for "Keys must be immutable"
  // This is not trivial; check out
  // https://github.com/MutabilityDetector/MutabilityDetector
}