package arom.todolist.exception;

public class LogOutTokenException extends RuntimeException{
    @Override
    public String getMessage() {
        return "로그아웃된 사용자입니다";
    }
}
