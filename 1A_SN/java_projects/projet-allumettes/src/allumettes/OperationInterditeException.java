package allumettes;
/**Classe modélisant l'exception qui se jette au cas de triche.
 *
 * @author Ayman Bahou
 *
 */
public class OperationInterditeException extends RuntimeException {

	public OperationInterditeException(String e) {
		super(e);
	}
}
