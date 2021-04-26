package mk.finki.ukim.proekt.model.exceptions;

public class InvalidBusinessUnitIdException extends RuntimeException{
    public InvalidBusinessUnitIdException(Long id){
        super(String.format("Business Unit with id: %d was not found.",id));
    }
}
