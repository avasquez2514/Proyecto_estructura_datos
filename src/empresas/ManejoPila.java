package empresas;

import java.util.HashSet;
import javax.swing.JOptionPane;

public class ManejoPila {
    /* Este método ingresa vehículos en la pila mientras que el usuario quiera o la pila se llene */
    public Pila IngresarDatos(Pila objp) {
        int resp;
        do {
            Vehiculo vehiculo = new Vehiculo(); // Crear un nuevo vehículo
            vehiculo.IngresarDatos(); // Llenar los datos del vehículo

            objp.Push(vehiculo); // Apilar el vehículo en la pila

            resp = JOptionPane.showConfirmDialog(null, "Ingresar otro Vehículo?", "Apilando datos", JOptionPane.YES_NO_OPTION);
        } while (resp == JOptionPane.YES_OPTION);

        return objp;
    }

    /* Método para imprimir los datos almacenados en la pila */
    public String Imprimir(Pila objp) {
        StringBuilder texto = new StringBuilder(); // Usar StringBuilder para construir el texto de salida de manera eficiente
        Pila pilaAuxiliar = new Pila(objp.getMaxsize()); // Crear una pila auxiliar para no perder los datos de la pila original
        Object dato; // Variable para ayudar como auxiliar a desapilar
        while (!objp.isEmpty()) { // Mientras hayan datos en la pila original
            dato = objp.Pop(); // Desapila un dato
            if (dato instanceof Vehiculo) { // Si el dato es una instancia de Vehiculo
                Vehiculo vehiculo = (Vehiculo) dato; // Castear el objeto a Vehiculo
                texto.append("Datos del vehículo:\n").append(vehiculo.toString()).append("\n"); // Agregar los datos del vehículo al texto
            }
            pilaAuxiliar.Push(dato); // Apilar el dato en la pila auxiliar para no perderlo
        }
        PasarPila(pilaAuxiliar, objp); // Restaurar los datos a la pila original
        return texto.toString(); // Retornar el texto construido como una cadena
    }

    /* Método para pasar los datos de una pila a otra */
    public void PasarPila(Pila p1, Pila p2) {
        while (!p1.isEmpty()) {
            p2.Push(p1.Pop());
        }
    }

    /* Método para contar los nodos en la pila */
    public int ContarNodos(Pila p1) {
        Pila p2 = new Pila(p1.getMaxsize());
        int con = 0; // Variable de retorno y cuenta los empleados almacenados en la pila
        while (!p1.isEmpty()) {
            p2.Push(p1.Pop()); // Se hace directo porque no necesitamos el dato
            con++;
        }
        PasarPila(p2, p1);
        return con;
    }

    /* Método para acumular la edad de todos los empleados en la pila */
    public int AcumularEdad(Pila p1) {
        Pila p2 = new Pila(p1.getMaxsize());
        int acumEd = 0; // Variable de retorno y acumula las edades de los empleados almacenados en la pila
        Clientes objE;
        while (!p1.isEmpty()) {
            objE = (Clientes) p1.Pop(); // Desapilamos un object y lo pasamos a empleado
            acumEd += objE.getEdad(); // Acumular la edad 
            p2.Push(objE); // Apilar en la pila auxiliar
        }
        PasarPila(p2, p1);
        return acumEd;
    }


    public double PromediarEdad(Pila p1) {
        int cont = ContarNodos(p1);
        int acue = AcumularEdad(p1);
        if (cont != 0) {
            return (double) acue / cont;
        } else {
            return 0;
        }
    }

    public Object BuscarEmpleado(Pila p1, String cedbus) {
        Pila p2 = new Pila(p1.getMaxsize());
        Clientes objE;
        Clientes objEmB = null;
        while (!p1.isEmpty()) {
            objE = (Clientes) p1.Pop(); // Desapilamos un object y lo pasamos a empleado
            if (cedbus.equals(objE.getCc())) {
                objEmB = objE; // Guardar los datos del empleado que estamos buscando
            }
            p2.Push(objE); // Apilar en la pila auxiliar
        }
        PasarPila(p2, p1);
        return objEmB;
    }

    /* Método para eliminar los elementos de la pila que están en la cola */
    public Pila eliminarRepetidos(Pila pila, Cola cola) {
        Pila pilaAuxiliar = new Pila(pila.getMaxsize());
        Pila resultado = new Pila(pila.getMaxsize());
        HashSet<Object> elementosCola = new HashSet<>();

        // Llenar el HashSet con los elementos de la cola para búsquedas eficientes
        while (!cola.isEmpty()) {
            elementosCola.add(cola.Pop());
        }

        // Iterar sobre cada elemento de la pila
        while (!pila.isEmpty()) {
            Object elemento = pila.Pop();
            // Verificar si el elemento NO está en la cola
            if (!elementosCola.contains(elemento)) {
                pilaAuxiliar.Push(elemento);
                resultado.Push(elemento); // Apilar en la pila de resultados
            } else {
                pilaAuxiliar.Push(elemento); // Mantener los elementos originales
            }
        }

        // Restaurar la pila original
        PasarPila(pilaAuxiliar, pila);

        // Devolver la pila con elementos únicos
        return resultado;
    }

    /* Método para imprimir clientes desde una pila */
    public String ImprimirClientes(Pila objp) {
        StringBuilder texto = new StringBuilder(); // Usar StringBuilder para construir el texto de salida de manera eficiente
        Pila pilaAuxiliar = new Pila(objp.getMaxsize()); // Crear una pila auxiliar para no perder los datos de la pila original
        Object dato; // Variable para ayudar como auxiliar a desapilar
        while (!objp.isEmpty()) { // Mientras hayan datos en la pila original
            dato = objp.Pop(); // Desapila un dato
            if (dato instanceof Clientes) { // Si el dato es una instancia de Clientes
                Clientes cliente = (Clientes) dato; // Castear el objeto a Clientes
                texto.append("Datos del cliente:\n").append(cliente.toString()).append("\n"); // Agregar los datos del cliente al texto
            }
            pilaAuxiliar.Push(dato); // Apilar el dato en la pila auxiliar para no perderlo
        }
        PasarPila(pilaAuxiliar, objp); // Restaurar los datos a la pila original
        return texto.toString(); // Retornar el texto construido como una cadena
    }
}













    
        