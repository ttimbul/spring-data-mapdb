package org.springframework.data.mapdb.query;

import org.springframework.beans.PropertyAccessorFactory;

import java.io.Serializable;
import java.util.Comparator;

public class PropertyComparator implements Comparator<Object>, Serializable {

	private static final long serialVersionUID = 1L;

	private final String attributeName;
	private final int direction;

	public PropertyComparator(String attributeName, boolean ascending) {
		this.attributeName = attributeName;
		this.direction = (ascending ? 1 : -1);
	}

	/**
	 *
	 * @param o1
	 *            An entry in a map
	 * @param o2
	 *            Another entry in the map
	 * @return Comparison result
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int compare(Object o1, Object o2) {

		try {
			Object o1Field = PropertyAccessorFactory.forBeanPropertyAccess(o1).getPropertyValue(this.attributeName);
			Object o2Field = PropertyAccessorFactory.forBeanPropertyAccess(o2).getPropertyValue(this.attributeName);

			if (o1Field == null) {
				return this.direction;
			}
			if (o2Field == null) {
				return -1 * this.direction;
			}
			if (o1Field instanceof Comparable o1Comparable && o2Field instanceof Comparable o2Comparable) {
				return this.direction * o1Comparable.compareTo(o2Comparable);
			}

		} catch (Exception ignore) {
			return 0;
		}

		return 0;
	}
}
