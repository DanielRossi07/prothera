package enums;

public enum EmployeeRoles {
    OPERADOR("Operador"),
    COORDENADOR("Coordenador"),
    DIRETOR("Diretor"),
    RECEPCIONISTA("Recepcionista"),
    GERENTE("Gerente"),
    ELETRICISTA("Eletricista"),
    CONTADOR("Contador");


    private final String description;

    EmployeeRoles(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
