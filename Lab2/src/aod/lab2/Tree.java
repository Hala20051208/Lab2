/**
 * 
 */
package aod.lab2;

/**
 * @author 24haal14
 * 
 * Interface för ett träd (BST).
 * Bestämmer vilka metoder som måste finnas
 */

public interface Tree<T> {

	  // Lägger till ett element i trädet
    public void add(T item);

    // Söker efter ett element (true om det finns)
    public boolean searchFor(T itemToSearchFor);

    // Returnerar antal element i trädet
    public int size();

    // Tömmer hela trädet
    public void clear();

    // Tar bort ett element (true om det fanns)
    public boolean remove(T itemToRemove);
}