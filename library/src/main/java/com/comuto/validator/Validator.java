package com.comuto.validator;

import android.support.annotation.NonNull;
import com.comuto.validator.constraint.Constraint;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Validator {
    private final Set<Constraint> constraints;

    public Validator() {
        this(new HashSet<Constraint>());
    }

    public Validator(Set<Constraint> constraints) {
        this.constraints = constraints;
    }

    /**
     * Adds the specified constraint to this validator. The validator is not modified if it
     * already contains the constraint.
     *
     * @param constraint the constraint to add.
     * @return {@code true} if this validator is modified, {@code false} otherwise.
     * @throws UnsupportedOperationException when adding to this validator is not supported.
     * @throws IllegalArgumentException when the constraint cannot be added to this validator.
     */
    public boolean add(Constraint constraint) {
        return constraints.add(constraint);
    }

    /**
     * Returns an array containing all elements contained in this validator. If the
     * specified array is large enough to hold the elements, the specified array
     * is used, otherwise an array of the same type is created. If the specified
     * array is used and is larger than this validator, the array element following
     * the collection elements is set to null.
     *
     * @param array the array.
     * @return an array of the elements from this validator.
     * @throws ArrayStoreException when the type of an element in this validator cannot be stored in
     * the type of the specified array.
     * @see Collection#toArray(Object[])
     */
    public Constraint[] toArray(Constraint[] array) {
        return constraints.toArray(array);
    }

    /**
     * Removes all constraints from this validator.
     *
     * @param constraints the collection of constraints to retain.
     * @return {@code true} if this validator was modified, {@code false} otherwise.
     * @throws UnsupportedOperationException when removing from this validator is not supported.
     */
    public boolean retainAll(Collection<Constraint> constraints) {
        return this.constraints.retainAll(constraints);
    }

    /**
     * Removes all elements from this validator, leaving it empty.
     *
     * @throws UnsupportedOperationException when removing from this validator is not supported.
     * @see #isEmpty
     * @see #size
     */
    public void clear() {
        constraints.clear();
    }

    /**
     * Searches this validator for all objects in the specified collection.
     *
     * @param constraints the collection of constraints.
     * @return {@code true} if all objects in the specified collection are
     * elements of this validator, {@code false} otherwise.
     */
    public boolean containsAll(Collection<Constraint> constraints) {
        return this.constraints.containsAll(constraints);
    }

    /**
     * Removes the specified object from this validator.
     *
     * @param constraint the constraint to remove.
     * @return {@code true} if this validator was modified, {@code false} otherwise.
     * @throws UnsupportedOperationException when removing from this validator is not supported.
     */
    public boolean remove(Constraint constraint) {
        return constraints.remove(constraint);
    }

    /**
     * Removes all objects in the specified collection from this validator.
     *
     * @param constraints the collection of constraints to remove.
     * @return {@code true} if this validator was modified, {@code false} otherwise.
     * @throws UnsupportedOperationException when removing from this validator is not supported.
     */
    public boolean removeAll(Collection<Constraint> constraints) {
        return this.constraints.removeAll(constraints);
    }

    /**
     * Returns an iterator on the elements of this validator. The elements are
     * unordered.
     *
     * @return an iterator on the elements of this validator.
     * @see Iterator
     */
    public Iterator<Constraint> iterator() {
        return constraints.iterator();
    }

    /**
     * Returns an array containing all elements contained in this validator.
     *
     * @return an array of the elements from this validator.
     */
    public Object[] toArray() {
        return constraints.toArray();
    }

    /**
     * Returns the number of elements in this validator.
     *
     * @return the number of elements in this validator.
     */
    public int size() {
        return constraints.size();
    }

    /**
     * Adds the objects in the specified collection which do not exist yet in
     * this validator.
     *
     * @param collection the collection of objects.
     * @return {@code true} if this validator is modified, {@code false} otherwise.
     * @throws UnsupportedOperationException when adding to this validator is not supported.
     * @throws ClassCastException when the class of an object is inappropriate for this validator.
     * @throws IllegalArgumentException when an object cannot be added to this validator.
     */
    public boolean addAll(Collection<? extends Constraint> collection) {
        return constraints.addAll(collection);
    }

    /**
     * Returns true if this validator has no elements.
     *
     * @return {@code true} if this validator has no elements, {@code false}
     * otherwise.
     * @see #size
     */
    public boolean isEmpty() {
        return constraints.isEmpty();
    }

    /**
     * Searches this validator for the specified object.
     *
     * @param constraint the constraint to search for.
     * @return {@code true} if object is an element of this validator, {@code false}
     * otherwise.
     */
    public boolean contains(Constraint constraint) {
        return constraints.contains(constraint);
    }

    /**
     * Validate all constraints contains in this validator.
     *
     * @return a non-null set of violation. If there is no violations, this set will be empty.
     * @throws UnsupportedException when an constraint object to validate is not supported.
     */
    @SuppressWarnings("unchecked")
    @NonNull
    public Set<Violation> validate() {
        final Set<Violation> outViolations = new HashSet<>();

        for (Constraint constraint : constraints) {
            outViolations.addAll(constraint.validate());
        }

        return outViolations;
    }
}
