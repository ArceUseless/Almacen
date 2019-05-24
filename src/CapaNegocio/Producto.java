/**
 * Producto.java
 * Definicion de la clase Producto
 * @author Alvaro Garcia Fuentes
 * @author Rafael Jesus Nieto Cardador
 */
package CapaNegocio;

public class Producto{
  
  //Atributos de instancia
  private int codigo;
  private String descripcion;
  private double precioCompra;
  private double precioVenta;
  private int stock;
  
  private Iva tipoDeIva;

  //Atributo de clase
  private static int cuentaProductos = 0;

  /**
   * Constructor parametrico
   * @param d
   * @param pC
   * @param pV
   * @param s
   * @param iva
   * @throws IvaInvalidoException 
   * @throws PrecioDeCompraNegativoException 
   * @throws PrecioDeVentaNegativoException 
   * @throws StockNegativoException 
   */
  public Producto( String d , double pC , double pV , int s, Iva iva ) throws IvaInvalidoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException, StockNegativoException{
    setCodigo();
    setDescripcion(d);
    setPrecioCompra(pC);
    setPrecioVenta(pV);
    setStock(s);
    setTipoDeIva(iva);
    }

  public Producto(int c) {
    this.codigo = c;
  }

  // Getters
  /**
   * getter para codigo
   * @return int
   * @see setCodigo
   */
  public int getCodigo(){
    return this.codigo;
    }
  
  /**
   * getter para descripcion
   * @return String
   * @see setDescripcion
   */
  public String getDescripcion(){
    return this.descripcion;
    }

  /**
   * getter para precioCompra
   * @return double
   * @see setPrecioCompra
   */
  public double getPrecioCompra(){
    return this.precioCompra;
    }
  
  /**
   * getter para precioVenta
   * @return double
   * @see setPrecioVenta
   */
  public double getPrecioVenta(){
    return this.precioVenta;
    }
  
  /**
   * getter para stock
   * @return int
   * @see setStock
   */
  public int getStock(){
    return this.stock;
    }
  
  /**
   * getter para el tipo de IVA
   * @return String (contiene el tipo de IVA)
   */
  public Iva getTipoDeIva() {
    return this.tipoDeIva;
  }
  
  //Setters
  /**
   * setter para codigo
   * @see getCodigo
   */
  private void setCodigo(){
    this.codigo = cuentaProductos++;
    }

  /**
   * setter para descripcion
   * @param d
   * @see getDescripcion
   */
  private void setDescripcion( String d ){
    this.descripcion = d;
    }
  
  /**
   * setter para PrecioCompra
   * @param pc
   * @see getPrecioCompra
   */
  private void setPrecioCompra( double pc )throws PrecioDeCompraNegativoException{
    if( pc < 0 ){
      throw new PrecioDeCompraNegativoException("ERROR: El precio de compra no puede ser negativo");
    }else
      this.precioCompra = pc;
    }
  
  /**
   * setter para precioVenta
   * @param pv
   * @throws PrecioDeVentaNegativoException 
   * @see getPrecioVenta
   */
  private void setPrecioVenta( double pv ) throws PrecioDeVentaNegativoException{
    if( pv < 0 ){
      throw new PrecioDeVentaNegativoException("ERROR: El precio de venta no puede ser negativo.");
    }else
      this.precioVenta = pv;
    }
  
  /**
   * setter para stock
   * @param s
   * @throws StockNegativoException 
   * @see getStock
   */
  private void setStock( int s ) throws StockNegativoException{
    if( s < 0 ){
      throw new StockNegativoException("ERROR: El stock no puede ser negativo");
    }else
      this.stock = s; 
    }
  
  /**
   * setter para el tipo de IVA
   * @param iva
   */
  private void setTipoDeIva(Iva iva) throws IvaInvalidoException {
    if(iva == null) {
      throw new IvaInvalidoException("ERROR: El tipo de iva no es válido.");
    }else {
      this.tipoDeIva = iva;
    }
    
  }
  
  //Metodos  
  
  /**
   * Incrementa el stock del producto
   * @param c Cantidad que se suma al stock
   * @throws StockNegativoException 
   * @throws ArgumentoNegativoException 
   * @see reducirExistencias
   */
  public void incrementarExistencias( int c ) throws ArgumentoNegativoException, StockNegativoException{
    if( c < 0){
      throw new ArgumentoNegativoException("ERROR: El argumento debe ser positivo.");
    }else {
      this.setStock( this.getStock() + c );
    }
  }
  /**
   * Decrementa el stock del producto
   * @param c Cantidad que se resta al stock
   * @throws StockNegativoException 
   * @throws ArgumentoNegativoException 
   * @see incrementarExistencias
   */
  public void reducirExistencias( int c ) throws StockNegativoException, ArgumentoNegativoException{ 
    if( c < 0){
      throw new ArgumentoNegativoException("ERROR: El argumento debe ser positivo.");
    }else if ( this.getStock() - c < 0) {
      throw new StockNegativoException("ERROR: No hay suficiente stock.");
    }
      this.setStock( this.getStock() - c );
    }
  
  public void modificarArticulo(String d, double pC, double pV, int s, Iva iva) throws IvaInvalidoException, StockNegativoException, PrecioDeCompraNegativoException, PrecioDeVentaNegativoException {
    setDescripcion(d);
    setPrecioCompra(pC);
    setPrecioVenta(pV);
    setStock(s);
    setTipoDeIva (iva);
  }
  /**
   * Metodo toString de la clase
   * @return String
   */
  @Override
  public String toString(){
    return "\nCodigo - " + this.codigo
        + "\nDescripcion - " + this.descripcion
        + "\nPrecio de compra - " + this.precioCompra
        + "\nPrecio de venta - " + this.precioVenta
        + "\nStock - " + this.stock
        + "\nTipo de IVA - "+ this.tipoDeIva + "\n";
    }
  
} // Fin de la clase Producto