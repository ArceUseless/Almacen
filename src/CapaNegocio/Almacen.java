/**
 * Almacen.java
 * Definicion de la clase envoltorio Almacen
 * @author Alvaro Garcia Fuentes
 * @author Rafael Jesus Nieto Cardador
 */
package CapaNegocio;
import java.util.ArrayList;

public class Almacen{

  //Atributos
  public ArrayList<Producto> almacen = new ArrayList<Producto>();
  
  //Metodos
  
  /**
   * Anade un producto al arraylist
   * @param p Objeto producto
   * @see retirarArticulo
   */
  public void anadirArticulo( String d, double pC, double pV, int s, Iva iva) throws IvaInvalidoException, StockNegativoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException {
    almacen.add(new Producto(d, pC, pV, s, iva));
    }
  
  /**
   * Retira un producto del arraylist
   * @param c Codigo del producto
   * @see anadirArticulo
   */
  public void retirarArticulo( int indice ){
    almacen.remove(indice);
  }
  
  /**
   * devuelve el codigo del elemento con indice dado,
   * si no se encuentra devuelve -1
   * @param c
   * @return int
   * @throws ProductoNoEncontradoException 
   * @see devolverArticulo
  */
  public int devolverIndice( int c ) throws ProductoNoEncontradoException{
    for( int i = 0 ; i < almacen.size() ; i++ )
      if( almacen.get(i).getCodigo() == c )
        return i;
    throw new ProductoNoEncontradoException("ERROR: Producto no encontrado.");
    }
  
  /**
   * método toString de la clase
   * @return String
   */
  
  @Override
  public String toString(){
      return "" + almacen;
}

} // Fin de la clase Almacen