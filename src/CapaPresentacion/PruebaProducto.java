/**
 * pruebaProducto.java
 * Programa para gestionar un almacén
 * usando la clase Producto definida en el fichero Producto.java
 * @author Alvaro García Fuentes
 * @author Rafael Jesús Nieto Cardador
 */
package CapaPresentacion;

import CapaNegocio.*;
import Utiles.*;

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
  
  private static void altaProducto(Almacen almacen, Menu menuIva) {
    System.out.println("\n======DAR DE ALTA PRODUCTO======");
    
    String d = Teclado.leerCadena("Introduzca la descripción del producto.");

    double pC = Teclado.leerDecimal("Introduzca el precio de compra del producto.");

    double pV = Teclado.leerDecimal("Introduzca el precio de venta del producto.");

    int s;
    
    try {
      s = Teclado.leerEntero("Introduzca el stock del producto.");
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }

    Iva iva;
    try {
      iva = gestionaIva(menuIva.gestionMenu());
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      iva = null;
    }
    
    try {
      almacen.anadirArticulo(d, pC , pV, s, iva);
    } catch (IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException | PrecioDeVentaNegativoException err) {
      System.err.println(err.getMessage());
      return;
    } catch (Exception e) {
      System.out.println("ERROR: Entrada incorrecta.");
      return;
    } 
  }
  
  private static void bajaProducto(Almacen almacen) {
    System.out.println("\n======DAR DE BAJA PRODUCTO======");
    
    int indice = 0;
    try {
      indice = almacen.devolverIndice(Teclado.leerEntero("Introduzca el código del producto a eliminar"));
    } catch (ProductoNoEncontradoException pnee) {
      System.err.println(pnee.getMessage());
      return;
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }catch (Exception e) {
      System.out.println("ERROR: Entrada incorrecta.");
    }
    almacen.retirarArticulo(indice); 
  }
  
  private static void modificaProducto(Almacen almacen, Menu menuIva) {
    System.out.println("\n======MODIFICAR EL PRODUCTO======");
    int indice;
    try {
      indice = almacen.devolverIndice(Teclado.leerEntero("Introduzca el código del producto a modificar"));
    } catch (ProductoNoEncontradoException pnee) {
      System.err.println(pnee.getMessage());
      return;
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }catch (Exception e) {
      System.out.println("ERROR: Entrada incorrecta.");
      return;
    }

    try {
      System.out.println("Descripción: " + almacen.almacen.get(indice).getDescripcion());
      String d = Teclado.leerCadena("Nueva descripción: ");

      System.out.println("Precio de compra: " + almacen.almacen.get(indice).getPrecioCompra());
      double pC = Teclado.leerDecimal("Nuevo precio de compra: ");

      System.out.println("Precio de venta: " + almacen.almacen.get(indice).getPrecioVenta());
      double pV = Teclado.leerDecimal("Nuevo precio de venta: ");

      System.out.println("Stock: " + almacen.almacen.get(indice).getStock());
      int s = Teclado.leerEntero("Nuevo stock: ");

      System.out.println("Tipo de IVA: " + almacen.almacen.get(indice).getTipoDeIva());
      Iva iva = gestionaIva(menuIva.gestionMenu());
      
      almacen.almacen.get(indice).modificarArticulo(d, pC, pV, s, iva); 
    } catch (IvaInvalidoException | StockNegativoException | PrecioDeCompraNegativoException | PrecioDeVentaNegativoException err) {
      System.err.println(err.getMessage());
      return;
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }catch (Exception e) {
      System.out.println("ERROR: Entrada incorrecta.");
      return;
    } 
  }
  
  private static void entradaMercancia(Almacen almacen) {
    System.out.println("\n======ENTRADA DE MERCANC�A======");
    
    int indice;
    try {
      indice = almacen.devolverIndice(Teclado.leerEntero("Introduzca el código del producto"));
    }catch (ProductoNoEncontradoException pnee) {
      System.err.println(pnee.getMessage());
      return;
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }catch (Exception e) {
      System.out.println("ERROR: Entrada incorrecta.");
      return;
    }
    try {
      almacen.almacen.get(indice)
          .incrementarExistencias(Teclado.leerEntero("Introduzca la cantidad que desea sumar al stock"));
    }catch (ArgumentoNegativoException ane) {
      System.err.println(ane.getMessage());
      return;
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }catch (Exception e) {
      System.out.println("ERROR: entrada incorrecta.");
      return;
    }
  }
  
  private static void salidaMercancia(Almacen almacen) {
    System.out.println("\n======SALIDA DE MERCANC�A======");
    
    int indice;
    try {
      indice = almacen.devolverIndice(Teclado.leerEntero("Introduzca el código del producto"));
    }catch (ProductoNoEncontradoException pnee) {
      System.err.println(pnee.getMessage());
      return;
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
    }catch (Exception e) {
      System.out.println("ERROR: Entrada incorrecta.");
      return;
    }
    
    try {
      almacen.almacen.get(indice)
          .reducirExistencias(Teclado.leerEntero("Introduzca la cantidad que desea restar al stock"));
    }catch (ArgumentoNegativoException ane) {
      System.err.println(ane.getMessage());
      return;
    }catch(StockNegativoException sne) {
      System.err.println(sne.getMessage());
    } catch (EnteroNoValidoException enve) {
      System.err.println(enve.getMessage());
      return;
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