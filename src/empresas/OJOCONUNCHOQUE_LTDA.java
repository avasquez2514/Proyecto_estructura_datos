package empresas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class OJOCONUNCHOQUE_LTDA {
    public List<Clientes> clientes = new ArrayList<>();
    private Cola colaClientes = new Cola(100); // Utiliza una sola instancia de Cola

    public static void main(String[] args) {
        OJOCONUNCHOQUE_LTDA objEmp = new OJOCONUNCHOQUE_LTDA();
        
        objEmp.run();
    }

    public void run() {
        Pila objp = new Pila(100000);
        ManejoPila ObjManPil = new ManejoPila();
        Cola objc = colaClientes; // Utiliza la instancia de cola única
        ManejoCola ObjManCol = new ManejoCola();
        ListaDoble objLD = new ListaDoble();
        ManejoLista objManLD = new ManejoLista();
        Clientes objEmp=new Clientes();
        ArbolBI objArb=new ArbolBI();
        Archivo objArc=new Archivo();
        CRUDClientes objCRUDE=new CRUDClientes();

        
        int opppal, opmenEst, oppil, opcol, oplis, opProyecto, oparbol, oparch;
        String id,otroid, texto;//para la identidad de la clase en mi caso es la cedula del empleado
        Object info;
        

        do { // Ciclo del menú principal
            opppal = leerEntero(MenuPpal());
            switch (opppal) { // Caso del menú principal
                case 1:
                    do { // Ciclo del menú estructuras
                        opmenEst = leerEntero(MenuEstructuras());
                        switch (opmenEst) { // Caso del menú de estructuras
                            case 1:
                                do { // Ciclo del menú de pilas
                                    oppil = leerEntero(MenuPilas());
                                    switch (oppil) { // Caso del menú de pilas
                                        case 1:
                                            objp = ObjManPil.IngresarDatos(objp);
                                            break;
                                        case 2:
                                            if (!objp.isEmpty()) // Si hay datos se imprime, sino NO
                                                JOptionPane.showMessageDialog(null, "Pila original:\n" + ObjManPil.Imprimir(objp));
                                            else
                                                JOptionPane.showMessageDialog(null, "La pila está vacía");
                                            break;
                                        case 3:
                                            if (!objp.isEmpty()) // Si hay datos se imprime, sino NO
                                                JOptionPane.showMessageDialog(null, "El promedio de edad es: \n" + ObjManPil.PromediarEdad(objp));
                                            else
                                                JOptionPane.showMessageDialog(null, "La pila está vacía");
                                            break;
                                        case 4:
                                            if (!objp.isEmpty()) {
                                                // Si hay datos se imprime, sino NO
                                                String cc = leerString("Ingrese la cédula del empleado a consultar: ");
                                                Object obje = ObjManPil.BuscarEmpleado(objp, cc);
                                                if (obje == null)
                                                    JOptionPane.showMessageDialog(null, "Empleado NO está en la pila");
                                                else
                                                    JOptionPane.showMessageDialog(null, "Datos del Empleado:\n" + obje.toString());
                                            } else // No hay datos
                                                JOptionPane.showMessageDialog(null, "La pila está vacía");
                                            break;
                                        case 5:
                                            if (!objp.isEmpty()) {
                                                // Eliminar datos repetidos de la pila con respecto a la cola
                                                objp = ObjManPil.eliminarRepetidos(objp, objc);
                                                // Mostrar las estructuras originales y las modificadas usando JOptionPane
                                                JOptionPane.showMessageDialog(null, "Pila original:\n" + ObjManPil.Imprimir(objp) +
                                                        "\n\nCola original:\n" + ObjManCol.Imprimir(objc) +
                                                        "\n\nPila modificada:\n" + ObjManPil.Imprimir(objp));
                                            } else {
                                                JOptionPane.showMessageDialog(null, "La pila está vacía");
                                            }
                                            break;
                                    } // Fin caso del menú de pilas
                                } while (oppil < 20); // Fin del ciclo del menú de pilas
                                break;
                            case 2:
                                do { // Ciclo del menú de colas
                                    opcol = leerEntero(MenuColas());
                                    switch (opcol) { // Caso del menú de colas
                                        case 1:
                                            objc = ObjManCol.IngresarDatos(objc);
                                            break;
                                        case 2:
                                            if (!objc.isEmpty()) // Si hay datos se imprime, sino NO
                                                JOptionPane.showMessageDialog(null, "La cola quedó: \n" + ObjManCol.Imprimir(objc));
                                            else
                                                JOptionPane.showMessageDialog(null, "La cola está vacía");
                                            break;
                                    } // Fin caso del menú de colas
                                } while (opcol < 20); // Fin del ciclo del menú de colas
                                break;
                            case 3:
                                do { // Ciclo del menú de listas dobles
                                    oplis = leerEntero(MenuListas());
                                    switch (oplis) { // Caso del menú de listas dobles
                                        case 1:
                                            int opCreacion = 0;
                                            objLD = new ListaDoble(); // Para validar que sea excluyente
                                            do { // Ciclo mientras
                                                try { // Manejar las excepciones
                                                    opCreacion = leerEntero("Ingresar los datos a la lista...\n1. Por Inicio\n2. Por Final\n"); 
                                                    if (opCreacion != 1 && opCreacion != 2) // Para mensaje
                                                        JOptionPane.showMessageDialog(null, "La opción debe ser 1 o 2!!");
                                                } catch (Exception e) {
                                                    JOptionPane.showMessageDialog(null, "Error " + e);
                                                }
                                            } while (opCreacion != 1 && opCreacion != 2); // Sale cuando ingresa lo que requiero      
                                            objLD = objManLD.IngresarDatos(objLD, opCreacion);
                                            break;
                                        case 2:
                                            if (objLD.EstaVacia())
                                                JOptionPane.showMessageDialog(null, "Lista vacía");
                                            else
                                                JOptionPane.showMessageDialog(null, "La lista desde el inicio es:\n" + objLD.ImprimirDesdeCabeza());
                                            break;
                                        case 3:
                                            if (objLD.EstaVacia())
                                                JOptionPane.showMessageDialog(null, "Lista vacía");
                                            else
                                                JOptionPane.showMessageDialog(null, "La lista desde el final es:\n" + objLD.ImprimirDesdeFinal());
                                            break;                
                                        case 4:
                                            if (objLD.EstaVacia())
                                                JOptionPane.showMessageDialog(null, "Lista vacía");
                                            else {
                                                info = objLD.LiberarCabeza();
                                                JOptionPane.showMessageDialog(null, "El dato que se elimina es:\n " + info.toString());
                                            }
                                            break;
                                        case 5:
                                            if (objLD.EstaVacia())
                                                JOptionPane.showMessageDialog(null, "Lista vacía");
                                            else {
                                                info = objLD.LiberarUltimo();
                                                JOptionPane.showMessageDialog(null, "El dato que se elimina es:\n " + info.toString());
                                            }
                                            break;
                                        case 12:
                                            if (objLD.EstaVacia())
                                                JOptionPane.showMessageDialog(null, "Lista vacía");
                                            else {
                                                info = objManLD.BuscarMayorEdad(objLD);
                                                JOptionPane.showMessageDialog(null, "El empleado con más edad es:\n " + info.toString());
                                            }
                                            break; 
                                    } // Fin caso de listas
                                } while (oplis < 20);
                                break;

                                case 4:  //ciclo del menu de arbol binario
                                do{//ciclo del menu de arbol binario oparbol
                                     oparbol=Integer.parseInt(JOptionPane.showInputDialog(MenuArbolBinario()));
                                     //en caso de oplis
                                     switch(oparbol)
                                     {
                                        case 1:
                                                objArb = new ArbolBI(); // Se inicia para que se cree desde cero
                                                int resp = JOptionPane.showConfirmDialog(null, "Usar prueba de escritorio?", "Asignación interna o asignación externa", JOptionPane.YES_NO_OPTION);
                                                if (resp == JOptionPane.YES_OPTION) {
                                                    objArb.setRaiz(objArb.PruebaEscritorio(null)); // Se pasa null como raíz inicial
                                                    JOptionPane.showMessageDialog(null, "Se creó el arbol con 6 nodos");
                                                 }
                                                 else//quiere ingresar los datos
                                                 {
                                                 id=Validaciones.leerString("Ingrese Id del cliente a insertar en la raiz del árbol: ");
                                                 objEmp = new Clientes().IngresarDatos();
                                                 objArb.CrearRaiz(objEmp);//creamos la raiz del arbol primero que todo
                                                 objArb.Crear(objArb.getRaiz());//metodo recursivo para crear el arbol binario a partir de la raiz ya creada
                                                 }
                                                 break;
                                         case 2: if(objArb.Estavacio()==true)//esta vacio
                                                       JOptionPane.showMessageDialog(null,"Árbol esta vacio");
                                                 else//si hay datos
                                                      { 
                                                       objArb.Inicializar();//para borrar las cadenas y no tome al anterior y sobreescriba
                                                       JOptionPane.showMessageDialog(null,"El arbol en Inorden es: \n"+objArb.Inorden(objArb.getRaiz()));       
                                                      }
                                                       break;
                                                                
                                         case 3: if(objArb.Estavacio()==true)//esta vacio
                                                       JOptionPane.showMessageDialog(null,"Árbol esta vacio");
                                                 else//si hay datos
                                                      { 
                                                       objArb.Inicializar();//para borrar las cadenas y no tome al anterior y sobreescriba
                                                       JOptionPane.showMessageDialog(null,"El arbol en Preorden es: \n"+objArb.Preorden(objArb.getRaiz()));       
                                                      }
                                                       break; 
                                                 
                                         case 4: if(objArb.Estavacio()==true)//esta vacio
                                                       JOptionPane.showMessageDialog(null,"Árbol esta vacio");
                                                 else//si hay datos
                                                     { 
                                                       objArb.Inicializar();//para borrar las cadenas y no tome al anterior y sobreescriba
                                                       JOptionPane.showMessageDialog(null,"El arbol en Postorden es: \n"+objArb.Postorden(objArb.getRaiz()));       
                                                     }
                                                       break; 
                                                 
                                         case 5: if(objArb.Estavacio()==true)//esta vacio
                                                       JOptionPane.showMessageDialog(null,"Árbol esta vacio");
                                                 else//si hay datos
                                                     { 
                                                       objArb.Inicializar();//para borrar las cadenas y no tome al anterior y sobreescriba
                                                       JOptionPane.showMessageDialog(null,"El peso del arbol es: \n"+objArb.Peso(objArb.getRaiz()));       
                                                     }
                                                       break;                          
                                          
                                         case 6: if(objArb.Estavacio()==true)//esta vacio
                                                       JOptionPane.showMessageDialog(null,"Árbol esta vacio");
                                                 else//si hay datos
                                                     { 
                                                       objArb.Inicializar();//para borrar las cadenas y no tome al anterior y sobreescriba
                                                       JOptionPane.showMessageDialog(null,"Los nodos hermanos del arbol son: \n"+objArb.Hermanos(objArb.getRaiz()));       
                                                     }
                                                       break;  
                                         
                                         case 7: if(objArb.Estavacio()==true)//esta vacio
                                                       JOptionPane.showMessageDialog(null,"Árbol esta vacio");
                                                 else//si hay datos
                                                     { 
                                                       objArb.Inicializar();//para borrar las cadenas y no tome al anterior y sobreescriba
                                                       JOptionPane.showMessageDialog(null,"Los nodos izquierdos del arbol son:\n"+objArb.HijosIzquierdos(objArb.getRaiz()));       
                                                     }
                                                       break;          
                                                 
                                        }//fin caso oparbol
                                    }while(oparbol<20);         
                                    break;
                                    
                        } // Fin caso de menú estructuras
                    } while (opmenEst < 5); // Fin del ciclo del menú de estructuras
                    break;
                case 2: // Nuevo caso para manejar la opción "Manejo del proyecto"
                    do {
                        opProyecto = leerEntero(MenuProyecto());
                        switch (opProyecto) {
                            case 1:
                                // Mostrar todos los clientes
                                JOptionPane.showMessageDialog(null, ObjManCol.ImprimirClientes(objc), "Clientes en la Cola", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 2:
                                // Calcular costo total de alquiler
                                double costoTotalAlquiler = ObjManCol.CalcularCostoTotalAlquiler(objc, objp);
                                JOptionPane.showMessageDialog(null, "El costo total de alquiler de todos los vehículos es: " + costoTotalAlquiler);
                                break;
                            case 3:
                                String clienteMayorGasto = ObjManCol.ClienteConMayorGasto(objc, objp);
                                JOptionPane.showMessageDialog(null, "El cliente que ha gastado más dinero en total es: " + clienteMayorGasto);
                                break;

                            case 4:
                                // Copiar clientes mayores de 50 años a la cola
                                objManLD.copiarMayoresDe50Anios(objLD, objc);
                                JOptionPane.showMessageDialog(null, "Clientes mayores de 50 años copiados a la cola.");
                                break;
                            case 5:
                                 // Desencolar clientes con edad inferior a 50 y pasarlos a lista doble
                                ObjManCol.copiarClientesMenoresDe50(objc, objLD);
                                JOptionPane.showMessageDialog(null, "Clientes con edad inferior a 50 desencolados y pasados a lista doble.");
                                break;
                            case 6:
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
        }
                    } while (opProyecto != 6);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
            

                case 3:
                    do {
                        oparch = Validaciones.leerEntero(this.MenuCRUDClientes());
                        switch(oparch) {
                            case 1:
                                objCRUDE.Addicionar(objArc);
                                break;
                            case 2:
                                objCRUDE.Retirar(objArc);
                                break;
                            case 3:
                                objCRUDE.consultar(objArc);
                                break;
                            case 4:
                                objCRUDE.Actualizar(objArc);
                                break;
                            case 5:
                                texto = objArc.MostrarTodo();
                                if (!texto.equals(""))
                                    JOptionPane.showMessageDialog(null, "" + texto);
                                break;
                            case 6:
                                objArc.CopiarArchivoLista(objLD);
                                JOptionPane.showMessageDialog(null, "Se copió la información del archivo");
                                break;
                            case 7:
                                if (!objc.isEmpty()) {
                                    // Desencolar datos de la cola y pasarlos a la lista doble
                                    DesencolarDatosEnLista(objc, objLD);
                                    JOptionPane.showMessageDialog(null, "Datos desencolados y pasados a la lista doble.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "La cola está vacía. No hay datos para desencolar.");
                                }
                                break;



                     }//fin caso
                     objCRUDE.escribir(objArc);//graba definitivamente en caso de haber cambios
                    }while(oparch<8); //fin ciclo del CRUD de empleados
                    break;
                    case 4:           
                        JOptionPane.showMessageDialog(null, "SALIENDO DEL SISTEMA...");
                        System.exit(0); // Termina el programa
                        break;
                }//fin caso del menu ppal
            }while(opppal<4);//fin del ciclo del menu principal
        }//FIN MAIN


    public String MenuPpal() {
        return  "\n"
                +"MENU PRINCIPAL\n"
                + "1. MANEJO DE ESTRUCTURAS\n"
                + "2. PROYECTO (OJOCONUNCHOQUE)\n"
                + "3. MANEJO DEL ARCHIVO CLIENTES\n"
                + "4. SALIR\n ";
                
    }

    public String MenuProyecto() {
        return "MENU PROYECTO (OJOCONUNCHOQUE)\n"
                + "1. Mostrar todos los clientes\n"
                + "2. Calcular costo total de alquiler\n"
                + "3. Mostrar cliente con mayor gasto\n"
                + "4. Copiar clientes de la lista doble con edad mayor a 50 a la cola\n"
                + "5. Desencolar clientes con edad menor a 50 y pasar a lista doble\n"
                + "6. Volver al menú principal ";
    }

       
    public String MenuCRUDClientes()
    {
       return "MENU DE ARCHIVO\n"
                 + "1. Agregar Registro\n"
                 + "2. Eliminar Registro\n"
                 + "3. Consultar registro\n"
                 + "4. Actualizar registro\n"
                 + "5. Mostrar todo el contenido del archivo\n"
                 + "6. Copiar el archivo a la lista\n"
                 + "7. Desencolar datos de la lista \n"
                 + "8. Volver al menú principal\n"; 
    }
    

    public String MenuEstructuras() {
        return "MENU ESTRUCTURAS\n"
                + "1. Manejo de Pilas\n"
                + "2. Manejo de Cola\n"
                + "3. Manejo de Listas\n"
                + "4. Manejo de Árbol binario\n"
                + "5. Volver al menú principal ";
    }



    public String MenuPilas() {
        return "MENU PILAS\n"
                + "1. Ingresar datos en la pila\n"
                + "2. Imprimir la pila\n"
                + "3. Promediar edad de los empleados en la pila\n"
                + "4. Buscar un empleado específico en la pila\n"
                + "5. Eliminar de la pila los datos que se repiten en la cola\n"
                + "20. Volver al menú anterior ";
    }

    public String MenuColas() {
        return "MENU COLAS\n"
                + "1. Ingresar datos en la cola\n"
                + "2. Imprimir la cola\n"
                + "20. Volver al menú anterior ";
    }

    public String MenuListas() {
        return "MENU LISTAS\n"
                + "1. Ingresar datos en la lista\n"
                + "2. Imprimir desde Inicio\n"
                + "3. Imprimir desde Final\n"
                + "4. Liberar el Primer nodo\n"
                + "5. Liberar el Ultimo nodo\n"
                + "6. Liberar un dato especifico\n"
                + "7. Insertar antes de un dato especifico\n"
                + "8. Insertar despues de un dato especifico\n"
                + "9. Insertar un dato como cabeza\n"
                + "10. Insertar un dato al final\n"
                + "11. Organizar la lista ascendente cedula\n"
                + "12. Mostrar el empleado mas longevo\n"
                + "20. Volver al menu anterior ";
    }

    public String MenuArbolBinario()
    {
        return "MENU ARBOL EMPLEADOS\n"
                + "1. Crear arbol\n"
                + "2. Mostrar InOrden\n"
                + "3. Mostrar PreOrden\n"
                + "4. Mostrar PostOrden\n"
                + "5. Peso del arbol\n"
                + "6. Nodos Hermanos\n"
                + "7. Nodos izquierdos\n"
                + "8. \n"
                + "9. \n"
                + "10. \n"
                + "11. \n"
                + "12. \n"
                + "20. Volver a menú anterior\n";
    }


    public int leerEntero(String mensaje) {
        return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
    }

    public String leerString(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
        
        
        
        
    }

    private void DesencolarDatosEnLista(Cola objc, ListaDoble objLD) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

