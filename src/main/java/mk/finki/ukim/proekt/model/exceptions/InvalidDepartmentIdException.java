package mk.finki.ukim.proekt.model.exceptions;

public class InvalidDepartmentIdException extends RuntimeException{
    public InvalidDepartmentIdException(Long id){
        super(String.format("Department with id: %d was not found.",id));
    }
}
