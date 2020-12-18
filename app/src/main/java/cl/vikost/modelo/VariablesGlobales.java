package cl.vikost.modelo;

public class VariablesGlobales {
    private static VariablesGlobales variablesGlobales = new VariablesGlobales();
    public String usuario;

    public static VariablesGlobales getInstance() {
        return variablesGlobales;
    }
}
