
    package empresas;

    import javax.swing.JOptionPane;


    public class ManejoLista 
    {
        /*metodo para ingresar datos en la lista doble, recibe la opcion
        para crear desde inicio o desde final, porque deben ser excluyentes*/
        public ListaDoble IngresarDatos(ListaDoble objLD,int op)
        {
            int resp;
            Clientes objE;
            resp=JOptionPane.showConfirmDialog(null,"Ingresar datos en la lista?","INGRESANDO...",JOptionPane.YES_NO_OPTION);
            while(resp==JOptionPane.YES_OPTION)//mientras resp="si"
            {
                objE=new Clientes();//para dar inicio
                objE=objE.IngresarDatos();
                objE.ingresarPlacasVehiculosAlquilados(); // Ingresar las placas de los vehículos alquilados
                if(op==1)//por inicio
                {
                    objLD.CrearNodoPorInicio(objE);
                }
                else//por final...son excluyentes!!!
                {
                    objLD.CrearNodoPorFinal(objE);
                }//fin si
                    
            resp=JOptionPane.showConfirmDialog(null,"Ingresar mas datos en la lista?"
                    ,"INGRESANDO...",JOptionPane.YES_NO_OPTION); 
            }//fin mientras
            return objLD;
        }//fin de ingresar datos


        public void copiarMayoresDe50Anios(ListaDoble listaDoble, Cola cola) {
            nodo actual = listaDoble.getCabeza();
            while (actual != null) {
                Clientes Cliente = (Clientes) actual.getDato();
                //System.out.println("parce "+ Cliente);
                if (Cliente.getEdad() > 50) {
                    //System.out.println("nea "+ Cliente.getEdad());
                    cola.Push(Cliente);
                }
                actual = actual.getSig();
            }      
        } 
        
        /*este metodo retorna el empleado con mas edad - mas longevo*/
        
        public Object BuscarMayorEdad(ListaDoble objl)
        {
            Object empmayor=null;//variable del retorno
            Clientes info;
            int mayorEdad;
            nodo q;//apuntador local para recorrer la lista
            if(objl.EstaVacia()==false)//hay datos en la lista
            {
                empmayor=objl.getCabeza().getDato();//tomamos el primer empleado
                mayorEdad=((Clientes)empmayor).getEdad();//tomamos la edad del primer empleado
                q=objl.getCabeza();//se coloca a q en cabeza
                
                while(q!=null)
                {
                    info=(Clientes)q.getDato();
                    //if(((Empleados)q.getDato()).getEdad()>mayorEdad)
                    if(info.getEdad()>mayorEdad)    
                    {
                        empmayor=info;
                        mayorEdad=info.getEdad();
                    }//fin si
                    q=q.getSig();//se adelanta en la lista
                }//fin mientras
            }//fin si
            return empmayor;
        }//fin  buscar mayor      
    
    public ListaDoble  CrearListaEstratoPar(Pila p1,Pila p2, Cola c1, Cola c2)
    {
        ListaDoble objl=new ListaDoble();//creamos la lista nueva es la del retorno
        objl= CopiarPilaEstratoPar(p1, p2,objl);//invocamos el metodo que copia de pila
        if(objl. EstaVacia()==true)//si no se copio nada
                JOptionPane.showMessageDialog(null,"la pila no tenia empleados con estrato par, no se copio nada");
        //Fin si
        objl=CopiarColaEstratoPar(c1,c2,objl);
        if(objl. EstaVacia()==true)
                JOptionPane.showMessageDialog(null,"la cola no tenia empleados con estrato par, no se copio nada");
        //Fin si
    return objl;
    }//Fin metodo

    public ListaDoble CopiarPilaEstratoPar( Pila p1,Pila p2,ListaDoble objl)
    {
    ManejoPila objman=new ManejoPila();//objeto para llamar al metodo pasar pila    
    while(p1.isEmpty()==false)//mientras hay datos en pila
    {
    if(((Clientes)p1.peek()).getEstS()%2==0)//se pregunta por el dato sin desapilarlo si es par
    {
        objl.CrearNodoPorFinal(p1.peek());//se copia a la lista
    }//Fin si
    p2.Push(p1.Pop());//se pasa todo a la pila auxiliar
    }//fin mientras
    objman.PasarPila(p2,p1);//se coloca todo como estaba
    return objl;//se retorna la nueva lista
    }//Fin metodo

    public ListaDoble CopiarColaEstratoPar(Cola c1,Cola c2,ListaDoble objl)
    {
    ManejoCola objman=new ManejoCola();//objeto para llamar al metodo pasar cola 
    while(c1.isEmpty()==false)
    {
    if(((Clientes)c1.peek()).getEstS()%2==0) 
    {
        objl.CrearNodoPorFinal(c1.peek());
    }//  Fin si
    c2.Push(c1.Pop());
    }//fin mientras
    objman.PasarCola(c2,c1);
    return objl;
    }//Fin metodo
    

    public void DesencolarDatosEnListaConValidacion(Cola cola, ListaDoble lista) {
        while (!cola.isEmpty()) {
            Clientes cliente = (Clientes) cola.Pop(); // Desencolar un elemento de la cola
            if (!clienteEnLista(cliente, lista)) { // Verificar si el cliente ya está en la lista
                lista.CrearNodoPorFinal(cliente); // Agregar el elemento desencolado al final de la lista doble
            }
        }
    }

    // Método auxiliar para verificar si un cliente está en la lista
    private boolean clienteEnLista(Clientes cliente, ListaDoble lista) {
        nodo actual = lista.getCabeza();
        while (actual != null) {
            Clientes clienteLista = (Clientes) actual.getDato();
            if (clienteLista.getCc().equals(cliente.getCc())) { // Comparar por ID del cliente
                return true; // El cliente ya está en la lista
            }
            actual = actual.getSig();
        }
        return false; // El cliente no está en la lista
    }


}


 