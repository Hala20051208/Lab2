/**
 * 
 */
package aod.lab2;

/**
 * @author 24haal14
 */

public interface Tree<T> {

    public void add(T item);

    public boolean searchFor(T itemToSearchFor);

    public int size();

    public void clear();

    public boolean remove(T itemToRemove);
}