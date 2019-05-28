package capaNegocio;
/**
 * Clase IvaInvalidoException que hereda de "Exception"
 * 
 * Excepci�n utilizada para controlar que el IVA no sea null.
 * 
 * @author Rafael Jes�s Nieto Cardador
 *
 */
public class IvaInvalidoException extends Exception {
  public IvaInvalidoException(String mensaje) {
    super(mensaje);
  }
  

}
