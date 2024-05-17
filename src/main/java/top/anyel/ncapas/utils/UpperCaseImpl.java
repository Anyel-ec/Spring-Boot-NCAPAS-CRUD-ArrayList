package top.anyel.ncapas.utils;

public class UpperCaseImpl implements UpperCase {
    public String upperCase(String texto){
        if (texto == null) {
            return null;
        }
        return texto.toUpperCase();
    }
}
