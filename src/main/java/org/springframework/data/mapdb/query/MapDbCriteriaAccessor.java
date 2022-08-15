package org.springframework.data.mapdb.query;

import java.util.function.Predicate;

import org.springframework.data.keyvalue.core.CriteriaAccessor;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;

public class MapDbCriteriaAccessor implements CriteriaAccessor<Predicate<?>> {

	@Override
	public Predicate<?> resolve(KeyValueQuery<?> query) {
		
		if (query == null || query.getCriteria() == null) {
			return null;
		}

		if (query.getCriteria() instanceof Predicate) {
			return (Predicate<?>) query.getCriteria();
		}

		throw new IllegalArgumentException("Cannot create criteria for " + query.getCriteria());
	}

}
