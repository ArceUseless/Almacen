package utiles;

import java.util.Arrays;

public class Menu {
  
  private String titulo;
  private String[] opciones;
  private int numOpciones;
  
  public Menu(String titulo, String[] opciones) {
    setNumOpciones(opciones.length+1);
    setTitulo(titulo);
    setOpciones(opciones);
  }

  private String getTitulo() {
    return titulo;
  }

  private void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  private String[] getOpciones() {
    return opciones;
  }

  private void setOpciones(String[] opciones) {
    this.opciones = new String[numOpciones];

    for (int i = 0; i < numOpciones - 1; i++)
        this.opciones[i] = (i + 1) + "." + opciones[i];
  }
  
  private int getNumOpciones() {
    return numOpciones;
  }
  
  private void setNumOpciones(int numOpciones) {
    this.numOpciones = numOpciones;
  }
  

  private boolean opcionValida(int opcion) {
    return (opcion > 0 && opcion <= numOpciones);
}

  private int recogerOpcion() throws EnteroNoValidoException {
    int opcion;
    do {
      opcion = Teclado.leerEntero("Introduzca una opción: ");
    } while (!opcionValida(opcion));
      return opcion;
  }

  public int gestionMenu() throws EnteroNoValidoException {
      System.out.println(this);
      return recogerOpcion();
  }

  @Override
  public String toString() {
    String cadena = "";
    cadena += titulo;
    for (int i = 0; i < numOpciones-1; i++) {
        cadena += "\n"+opciones[i];
    }
    return cadena;
  }
  
  

}
