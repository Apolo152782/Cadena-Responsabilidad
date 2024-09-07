// Clase base para los manejadores en la cadena
abstract class SoporteTecnico {
    protected SoporteTecnico siguienteNivel;

    // Configura el siguiente manejador en la cadena
    public void setSiguienteNivel(SoporteTecnico siguienteNivel) {
        this.siguienteNivel = siguienteNivel;
    }

    // Método abstracto para manejar las solicitudes
    public abstract void manejarProblema(String problema);
}

// Primer nivel: Soporte automatizado
class SoporteAutomatizado extends SoporteTecnico {
    @Override
    public void manejarProblema(String problema) {
        if (problema.equals("soluciones comunes")) {
            System.out.println("Soporte Automatizado: Ofreciendo soluciones comunes.");
        } else {
            System.out.println("Soporte Automatizado: No puedo resolverlo. Pasando al operador humano.");
            if (siguienteNivel != null) {
                siguienteNivel.manejarProblema(problema);
            }
        }
    }
}

// Segundo nivel: Operador humano
class OperadorHumano extends SoporteTecnico {
    @Override
    public void manejarProblema(String problema) {
        if (problema.equals("manual del usuario")) {
            System.out.println("Operador Humano: Leyendo el manual del usuario.");
        } else {
            System.out.println("Operador Humano: No puedo resolverlo. Pasando al ingeniero.");
            if (siguienteNivel != null) {
                siguienteNivel.manejarProblema(problema);
            }
        }
    }
}

// Tercer nivel: Ingeniero
class Ingeniero extends SoporteTecnico {
    @Override
    public void manejarProblema(String problema) {
        System.out.println("Ingeniero: Resolviendo el problema técnico complejo: " + problema);
    }
}

// Clase principal para probar el patrón Chain of Responsibility
public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // Crear los diferentes niveles de soporte
        SoporteTecnico soporteAutomatizado = new SoporteAutomatizado();
        SoporteTecnico operadorHumano = new OperadorHumano();
        SoporteTecnico ingeniero = new Ingeniero();

        // Configurar la cadena de responsabilidad
        soporteAutomatizado.setSiguienteNivel(operadorHumano);
        operadorHumano.setSiguienteNivel(ingeniero);

        // Simular el proceso de soporte técnico
        System.out.println("Intentando resolver un problema con soluciones comunes...");
        soporteAutomatizado.manejarProblema("soluciones comunes");

        System.out.println("\nIntentando resolver un problema más complejo...");
        soporteAutomatizado.manejarProblema("problema de drivers");
    }
}