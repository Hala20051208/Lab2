/**
 * 
 */
package aod.lab1;

/**
 * 
 */
public interface List <T>{
	/**
	 * 
	 * @param data
	 */
	 void add(T data);
	    void add(T data, int index);
	    T get(int index);
	    void set(T data, int index);
	    void remove();
	    void remove(int index);
	    int size();
	    void clear();
}
