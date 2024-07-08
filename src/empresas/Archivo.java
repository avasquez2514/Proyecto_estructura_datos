
package empresas;


import java.io.Serializable;

import javax.swing.JOptionPane;

/*La serialización es un proceso que consiste en convertir un objeto en una secuencia 
de bytes para almacenar el objeto o transmitirlo a la memoria, a una base de 
datos o a un archivo. Su propósito principal es guardar el estado de un objeto 
para poder volver a crearlo cuando sea necesario.*/
public class Archivo implements Serializable
{
    private Object[] archivo;  //archivo es un vector, pero este vector SI SE GRABA, no como los que usamos que eran temporales
    private int nElementos;//numero de elementos del vector
 
    //constructor
    public Archivo() //constructor vacio de la clase archivo
    {
        // Crea el vector empleado para grabar en el archivo
        nElementos = 0;
        archivo = Dimensionar(nElementos);//invoca el metodo para inicializar el archivo, aumenta o disminuye de acuerdo a lo que mandemos
    }//fin constructor
 
    /*este metodo crea el archivo y lo retorna, con el numero de elementos que 
    le manden, ojo...lo borra y lo crea de nuevo*/
    private Object[] Dimensionar(int nElementos)
    {
        try 
        {
           return new Clientes[nElementos];//pide memoria para el vector tipo empleados - object
        } catch (OutOfMemoryError e) {//Una excepción OutOfMemoryError puede ser el resultado de quedarse sin espacio en el almacenamiento dinámico de 
            System.out.println(e.toString());
            return archivo;
                                      }//para el manejo de excepciones
    }//fin de crear vector archivo
  
    /*este metodo retorna el registro de empleado, en caso de existir, es para consultar 
    el registro en la posicion que le llega*/
    public Object EmpleadoPosi(int i) 
    {
        if (i >= 0 && i < nElementos) {
            return archivo[i];
        } else {
            System.out.println("No hay cliente en esa posicion");
            return null;
        }
    }//fin de retornar empleado en la posicion que le llegue
 
    public int longitud() 
    {
        return archivo.length;//retorna la longitud del archivo o sea del vector
    }//fin de longitud
 
    /*redimensionar es copiar el vector en otro y volver a pasar la info al archivo,
    es usar otro vector auxiliar*/
    public void Agregar(Object objemp) 
    {
        Object[] copiaDeLista;//vector auxiliar 
 
        //el vector crece conforme se le van anadiendo nuevos elementos 
        copiaDeLista = archivo;
        nElementos = copiaDeLista.length;
        archivo = Dimensionar(nElementos + 1);//se crea el vector auxiliar con una posicion mas
        for (int i = 0; i < nElementos; i++) {//ciclo para pasar todo el vector al nuevo con una poicion mas
            archivo[i] = copiaDeLista[i];
        }
        archivo[nElementos] = objemp;//se coloca el nuevo empleado en la nueva posicion del vector
        nElementos++;
    }//fin de agregar
 
    /*retira el especifico con null, pasando de vector a vector, o sea tambien se usa 
    un vector auxiliar porque es la forma de cambiar en vector estatico, creando otro!
    en este caso no se aumento el numero de elementos sino que se disminuye*/
    public boolean Retirar(String cod) 
    {
        Object[] copiaDeLista;//vector auxiliar
        int posi = Buscar(cod);//se llama al metodo buscar para que retorne la posicion donde esta ubicado
        if (posi != -1) {//si el empleado se encuentra en una posicion válida 
            archivo[posi] = null;//se borra el empleado
            copiaDeLista = archivo;//se pasa el archivo al vector auxiliar
            if (copiaDeLista.length != 0) {//si hay vector
                
                nElementos = copiaDeLista.length;//se toma el numero de elementos del vector aux
                archivo = Dimensionar(nElementos - 1);//se quita una posicion porque se va a eliminar
                int j=0;
                for (int i = 0; i < nElementos; i++) {//ciclo para pasar todo el vector al nuevo
                    if (copiaDeLista[i] != null) {//hay vector aux?
                        archivo[j] = copiaDeLista[i];//la j es el indice del nuevo, la i de la copia
                        j=j+1;
                    }
                }
                nElementos--;// el vector disminuye cuando se eliminan elementos
                return true;
            }//fin si
        }//fin si
        return false;//no hay vectores o no se realiza el proceso
    }//fin de retirar empleado
 
