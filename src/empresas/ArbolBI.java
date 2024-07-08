
package empresas;

import javax.swing.JOptionPane;

public class ArbolBI 
{
   //atributos propios y privados
    private nodo raiz;
    private Archivo archivo; // Agrega una referencia al archivo donde se guardarán los nodos terminales
   //constructor vacio 
    public ArbolBI()
    {
    raiz=null;
    archivo = new Archivo(); // Inicializa el archivo
    }
   
    //auxiliares
    Object info; //para el manejo de los datos
    nodo q, aux; //apuntadores necesarios para recorrido
    String id,texto="";//cadena para concatenar todos los datos 
    int cont=0;
    Clientes ObjE=new Clientes ();
    
 //metodo que retorna falso si el arbol tiene al menos un dato o verdadero si no hay datos  
    
 public boolean Estavacio()
 {
        return raiz==null; //“ARBOL VACIO!!!“
 }//fin esta vacio
  
 //metodo unico para crear el nodo raiz y deja a raiz y a q ubicados en el nodo
 public void CrearRaiz(Object info) 
    {   
       setRaiz(new nodo(null,info,null)); 
       q=getRaiz();
    }
    //fin crearRaiz
 
 //metodo que crea un nodo y recibe el apuntador, se usa para crear los hijos izq y der
 public void InsertarNodos(nodo q) {
  String id = Validaciones.leerString("Digite id para el hijo de: " + q.getDato().toString());
  Clientes objE = new Clientes().IngresarDatos(); // Crear un nuevo objeto Clientes y solicitar datos
  aux = new nodo(null, objE, null); // Crear un nuevo nodo con el objeto Clientes creado
}

 
 //metodo NO recursivo para crear por la derecha y/o por la izquierda un nodo hijo 
public void IngresarHijos(nodo q)
{
   int resp;
   resp=JOptionPane.showConfirmDialog(null," Deseas ingresar hijo izquierdo (Anterior) de "+q.getDato(),"Ingreso de datos",JOptionPane.YES_NO_OPTION);
   
   if (resp==JOptionPane.YES_OPTION)
   {
            InsertarNodos(q); //se crea el hijo izquierdo del nodo que señala el apuntador q
            q.setAnt(aux);//se enlaza el nuevo nodo por el lado izquierdo del nodo que esta señalando q 
            //o sea que el nodo que señala aux queda como hijo izquierdo del nodo que señala q
   }//fin si
   
   resp=JOptionPane.showConfirmDialog(null,"Deseas Ingresar hijo derecho (Siguiente) de "+q.getDato(),"Ingreso de datos",JOptionPane.YES_NO_OPTION);
   
   if (resp==JOptionPane.YES_OPTION)
   {
            InsertarNodos(q);//se crea el hijo derecho del nodo que señala el apuntador q 
            q.setSig(aux);//se enlaza el nuevo nodo por el lado derecho del nodo que esta señalando q  
            //o sea que el nodo que señala aux queda como hijo derecho del nodo que señala q
   }//fin si
}//Fin InsertarNodos

//metodo para inicializar las variables para que no sume o concatene lo anterior de todo
public void Inicializar()
{
    texto="";
    cont=0;
  
}
//metodo recursivo para crear el arbol
    public void Crear(nodo q)
    {        
      //caso base o caso degenerado
          if(q!=null) 
          {
            IngresarHijos(q);//metodo que crea los hijos izq y/o derecho
            //llamada recursiva por la izquierda
            Crear(q.getAnt()) ;
            //llamada recursiva por la derecha
            Crear(q.getSig()) ;
          }// fin si
    }//fin crear
    
     public String Inorden(nodo q)
    {
      
       //caso base o caso degenerado
          if(q!=null) 
          {
            //llamada recursiva por la izquierda
            Inorden(q.getAnt()) ;
            //raiz
            texto=texto+"( "+q.getDato().toString()+" )\n";
            //llamada recursiva por la derecha
            Inorden(q.getSig()) ;
          }// fin si 
        return texto;  
    }//fin de inorden 
    
