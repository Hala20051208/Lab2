package aod.lab2;

/**
 * Interface för ett träd.
 *
 * @param <T> typen som lagras i trädet
 */
public interface Tree<T> {

    /**
     * Lägger till ett element i trädet.
     *
     * @param itemToAdd elementet som ska läggas till
     */
    void add(T itemToAdd);

    /**
     * Söker efter ett element i trädet.
     *
     * @param itemToSearchFor elementet som ska sökas efter
     * @return true om elementet finns, annars false
     */
    boolean searchFor(T itemToSearchFor);

    /**
     * Returnerar antalet element i trädet.
     *
     * @return antal element
     */
    int size();

    /**
     * Tömmer trädet på alla element.
     */
    void clear();

    /**
     * Tar bort ett element ur trädet.
     *
     * @param itemToRemove elementet som ska tas bort
     * @return true om elementet fanns och togs bort, annars false
     */
    boolean remove(T itemToRemove);
}