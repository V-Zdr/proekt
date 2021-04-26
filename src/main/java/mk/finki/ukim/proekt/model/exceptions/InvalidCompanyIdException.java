package mk.finki.ukim.proekt.model.exceptions;

public class InvalidCompanyIdException extends RuntimeException{
    public InvalidCompanyIdException(Long id){
        super(String.format("Business Unit with id: %d was not found.",id));
    }
}
