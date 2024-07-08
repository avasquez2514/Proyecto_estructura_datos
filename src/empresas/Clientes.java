    package empresas;
    import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class Clientes {
        // Atributos propios y privados
        private String Cc, Nom, Apell;
        private int Edad, EstS;
        private int SBM;
        private List<Vehiculo> vehiculosAlquilados; // Lista de vehículos alquilados

        // Constructor por defecto
        public Clientes() {
            vehiculosAlquilados = new ArrayList<>(); // Inicializar la lista en el constructor
        }

        // Constructor con parámetros
        public Clientes(String Cc, String Nom, String Apell, int Edad, int EstS) {
            this.Cc = Cc;
            this.Nom = Nom;
            this.Apell = Apell;
            this.Edad = Edad;
            this.EstS = EstS;
            this.SBM = 0; // Inicializar SBM con un valor por defecto
            vehiculosAlquilados = new ArrayList<>(); // Inicializar la lista en el constructor
        }

        // Métodos getters y setters
        public String getCc() {
            return Cc;
        }

        public void setCc(String Cc) {
            this.Cc = Cc;
        }

        public String getNom() {
            return Nom;
        }

        public void setNom(String Nom) {
            this.Nom = Nom;
        }

        public String getApell() {
            return Apell;
        }

        public void setApell(String Apell) {
            this.Apell = Apell;
        }

        public int getEdad() {
            return Edad;
        }

        public void setEdad(int Edad) {
            this.Edad = Edad;
        }

        public int getEstS() {
            return EstS;
        }

        public void setEstS(int EstS) {
            this.EstS = EstS;
        }

        public int getSBM() {
            return SBM;
        }

        public void setSBM(int SBM) {
            this.SBM = SBM;
        }

        // Método para obtener el nombre completo del cliente
        public String getNombreCompleto() {
            return Nom + " " + Apell;
        }

        // Método para agregar un vehículo alquilado
        public void agregarVehiculo(Vehiculo vehiculo) {
            vehiculosAlquilados.add(vehiculo);
        }

        // Método para obtener la lista de vehículos alquilados
        public List<Vehiculo> getVehiculosAlquilados() {
            return vehiculosAlquilados;
        }

        // Método para ingresar datos del cliente
        public Clientes IngresarDatos() {
            String id = Validaciones.leerString("Digite Numero cedula: ");
            String no = Validaciones.leerString("Digite Nombre: ");
            String ape = Validaciones.leerString("Digite Direccion: ");
            int ES = Validaciones.leerEntero("Digite Numero edad: ");
            int ed = Validaciones.leerEntero("Digite Numero telefonico: ");

            // Establecer los valores en el objeto actual de Cliente
            this.Cc = id;
            this.Nom = no;
            this.Apell = ape;
            this.Edad = ES;
            this.SBM = ed;
            

            // Crear una nueva lista de vehículos alquilados
            vehiculosAlquilados = new ArrayList<>();

            return this; // Retornar el objeto Cliente actual
        }

        public void ingresarPlacasVehiculosAlquilados() {
        int numVehiculos = Validaciones.leerEntero("Ingrese la cantidad de vehículos alquilados por el cliente:");
        for (int i = 0; i < numVehiculos; i++) {
            String placa = Validaciones.leerString("Ingrese la placa del vehículo " + (i+1) + " alquilado:");
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setPlaca(placa);
            agregarVehiculo(vehiculo);
        }
    }

        // Método para representar el objeto como una cadena de texto
        @Override
        public String toString() {
            return "Cc = " + Cc + ", Nombre = " + Nom + ", Apellido = " + Apell + ", Edad = " + Edad + ", Numero telefonico: " + EstS ;
        }

        double calcularCostoAlquiler() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        String getNombre() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

            public double getMayorGasto() {
        double mayorGasto = 0;
        for (Vehiculo vehiculo : vehiculosAlquilados) {
            double costoAlquiler = vehiculo.getCostoAlquiler();
            if (costoAlquiler > mayorGasto) {
                mayorGasto = costoAlquiler;
            }
        }
        return mayorGasto;
    }

    public Object Editar(String id, String no, String ape, int ES, int ed) {
        System.out.println("Editar Cliente:");
        System.out.println("1. Numero cedula: " + this.getCc());
        System.out.println("2. Nombre: " + this.getNom());
        System.out.println("3. Direccion: " + this.getApell());
        System.out.println("4. Edad: " + this.getEdad());
        System.out.println("5. Numero telefonico: " + this.getSBM());
        System.out.println("6. Terminar");
    
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.print("Seleccione el número de la opción que desea editar: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea en el buffer
    
            switch (opcion) {
                case 1:
                    System.out.print("Nuevo numero cedula: ");
                    this.setCc(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Nuevo nombre: ");
                    this.setNom(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Nueva direccion: ");
                    this.setApell(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Nueva edad: ");
                    this.setEdad(scanner.nextInt());
                    break;
                case 5:
                    System.out.print("Nuevo numero telefonico: ");
                    this.setSBM(scanner.nextInt());
                    break;
                case 6:
                    System.out.println("Edición terminada.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);
    
        return this;
    }
    


    }
