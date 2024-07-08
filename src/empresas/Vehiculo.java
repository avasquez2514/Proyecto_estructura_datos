package empresas;

public class Vehiculo {
    private String placa;
    private String modelo;
    private int año;
    private double tarifa;
    private double costoAlquiler;

    // Resto del código de la clase Vehiculo...

    public Vehiculo IngresarDatos() {
        String placa, modelo;
        int año;
        double tarifa;

        placa = Validaciones.leerString("Digite Placa del vehículo: ");
        modelo = Validaciones.leerString("Digite Modelo del vehículo: ");
        año = Validaciones.leerEntero("Digite Año del vehículo: ");

        // Leer la tarifa de alquiler por día del vehículo
        String tarifaStr = Validaciones.leerString("Digite Tarifa de alquiler por día del vehículo: ");
        try {
            tarifa = Double.parseDouble(tarifaStr);
        } catch (NumberFormatException e) {
            // Si ocurre un error al convertir la entrada a double, imprimir un mensaje de error y establecer la tarifa en 0
            System.out.println("Error al leer la tarifa. La tarifa se establecerá en 0.");
            System.out.println("Entrada del usuario: " + tarifaStr);
            tarifa = 0;
        }

        // Establecer los valores en el objeto actual de Vehiculo
        this.setPlaca(placa);
        this.setModelo(modelo);
        this.setAño(año);
        this.setTarifa(tarifa);

        return this; // Retornar el objeto Vehiculo actual
    }

    
        public void setPlaca(String placa) {
        this.placa = placa;
    }

        public String getPlaca() {
        return placa;
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    // Resto del código de la clase Vehiculo...
    
    public double getTarifa() {
        return tarifa;
        
        
}
    public double getCostoAlquiler(int numDias) {
        return tarifa * numDias; // Multiplica la tarifa diaria por el número de días
    }  
    
        public String toString() {
        return "Placa: " + placa + ", Modelo: " + modelo + ", Año: " + año + ", Tarifa: " + tarifa;
    }
        
public double getCostoAlquiler() {
    return this.costoAlquiler;
}
        

}
