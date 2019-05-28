/**
 * pruebaProducto.java
 * Programa para gestionar un almacén
 * usando la clase Producto definida en el fichero Producto.java
 * @author Alvaro García Fuentes
 * @author Rafael Jesús Nieto Cardador
 */
package capaPresentacion;

import capaNegocio.*;
import utiles.*;

public class PruebaProducto {
  
  /**
   * Programa principal
   * @param args
   */
  public static void main( String[] args ){
   
    Almacen almacen = new Almacen();
    Menu menuIva = new Menu("Elige uno de los siguientes tipos de IVA:",
        new String[] { "GENERAL", "REDUCIDO", "SUPER REDUCIDO" });
    Menu menuGeneral = new Menu("Elige una de las opciones del almacén:", new String[] { "Mostrar Almacén", "Alta",
        "Baja", "Modificación", "Entrada de Mercancía", "Salida de Mercancía", "Salir" });

    int opcion = 0;
    // Menu
    do {
      try {
        opcion = menuGeneral.gestionMenu();
      } catch (EnteroNoValidoException enve) {
        System.err.println(enve.getMessage());
        opcion = -1;
      }

      // Estructura switch para manejar el menú
      switch (opcion) {
      // Mostrar almacén
      case 1:
        mostrarAlmacen(almacen);
        break;
      case 2:
        // Alta
        altaProducto(almacen, menuIva);
        break;
      case 3:
        // Baja
        bajaProducto(almacen);
        break;
      case 4:
        // Modificar
        modificaProducto(almacen, menuIva);
        break;
      case 5:
        // Entrada de mercancia
        entradaMercancia(almacen);
        break;
      case 6:
        // Salida de mercancia
        salidaMercancia(almacen);
        break;
      case 7:
        // Salir del menu
        System.out.println("Saliendo...");
        break;
      }
    } while (opcion != 7);
  }
  public static void mostrarAlmacen(Almacen almacen) {
    System.out.println(almacen); 
  }
  
  private static void altaProducto(Almacen almacen, Menu menuIva)  {
    try {
      System.out.println("\n======DAR DE ALTA PRODUCTO======");
      
      String d = Teclado.leerCadena("Introduzca la descripción del producto.");
  
      double pC = Teclado.leerDecimal("Introduzca el precio de compra del producto.");
  
      double pV = Teclado.leerDecimal("Introduzca el precio de venta del producto.");
      
      int s = Teclado.leerEntero("Introduzca el stock del producto.");
  
      Iva iva = gestionaIva(menuIva.gestionMenu());
      
      almacen.anadirArticulo(d, pC , pV, s, iva);
    } catch (NumberFormatException nfe) {
        System.err.println("ERROR: Formato numérico incorrecto.");
    } catch (EnteroNoValidoException enve) {
      System.err.println("ERROR: Entero no válido.");
    }catch (IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException | PrecioDeVentaNegativoException err) {
      System.err.println(err.getMessage());
      return;
    } catch (Exception e) {
      System.out.println("ERROR: Entrada incorrecta.");
      return;
    } 
  }
  
  private static void bajaProducto(Almacen almacen) {
    System.out.println("\n======DAR DE BAJA PRODUCTO======");
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto a eliminar");
      almacen.compruebaCodigo(codigo);
      almacen.retirarArticulo(codigo);
    } catch (ProductoNoEncontradoException pnee) {
      System.err.println(pnee.getMessage());
      return;
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }
  }
  
  private static void modificaProducto(Almacen almacen, Menu menuIva) {
    System.out.println("\n======MODIFICAR EL PRODUCTO======");
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto: ");
      almacen.compruebaCodigo(codigo);
      String d = Teclado.leerCadena("Nueva descripción: ");
      double pC = Teclado.leerDecimal("Nuevo precio de compra: ");
      double pV = Teclado.leerDecimal("Nuevo precio de venta: ");
      int s = Teclado.leerEntero("Nuevo stock: ");
      Iva iva = gestionaIva(menuIva.gestionMenu());
      
      almacen.modificaProducto(codigo,d, pC, pV, s, iva); 
    } catch (IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException | PrecioDeVentaNegativoException err) {
      System.err.println(err.getMessage());
      return;
    } catch (ProductoNoEncontradoException pnee) {
      System.err.println(pnee.getMessage());
      return;
    } catch (Exception e) {
      System.out.println("ERROR: Entrada incorrecta.");
      return;
    } 
  }
  
  private static void entradaMercancia(Almacen almacen) {
    System.out.println("\n======ENTRADA DE MERCANC�A======");
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto");
      almacen.compruebaCodigo(codigo);
      almacen.incrementarExistencias(codigo,Teclado.leerEntero("Introduzca la cantidad que desea sumar al stock"));
    } catch (ProductoNoEncontradoException pnee) {
      System.err.println(pnee.getMessage());
      return;
    } catch (ArgumentoNegativoException ane) {
      System.err.println(ane.getMessage());
      return;
    } catch (Exception e) {
      System.out.println("ERROR: entrada incorrecta.");
      return;
    }
  }
  
  private static void salidaMercancia(Almacen almacen) {
    System.out.println("\n======SALIDA DE MERCANC�A======");   
    try {
      int codigo = Teclado.leerEntero("Introduzca el código del producto");
      almacen.compruebaCodigo(codigo);
      almacen.reducirExistencias(codigo ,Teclado.leerEntero("Introduzca la cantidad que desea restar al stock"));
    } catch (ProductoNoEncontradoException pnee) {
      System.err.println(pnee.getMessage());
      return;
    } catch (ArgumentoNegativoException ane) {
      System.err.println(ane.getMessage());
      return;
    } catch(StockNegativoException sne) {
      System.err.println(sne.getMessage());
    } catch (Exception e) {
      System.out.println("ERROR: entrada incorrecta.");
      return;
    }
  }

  public static Iva gestionaIva(int opcion) {
    switch(opcion) {
      case 1:
        return Iva.GENERAL;
      case 2:
        return Iva.REDUCIDO;
      case 3:
        return Iva.SUPER_REDUCIDO;
      default:
        return null;
          
    }
  }
}