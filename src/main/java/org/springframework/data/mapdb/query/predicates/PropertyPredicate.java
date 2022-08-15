package org.springframework.data.mapdb.query.predicates;

import org.springframework.beans.PropertyAccessorFactory;

import java.util.function.Predicate;

public class PropertyPredicate<V> implements Predicate<V> {

	private final String property;

	private final Predicate<V> predicate;

	private PropertyPredicate(String property, Predicate<V> predicate) {
		this.property = property;
		this.predicate = predicate;
	}

	@Override
	public boolean test(V input) {
		return predicate.test((V) PropertyAccessorFactory.forBeanPropertyAccess(input).getPropertyValue(property));
	}

	public static <V> Predicate<V> wrap(Predicate<V> predicate, String property) {
		return new PropertyPredicate<>(property, predicate);
	}

}