    //metodo buscar, retorna la posicion y recibe el codigo o id del empleado a buscar
    public int Buscar(String cod) {
        int posi = -1;
        for (int i = 0; i < nElementos; i++) {
            if (((Clientes)archivo[i]).getCc().equals(cod)) {//si el id o cod es igual al almacenado en el vector
                posi = i;//se guarda en posi
            }//fin si
        }//fin para i
        return posi;//retorna la posicion o -1 si no lo encuentra
    }//fin de buscar

    //metodo que retorna una cadena con todo lo del archivo, en caso de tener registros
    public String MostrarTodo() 
    {
    	String cadena="";
        if(nElementos==0)
        {
        System.out.println("**** A R C H I V O   V A C I O *****");
        }
        else
        {
            for (int i = 0; i < nElementos; i++) 
            {
             cadena=cadena+"  "+archivo[i].toString()+"\n";
            }
        }
        return cadena;        
    } //fin mostrar todo 
    
     //metodo buscar, retorna la posicion y recibe el codigo o id del empleado a buscar
    public boolean Cambiar(String cod) {
        boolean sw=false;
        int resp;
        Clientes objE=new Clientes();
        Object objEmp;
        

        int posi=-1;
        posi=Buscar(cod);//buscamos el codigo y la posicion en el vector archivo
        if(posi!=-1)//si se encontro
        {
            objEmp=EmpleadoPosi(posi);//se invoca el metodo que retorna el empleado de acuerdo a la posicion
            //colocamos la informacion del archivo en las auxiliares para permitir los cambios
        
            resp=JOptionPane.showConfirmDialog(null,"Grabar los cambios?","Actualizando",JOptionPane.YES_NO_OPTION);
                if(resp==JOptionPane.YES_OPTION)//si quiere cambiar los datos
                {
                    Remplazar(objEmp);//se graba en el archivo usando el vector auxiliar
                    sw=true;//se encuentra y se graban los cambios
                    JOptionPane.showMessageDialog(null,"Se grabaron los cambios satisfactoriamente");
                }
                else
                    JOptionPane.showMessageDialog(null,"NO se grabaron los cambios");
                //fin si
            }//fin si
        
        return sw;//retorna si se cambio o no
    }//fin de buscar
    
 public void Remplazar(Object objemp) 
    {
        Object[] copiaDeLista;//vector auxiliar 
 
        //el vector crece conforme se le van anadiendo nuevos elementos 
        copiaDeLista = archivo;
        nElementos = copiaDeLista.length;
        archivo = Dimensionar(nElementos);//se crea el vector auxiliar con una posicion mas
        for (int i = 0; i < nElementos; i++) {//ciclo para pasar todo el vector al nuevo con una poicion mas
            //si lo encuentra no pasamos el contenido del vector sino el objeto cambiado
            if (((Clientes)copiaDeLista[i]).getCc().equals(((Clientes)objemp).getCc())) {
                 archivo[i] = objemp;
                }
            else
                archivo[i] = copiaDeLista[i];
        }//fin para
    }//fin de reemplazar


 //metodo que pasa el archivo a la lists, en caso de tener registros
    public ListaDoble CopiarArchivoLista(ListaDoble objlis) 
    {
    	String cadena="";
        if(nElementos==0)
        {
        System.out.println("**** A R C H I V O   V A C I O *****");
        }
        else
        {
            for (int i = 0; i < nElementos; i++) 
            {
             objlis.CrearNodoPorFinal(archivo[i]);
            }
        }
        return objlis;        
    } //fin    
    
    
    
    
}//fin clase archivo