   public String Preorden(nodo q)
    {
      
       //caso base o caso degenerado
          if(q!=null) 
          {
            //raiz
            texto=texto+"( "+q.getDato().toString()+" )\n";
            //llamada recursiva por la izquierda
            Preorden(q.getAnt()) ;
            //llamada recursiva por la derecha
            Preorden(q.getSig()) ;
          }// fin si 
        return texto;  
    }//fin de preorden 
   
     public String Postorden(nodo q)
    {
      
       //caso base o caso degenerado
          if(q!=null) 
          {
            //llamada recursiva por la izquierda
            Postorden(q.getAnt()) ;
            //llamada recursiva por la derecha
            Postorden(q.getSig()) ;
            //raiz
            texto=texto+"( "+q.getDato().toString()+" )\n";
          }// fin si 
        return texto;  
    }//fin de preorden 
    
   
 //el peso es el numero de hojas o nodos terminales del arbol    
     public int Peso(nodo q)
    {
      //caso base o caso degenerado
          if(q!=null) 
          {
              if(q.getAnt()==null&&q.getSig()==null)//es nodo terminal u hoja
              {
                  cont++;
              }
            //llamada recursiva por la izquierda
            Peso(q.getAnt()) ;
            //llamada recursiva por la derecha
            Peso(q.getSig()) ;
          }// fin si 
        return cont;  
    }//fin de peso 
    
//retorna una cadena con los nodos hermanos
     public String Hermanos(nodo q) 
     {
        //caso base o caso degenerado
        if (q != null) {
            
            //Raíz
            if(q.getAnt() != null && q.getSig() !=null){//es nodo rama, tiene los dos hijos posibles
                texto = texto + "( " + q.getAnt().getDato()+ " \n*** HERMANO DE *** " + q.getSig().getDato() + " )\n";
            }   
            //llamada recursiva por la izquierda
            Hermanos(q.getAnt());
            //llamada recursiva por la derecha
            Hermanos(q.getSig());
        }// fin si 
        return texto;
    }//fin de preorden
     
 //retorna una cadena con solo los hijos izquierdos    
     public String HijosIzquierdos(nodo q){
        if(q!=null){
            if(q.getAnt()!=null){//si hay nodo a la izquierda
                texto=texto+q.getAnt().getDato()+"\n";
            }
            HijosIzquierdos(q.getAnt());
            HijosIzquierdos(q.getSig());
        }
        return texto;
    }//Fin Hijos Izquierdos
     
    public nodo PruebaEscritorio(nodo raiz) {
      nodo q = new nodo(); // Crear un nuevo nodo
      q.setDato(new Clientes("1010", "Ana", "Lopez", 23, 2)); // Raíz del árbol
  
      // Crear los nodos hijos y establecerlos en la izquierda y derecha de la raíz
      q.setAnt(new nodo(null, new Clientes("1020", "Beto", "Mendez", 28, 2), null)); // hijo izquierdo de ana (beto)
      q.setSig(new nodo(null, new Clientes("1030", "Carla", "Mesa", 38, 3), null)); // hijo derecho de ana (carla)
      q.getAnt().setAnt(new nodo(null, new Clientes("1160", "Pedro", "Gomez", 19, 3), null)); // hijo izquierdo de Beto (pedro)
      q.getAnt().setSig(new nodo(null, new Clientes("1100", "Jimin", "Rua", 21, 4), null)); // hijo derecho de Beto (jimin)
      q.getSig().setSig(new nodo(null, new Clientes("1130", "Muse", "Mesa", 19, 3), null)); // hijo derecho de Carla (muse)
  
      // Devolver la raíz del árbol
      return q;
      
      
  }
  
    
    /**
     * @return the raiz
     */
    public nodo getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(nodo raiz) {
        this.raiz = raiz;
    }

    
}//fin clase arbol BI
