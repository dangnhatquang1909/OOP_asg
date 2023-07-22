package a2_2001040173;

import java.util.Vector;

import utils.DOpt;
import utils.DomainConstraint;
import utils.OptType;
import utils.collections.Collection;

/**
 * @overview <tt> Generic set are mutable, unbounded sets of elements of a given type. 
 *  Element type can be Object. </tt>
 * 
 * @attributes <tt>
 *   elements   Set<T>  Vector<T> </tt>
 * @object <tt> A typical Set<T> object is c={x1,...,xn}, where x1,...,xn are
 *   elements of type T. </tt>
 * @abstract_properties <tt>
 *  optional(elements) = false /\ 
 *  for all x in elements. x is T /\ 
 *  for all x, y in elements. x.equals(y) </tt>
 */
public class Set<T> implements Collection<T> {
	@DomainConstraint(type = "Vector", optional = false)
	private Vector<T> elements; // use generic syntax

	/**
	 * @effects <tt> initialise this to be empty </tt>
	 */
	public Set() {
		elements = new Vector<>();
	}

	/**
	 * @modifies this
	 * @effects <tt>
	 *   if x is already in this
	 *     do nothing
	 *   else
	 *     add x to this, i.e., this_post = this + {x} </tt>
	 */
	@DOpt(type = OptType.MutatorAdd)
	public void insert(T x) {
		if (getIndex(x) < 0)
			elements.add(x);
	}

	/**
	 * @modifies this
	 * @effects <tt>
	 *   if x is not in this
	 *     do nothing
	 *   else
	 *     remove x from this, i.e.
	 *     this_post = this - {x} </tt>
	 */
	@DOpt(type = OptType.MutatorRemove)
	public void remove(T x) {
		int i = getIndex(x);
		if (i < 0)
			return;
		elements.set(i, elements.lastElement());
		elements.remove(elements.size() - 1);
	}

	/**
	 * @effects <tt> 
	 *  if x is in this
	 *    return true
	 *  else
	 *    return false </tt>
	 */
	@DOpt(type = OptType.ObserverContains)
	public boolean isIn(T x) {
		return (getIndex(x) >= 0);
	}

	/**
	 * @effects <tt> return the cardinality of this </tt>
	 */
	@DOpt(type = OptType.ObserverSize)
	public int size() {
		return elements.size();
	}

	/**
	 * @effects <tt>
	 *  if this is not empty
	 *    return a new Vector<T> of elements of this
	 *  else
	 *    return null </tt>
	 */
	@DOpt(type = OptType.Observer)
	public Vector<T> getElements() {
		if (size() == 0)
			return null;
		else {
			Vector<T> els = new Vector<>();
			for (T e : elements)
				els.add(e);
			return els;
		}
	}

	/**
	 * @effects <tt>
	 *  if this is empty
	 *    throw an IllegalStateException
	 *  else
	 *    return an arbitrary element of this </tt>
	 */
	public T choose() throws IllegalStateException {
		if (size() == 0)
			throw new IllegalStateException("Set.choose: set is empty");
		return elements.lastElement();
	}

	/**
	 * @effects <tt>
	 *  if x is in this
	 *    return the index where x appears
	 *  else
	 *    return -1 </tt>
	 */
	private int getIndex(T x) {
		for (int i = 0; i < elements.size(); i++) {
			if (x.equals(elements.get(i)))
				return i;
		}

		return -1;
	}

	@Override
	public String toString() {
		if (size() == 0)
			return "Set:{ }";

		String s = "Set:{" + elements.elementAt(0).toString();
		for (int i = 1; i < size(); i++) {
			s = s + " , " + elements.elementAt(i).toString();
		}

		return s + "}";
	}

	/**
	 * @effects <tt>
	 *   if this satisfies abstract properties
	 *     return true
	 *   else
	 *     return false </tt>
	 */
	public boolean repOK() {
		if (elements == null)
			return false;

		for (int i = 0; i < elements.size(); i++) {
			T x = elements.get(i);

			for (int j = i + 1; j < elements.size(); j++) {
				if (elements.get(j).equals(x))
					return false;
			}
		}
		return true;
	}
}
