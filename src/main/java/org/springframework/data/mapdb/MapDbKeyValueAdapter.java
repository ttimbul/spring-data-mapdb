package org.springframework.data.mapdb;

import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.springframework.data.keyvalue.core.AbstractKeyValueAdapter;
import org.springframework.data.keyvalue.core.ForwardingCloseableIterator;
import org.springframework.data.util.CloseableIterator;
import org.springframework.util.Assert;

import java.util.Map.Entry;

public class MapDbKeyValueAdapter extends AbstractKeyValueAdapter {

	private final DB mapDb;

	public MapDbKeyValueAdapter() {
		this(DBMaker.memoryDB().closeOnJvmShutdown().make());
	}

	public MapDbKeyValueAdapter(DB mapDb) {
		super(new MapDbQueryEngine());
		Assert.notNull(mapDb, "hzInstance must not be 'null'.");
		this.mapDb = mapDb;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object id, Object item, String keyspace) {
		return getMap(keyspace).put(id, item);
	}

	@Override
	public boolean contains(Object id, String keyspace) {
		return getMap(keyspace).containsKey(id);
	}

	@Override
	public Object get(Object id, String keyspace) {
		return getMap(keyspace).get(id);
	}

	@Override
	public Object delete(Object id, String keyspace) {
		return getMap(keyspace).remove(id);
	}

	@Override
	public Iterable<?> getAllOf(String keyspace) {
		return getMap(keyspace).getValues();
	}

	@SuppressWarnings("unchecked")
	@Override
	public CloseableIterator<Entry<Object, Object>> entries(String keyspace) {
		return new ForwardingCloseableIterator<Entry<Object, Object>>(getMap(keyspace).entryIterator());
	}

	@Override
	public void deleteAllOf(String keyspace) {
		getMap(keyspace).clear();
	}

	@Override
	public void clear() {
		mapDb.close();
	}

	@Override
	public long count(String keyspace) {
		return getMap(keyspace).size();
	}

	@SuppressWarnings("rawtypes")
	public BTreeMap getMap(String keyspace) {
		Assert.isInstanceOf(String.class, keyspace, "Keyspace identifier must be of type String.");
		return mapDb.treeMap(keyspace).counterEnable().createOrOpen();
	}
	
	@Override
	public void destroy() {
		mapDb.close();
	}

}
