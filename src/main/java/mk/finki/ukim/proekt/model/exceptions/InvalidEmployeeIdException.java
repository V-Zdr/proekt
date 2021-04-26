package mk.finki.ukim.proekt.model.exceptions;

public class InvalidEmployeeIdException  extends RuntimeException{
    public InvalidEmployeeIdException(Long id){
        super(String.format("Employee with id: %d was not found.",id));
    }
}
