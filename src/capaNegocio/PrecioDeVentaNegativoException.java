package capaNegocio;

public class PrecioDeVentaNegativoException extends Exception {
  public PrecioDeVentaNegativoException(String mensaje) {
    super(mensaje);
  }
}
