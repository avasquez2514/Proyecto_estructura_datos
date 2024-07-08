
package empresas;

import java.awt.HeadlessException;//para manejar situaciones en la que se requiere un entorno gráfico y No se tiene
import java.io.BufferedWriter;
import java.io.File;// clase File para trabajar con archivos y directorios
import java.io.FileInputStream;//permite la lectura de bytes desde un archivo
import java.io.FileOutputStream;// permite la escritura de bytes
import java.io.FileWriter;
import java.io.IOException;//excepcion de i-o
import java.io.ObjectInputStream; //Permite leer objetos desde un flujo de entrada, útil para la deserialización de objetos.
import java.io.ObjectOutputStream;//Permite escribir objetos en un flujo de salida, útil para la serialización de objetos.
import java.io.PrintWriter;
import java.io.Serializable;//Marca una clase como serializable, lo que significa que sus objetos pueden ser convertidos a bytes para su almacenamiento o transmisión

import javax.swing.JOptionPane;//mostrar cuadros de díalogo

/*CRUD son las siglas de crear, leer, actualizar y borrar 
(por sus siglas en inglés Create, Read, Update y Delete) 
y hace referencia a las principales operaciones para interactuar con los registros 
de una base de datos. Un registro es cada entrada o fila en una tabla de la base de datos
y una linea de texto del archivo de texto*/

public class CRUDClientes implements Serializable {
    private boolean cambios = false;

    public Archivo Creacion() {
        Archivo Arch = null;
        ObjectInputStream objis = null;
        try {
            File fichero = new File("C:\\Users\\Usuario\\Desktop\\2023-2\\virtualED\\Empresas\\Empleados.txt");
            if (!fichero.exists()) {
                Arch = new Archivo();
                JOptionPane.showMessageDialog(null, "*********** A R C H I V O  N U E V O *********");
            } else {
                objis = new ObjectInputStream(new FileInputStream(fichero));
                Arch = (Archivo) objis.readObject();
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                if (objis != null) {
                    objis.close();
                }
            } catch (IOException e) {
            }
        }
        return Arch;
    }


    public void Addicionar(Archivo ArchEst) {
        int posi;
        String Cc = "", No, Ap;
        int Ed, EstS;

        try {
            Cc = Validaciones.leerString("Ingrese el ID para Agregar:  ");
        } catch (HeadlessException | NumberFormatException e) {
        }
        posi = ArchEst.Buscar(Cc);
        if (posi == -1) {
            try {
                No = Validaciones.leerString("Identificacion:    " + Cc + "\nNombre:    ");
                Ap = Validaciones.leerString("Direccion:    ");
                Ed = Validaciones.leerEntero("Edad:     ");
                EstS = Validaciones.leerEntero("Numero telefonico:    ");

                Clientes nuevoCliente = new Clientes(Cc, No, Ap, Ed, EstS);
                ArchEst.Agregar(nuevoCliente);
                cambios = true;
                guardarEnArchivoTexto(nuevoCliente);
            } catch (HeadlessException | NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "*********** R E G I S T R O   Y A   E X I S T E *********");
        }
    }

    
    public void Retirar(Archivo ArchEst) {
        String Cc = "";
        boolean eliminado = false;

        try {
            Cc = Validaciones.leerString("Ingrese el Id del empleado a Borrar:  ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        eliminado = ArchEst.Retirar(Cc);
        if (eliminado) {
            JOptionPane.showMessageDialog(null, "************* Registro eliminado del archivo*****************  " + Cc);
            cambios = true;
        } else if (ArchEst.longitud() != 0) {
            JOptionPane.showMessageDialog(null, "************* Registro No se Encuentra en el archivo*****************" + Cc);
        } else {
            JOptionPane.showMessageDialog(null, "************* A r c h i v o   v a c i o *****************");
        }
    }

    public void consultar(Archivo ArchEst) {
        int posi;
        String Cc = "";

        try {
            Cc = Validaciones.leerString("Ingrese el ID para Buscar:  ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        posi = ArchEst.Buscar(Cc);
        if (posi == -1) {
            if (ArchEst.longitud() != 0) {
                JOptionPane.showMessageDialog(null, "************* Registro No se Encuentra en el archivo***************** " + Cc);
            } else {
                JOptionPane.showMessageDialog(null, "************* A r c h i v o   v a c i o *****************");
            }
        } else {
            JOptionPane.showMessageDialog(null, "  " + ArchEst.EmpleadoPosi(posi).toString());
        }
    }

    public void escribir(Archivo ArchEst) {
        ObjectOutputStream ous = null;
        try {
            if (cambios) {
                ous = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Usuario\\Desktop\\2023-2\\virtualED\\Empresas\\Empleados.txt"));
                ous.writeObject(ArchEst);
            }
            ArchEst = null;
        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public void Actualizar(Archivo ArchEmp) {
        boolean posi;
        String Cc = "";
        try {
            Cc = Validaciones.leerString("Ingrese el ID para actualizar:  ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        posi = ArchEmp.Cambiar(Cc);
        if (!posi) {
            JOptionPane.showMessageDialog(null, "************* Registro No se Encuentra en el archivo***************** " + Cc);
        } else {
            if (ArchEmp.longitud() != 0) {
                JOptionPane.showMessageDialog(null, "************* A r c h i v o   v a c i o *****************");
            }
        }
    }
    


    public void guardarEnArchivoTexto(Clientes cliente) {
        try (FileWriter fw = new FileWriter("clientes.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("ID: " + cliente.getCc());
            out.println("Nombre: " + cliente.getNom());
            out.println("Direccion: " + cliente.getApell());
            out.println("Edad: " + cliente.getEdad());
            out.println("Numero telefonico: " + cliente.getEstS());
            out.println("------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
