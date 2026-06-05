package com.famisanar.tienda.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(Integer id){
        super(String.format("No se encontró ningun producto con el ID %s",
                id));
    }
}
