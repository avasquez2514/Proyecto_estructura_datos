
    package empresas;

    import javax.swing.JOptionPane;
    public class ListaDoble
    {
        //atributos propios y privados
        private nodo Cabeza;

        //constructor vacio
        public ListaDoble() 
        {
            Cabeza=null;//condiciones iniciales de lista doble
        }

        //nodos auxiliares
        nodo q,p,Fin;
        
        /*metodo para validar si la lista esta vacia 
        o tiene datos, retorna verdadero si no hay datos*/
        public boolean EstaVacia()
        {
        if(getCabeza()==null)  
                //"LISTA VACIA!!!“   //mensaje opcional
                return true;
            else
                return false;
            //fin si 
        }//fin esta vacia

    /*este metodo ubica el apuntador auxiliar Fin en el ultimo nodo
        de la lista*/
    public nodo UbicarUltimo() 
    {
        Fin=getCabeza(); 
        while(Fin.getSig()!=null)
        {
        Fin=Fin.getSig();
        }//	fin de mientras
        return  Fin;
    }//fin de ubicarultimo

    /*este metodo crea un nodo de la lista en caso de que no exista y coloca
        nuevos datos por el apuntador Fin, quedando de primero el primero
        que ingresa, el apuntador Fin es un auxiliar y por eso NO se encapsula
    y siempre se debe estar ubicando*/
    public void CrearNodoPorFinal(Object info)
    {
        q=new nodo(null,info,null);
        if(EstaVacia()==true)
        {
        setCabeza(q);
            Fin=q; 
        }
        else 
        {
                Fin.setSig(q);
                q.setAnt(Fin);
                Fin=q;
        }//Fin si
        }//fin crearlista por final
    
    
        /*este metodo crea un nodo de la lista en caso de que no exista y coloca
        nuevos datos por el apuntador cabeza, quedando de ultimo el primero
        que ingresa*/

        public void CrearNodoPorInicio(Object info)
        {
        q=new nodo(null,info,null);//se crea el nodo y  se le mandan los 3 parametros o argumentos
        if(EstaVacia()==true)
        setCabeza(q);
        else
        {
            getCabeza().setAnt(q);
            q.setSig(getCabeza());
            setCabeza(q);
        }//Fin si
        }//fin crearlista por final
        
        /*metodo que retorna una cadena con los datos de la lista desde el primero
        NO imprime*/
        public String ImprimirDesdeCabeza()
        {
        String texto=" \n";
        if (EstaVacia()==false)//si la lista tiene datos
        {
        q=getCabeza(); 
        while (q!=null)  
        {
            texto =texto+"\n"+q.toString(); //q.obtenerDato()
            q=q.getSig(); 
        }//fin mientras
        }//Fin si
        return texto ;
        }//Fin imprimir

        /*metodo que retorna una cadena con los datos de la lista desde el ultimo
        NO imprime*/
    public String ImprimirDesdeFinal()
    {
        String texto=" \n";
        if (EstaVacia()==false)
        {
        UbicarUltimo(); //ubica al nodo fin
        while (Fin!=null)
        {
            texto =texto+"\n"+Fin.toString();//fin.obtenerDato()
            Fin=Fin.getAnt(); 
        }//fin mientras
        }//Fin si
    return texto ;
    }//Fin imprimir

    /*este metodo inserta un nodo de primero y SIEMPRE parte de 
        la base de tener informacion la lista, o sea NO crea la lista desde 
        cero*/
    public void InsertarComoCabeza(Object info)
    {
    if (EstaVacia()==false)//si hay datos
    {
        getCabeza().setAnt(new nodo(null,info,getCabeza()));
        setCabeza(getCabeza().getAnt());
    }

    //Fin si
    }//Fin de insertar como cabeza

    /*este metodo inserta un nodo de ultimo y SIEMPRE parte de 
        la base de tener informacion la lista, o sea NO crea la lista desde 
        cero*/
    public  void InsertarAlFinal(Object info)
    {
    if (EstaVacia()==false)
    {
        Fin=UbicarUltimo();
        Fin.setSig(new nodo(Fin,info,null));	

    }//Fin si
    }//Fin de insertar Final

    /*Libera el espacio de memoria y elimina el primer dato de la lista
    usa el destructor basico que es finalize*/
    public Object LiberarCabeza()
    {
    Object info=null;
    if (EstaVacia()==false)   ///si hay datos
    {
        q=getCabeza();  //toma la memoria donde esta el primer nodo
        info=q.getDato();
        if(getCabeza().getSig()==null)    //si es lista de un solo nodo
            setCabeza(null);   //lista quedo vacia
        else
            {
            setCabeza(getCabeza().getSig());
            getCabeza().setAnt(null);
            }//fin si
        q.finalize();
    }
    else
        JOptionPane.showMessageDialog(null,"Lista vacia No podemos liberar cabeza");
    //fin si
    return info;
    }//fin liberar cabeza

    /*Libera el espacio de memoria y elimina el ultimo dato de la lista
    usa el destructor basico que es finalize*/
    public Object LiberarUltimo()
    {
    Object info=null;
    if (EstaVacia()==false)   ///si hay datos
    {
        UbicarUltimo();
        info=Fin.getDato();
        if(getCabeza().getSig()==null)    //si es lista de un solo nodo
            setCabeza(null);   //lista quedo vacia
        else
            Fin.getAnt().setSig(null);
        //fin si
        Fin.finalize();
    }
    else
        JOptionPane.showMessageDialog(null,"Lista vacia No podemos liberar el ultimo");
    //fin si
    return info;
            }//fin liberar ultimo

        

        /*busca por id  y si lo encuentra retorna verdadero y deja ubicado en el dato 
        al nodo p ....y si no lo encuentra retorna falso y p no apunta a nada*/
    public boolean Buscar(String id)
    {
    p=getCabeza();
    Clientes objEmp;
    objEmp=(Clientes)p.getDato();//se toma el primer dato 
    String idp=objEmp.getCc();//se toma la identificacion del primer dato 
    while(p!=null&&!(id.equals(idp)))
    {    
            p=p.getSig();
            if(p!=null)
            {
                objEmp=(Clientes)p.getDato();//se toma el dato señalado por p en este momento
                idp=objEmp.getCc();//se toma la identificacion del dato señalado por p para compararlo
            }
    }//fin mientras
    if(p==null) 
        return false;
    else
        return true;
    //fin si          
    }//fin buscar
    
    /*Libera el espacio de memoria y elimina el dato del id que se busca, puede estar en 
    cualquier nodo de la lista y debe tomarse en cuenta
    usa el destructor basico que es finalize*/
    public void LiberarDato(String id)
    {
    if (EstaVacia()==false) 
    {
        if(Buscar(id)==false) 
                        JOptionPane.showMessageDialog(null,"Dato no se encuentra en la lista");
        else
            {
            if(p.getAnt()==null)   // Si(p=obtenerCabeza())  ///si es el primer dato
                LiberarCabeza();
            else
                    {
                if(p.getSig()==null)//si es el ultimo dato de la lista
                    LiberarUltimo();
                else//sino esta entre dos nodos
                            {
                    p.getAnt().setSig(p.getSig());
                    p.getSig().setAnt(p.getAnt());
                    p.finalize();  //-   liberar(p);
                            }//fin si
                    }//fin si
        }//fin si

    }//fin si
    }//fin liberar dato

    public void InsertarDespues( String idBuscar, Object datoIns)
    {
    if(Buscar(idBuscar)==true)
    {    
        
        if(p.getSig()==null)
        {
                    InsertarAlFinal (datoIns);
        }
        else
        {
                p.setSig(new nodo(p ,datoIns ,p.getSig()));
                p.getSig().getSig().setAnt(p.getSig());
        }//        fin si
    }

    //Fin si
    }//fin metodo insertar despues
    
    public void InsertarAntes( String idBuscar, Object datoIns)
    {

    if(Buscar(idBuscar)==true)
    {    
    
        if(p.getAnt()==null)//if(p==getCabeza())
        {
                    InsertarComoCabeza (datoIns);
        }
        else
        {
                p.setAnt(new nodo(p.getAnt() ,datoIns ,p));
                p.getAnt().getAnt().setSig(p.getAnt());
        }//        fin si
    }
    //Fin si
    }//fin metodo insertar despues
        
    
    
        public nodo getCabeza() {
            return Cabeza;
        }

        
        public void setCabeza(nodo Cabeza) {
            this.Cabeza = Cabeza;
        }

    void insertarAlFinal(Clientes cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    int getSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Clientes getDato(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object PopFinal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean contiene(Clientes cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
        
    }//fin clase lista doble
