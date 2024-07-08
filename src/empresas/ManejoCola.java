            package empresas;


            import java.util.List;

import javax.swing.JOptionPane;

            public class ManejoCola {
                /* Método para ingresar clientes en la cola mientras que el usuario quiera o la cola se llene */
            public Cola IngresarDatos(Cola objc) {
                int resp;
                resp = JOptionPane.showConfirmDialog(null, "Ingresar Cliente?", "Encolando datos", JOptionPane.YES_NO_OPTION);
                while (resp == JOptionPane.YES_OPTION) {
                    Clientes obje = new Clientes(); // Crear un nuevo objeto Clientes en cada iteración
                    obje.IngresarDatos(); // Leer los datos del cliente
                    obje.ingresarPlacasVehiculosAlquilados(); // Ingresar las placas de los vehículos alquilados
                    objc.Push(obje); // Encolar el cliente
                    resp = JOptionPane.showConfirmDialog(null, "Ingresar otro Cliente?", "Encolando datos", JOptionPane.YES_NO_OPTION);
                }
                return objc;
            }



                /* Método para imprimir los datos almacenados en la cola */
        public String Imprimir(Cola objc) {
            StringBuilder texto = new StringBuilder(); // Usar StringBuilder para construir el texto de salida de manera eficiente
            Cola objcaux = new Cola(objc.getMaxsize()); // Crear una cola auxiliar
            Object dato; // Variable para ayudar como auxiliar a desencolar
            while (!objc.isEmpty()) { // Mientras hayan datos en la cola
                dato = objc.Pop(); // Se desencola un dato
                texto.append(dato.toString()); // Se concatena el dato en la cadena
                if (dato instanceof Clientes) {
                    List<Vehiculo> vehiculos = ((Clientes) dato).getVehiculosAlquilados();
                    texto.append(" - Cantidad de vehículos alquilados: ").append(vehiculos.size()).append("\n");
                    texto.append(" - Placas de vehículos alquilados: ");
                    for (Vehiculo vehiculo : vehiculos) {
                        texto.append(vehiculo.getPlaca()).append(", ");
                    }
                    texto.delete(texto.length() - 2, texto.length()); // Eliminar la última coma y espacio
                    texto.append("\n");
                }
                objcaux.Push(dato); // Se encola en la cola auxiliar para que no se pierda el dato
            }
            PasarCola(objcaux, objc); // Pasar los datos de la cola auxiliar a la cola original
            return texto.toString(); // Retornar el texto construido como una cadena
        }

                    /* Método para pasar los datos de una cola a otra */
                    public void PasarCola(Cola c1, Cola c2) {
                        while (!c1.isEmpty()) {
                            c2.Push(c1.Pop());
                        }
                    }

                        public void PasarPila(Pila p1, Pila p2) {
                while (!p1.isEmpty()) {
                    p2.Push(p1.Pop());
                }
            }



    public String ImprimirClientes(Cola objc) {
        StringBuilder texto = new StringBuilder(); // Usar StringBuilder para construir el texto de salida de manera eficiente
        Cola objcaux = new Cola(objc.getMaxsize()); // Crear una cola auxiliar
        Object dato; // Variable para ayudar como auxiliar a desencolar
        while (!objc.isEmpty()) { // Mientras hayan datos en la cola
            dato = objc.Pop(); // Se desencola un dato
            if (dato instanceof Clientes) {
                Clientes cliente = (Clientes) dato;
                texto.append("Cc: ").append(cliente.getCc()).append("\n");
                texto.append("Nombre: ").append(cliente.getNom()).append("\n");
                texto.append("Apellido: ").append(cliente.getApell()).append("\n");
                texto.append("Edad: ").append(cliente.getEdad()).append("\n");
                texto.append("Teléfono: ").append(cliente.getSBM()).append("\n");
                texto.append("Vehículos alquilados:\n");
                List<Vehiculo> vehiculos = cliente.getVehiculosAlquilados();
                for (Vehiculo vehiculo : vehiculos) {
                    texto.append(" - Placa: ").append(vehiculo.getPlaca()).append("\n");
                }
                texto.append("\n");
            }
            objcaux.Push(dato); // Se encola en la cola auxiliar para que no se pierda el dato
        }
        PasarCola(objcaux, objc); // Pasar los datos de la cola auxiliar a la cola original
        return texto.toString(); // Retornar el texto construido como una cadena
    }







    public double CalcularCostoTotalAlquiler(Cola objc, Pila objp) {
        double costoTotal = 0;
        Cola objcaux = new Cola(objc.getMaxsize()); // Cola auxiliar para no perder los datos originales
        Object dato;

        while (!objc.isEmpty()) {
            dato = objc.Pop();
            if (dato instanceof Clientes) {
                Clientes cliente = (Clientes) dato;
                List<Vehiculo> vehiculos = cliente.getVehiculosAlquilados();
                for (Vehiculo vehiculo : vehiculos) {
                    // Buscar el precio de alquiler en la pila utilizando la placa del vehículo
                    double costoAlquiler = BuscarCostoAlquilerPorPlaca(objp, vehiculo.getPlaca());
                    costoTotal += costoAlquiler;
                    //System.out.println("el costo del cliente es " + costoAlquiler);
                    //System.out.println("soy " + cliente.getNom());



                    /*if(cliente.getNom() != null && cliente.getNom() != name){
                        name = cliente.getNom();
                            if(name != cliente.getNom()){
                                name = cliente.getNom();
                                    if(gastoCliente > mayorV){ 
                                        nameM = cliente.getNom();
                                        gastoCliente = 0;
                                    } else {
                                        gastoCliente = 0;
                                    }
                                } else {
                                    gastoCliente += vehiculo.getTarifa();
                                    System.out.println("el valor que paga el cliente es " + nameM);
                                }
                            
                    }*/

                    
                }
            }
            objcaux.Push(dato); // Se encola en la cola auxiliar para no perder el dato
        }

        PasarCola(objcaux, objc); // Pasar los datos de la cola auxiliar a la cola original
        return costoTotal;
    }

    // Método auxiliar para buscar el costo de alquiler por placa en la pila
    public double BuscarCostoAlquilerPorPlaca(Pila objp, String placa) {
        double costoAlquiler = 0;
        Pila objpaux = new Pila(objp.getMaxsize()); // Pila auxiliar para no perder los datos originales
        Object dato;

        while (!objp.isEmpty()) {
            dato = objp.Pop();
            if (dato instanceof Vehiculo) {
                Vehiculo vehiculo = (Vehiculo) dato;
                if (vehiculo.getPlaca().equals(placa)) {
                    costoAlquiler = vehiculo.getTarifa();
                } 
            }
            objpaux.Push(dato); // Se apila en la pila auxiliar para no perder el dato
        }

        PasarPila(objpaux, objp); // Pasar los datos de la pila auxiliar a la pila original
        return costoAlquiler;
    }

    
    public String ClienteConMayorGasto(Cola objc, Pila objp) {
    double mayorGasto = 0;
    String nombreClienteMayorGasto = "";

    Cola objcaux = new Cola(objc.getMaxsize()); // Cola auxiliar para no perder los datos originales
    Object dato;

    while (!objc.isEmpty()) {
        dato = objc.Pop();
        if (dato instanceof Clientes) {
            Clientes cliente = (Clientes) dato;
            List<Vehiculo> vehiculos = cliente.getVehiculosAlquilados();
            double gastoCliente = 0;
            for (Vehiculo vehiculo : vehiculos) {
                // Buscar el precio de alquiler en la pila utilizando la placa del vehículo
                double costoAlquiler = BuscarCostoAlquilerPorPlaca(objp, vehiculo.getPlaca());
                gastoCliente += costoAlquiler;
            }
            if (gastoCliente > mayorGasto) {
                mayorGasto = gastoCliente;
                nombreClienteMayorGasto = cliente.getNom();
            }
        }
        objcaux.Push(dato); // Se encola en la cola auxiliar para no perder el dato
    }

    PasarCola(objcaux, objc); // Pasar los datos de la cola auxiliar a la cola original

    return nombreClienteMayorGasto;
}
    
    public void copiarClientesMenoresDe50(Cola colaClientes, ListaDoble listaDoble) {
        Cola colaAuxiliar = new Cola(100); // Cola auxiliar para almacenar temporalmente los clientes que no cumplen la condición
        
        while (!colaClientes.isEmpty()) {
            Clientes cliente = (Clientes) colaClientes.Pop();
            if (cliente.getEdad() < 50) {
                listaDoble.CrearNodoPorFinal(cliente);
            } else {
                colaAuxiliar.Push(cliente);
            }
        }

        // Pasar los clientes de la cola auxiliar de vuelta a la cola original
        while (!colaAuxiliar.isEmpty()) {
            colaClientes.Push(colaAuxiliar.Pop());
        }
    }

    // Métodos auxiliares para imprimir y manejar las colas...
}




    

