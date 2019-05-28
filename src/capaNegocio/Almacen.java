/**
 * Almacen.java
 * Definicion de la clase envoltorio Almacen
 * @author Alvaro Garcia Fuentes
 * @author Rafael Jesus Nieto Cardador
 */
package capaNegocio;
import java.util.ArrayList;

public class Almacen{

  //Atributos
  private ArrayList<Producto> almacen = new ArrayList<Producto>();
  
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
  public void retirarArticulo( int codigo )throws ProductoNoEncontradoException{
    almacen.remove(new Producto(codigo));
  }
  /**
   * Incrementa el stock del producto
   * @param cantidad Cantidad que se suma al stock
   * @throws StockNegativoException 
   * @throws ArgumentoNegativoException 
   * @see reducirExistencias
   */
  
  public void incrementarExistencias( int cantidad, int codigo ) throws ArgumentoNegativoException, StockNegativoException, ProductoNoEncontradoException{
    if( cantidad < 0){
      throw new ArgumentoNegativoException("ERROR: El argumento debe ser positivo.");
    }else {
      Producto productoAux = this.almacen.get(this.almacen.indexOf(new Producto(codigo)));
      productoAux.incrementarStock(cantidad);
    }
  }
  /**
   * Decrementa el stock del producto
   * @param c Cantidad que se resta al stock
   * @throws StockNegativoException 
   * @throws ArgumentoNegativoException 
   * @see incrementarExistencias
   */
  public void reducirExistencias( int cantidad, int codigo ) throws StockNegativoException, ArgumentoNegativoException, ProductoNoEncontradoException{ 
    if( cantidad < 0){
      throw new ArgumentoNegativoException("ERROR: El argumento debe ser positivo.");
    }else {
      Producto productoAux = this.almacen.get(this.almacen.indexOf(new Producto(codigo)));
      if(productoAux.getStock()-cantidad <0) {
        throw new StockNegativoException("ERROR: El stock pasaría a ser negativo.");
      }
      productoAux.reducirStock(cantidad);
    }
  }
  
  public void modificaProducto(int codigo, String d, double pC, double pV, int s, Iva iva) throws IvaInvalidoException, StockNegativoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException, ProductoNoEncontradoException {
    this.almacen.get(this.almacen.indexOf(new Producto(codigo))).modificarProducto(d, pC, pV, s, iva);
  }
  
  public void compruebaCodigo(int codigo) throws ProductoNoEncontradoException {
    if(this.almacen.contains(new Producto(codigo))== false){
      throw new ProductoNoEncontradoException("ERROR: Producto no encontrado.");
    }
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