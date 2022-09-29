package hello.exception.exhandler.advice;

import hello.exception.api.ApiExceptionController;
import hello.exception.api.ApiExceptionV2Controller;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 * packageName    : hello.exception.exhandler.advice
 * fileName       : ExControllerAdvice
 * author         : kanghyun Kim
 * date           : 2022/09/30
 * description    : @ControllerAdvice 는 대상으로 지정한 여러 컨트롤러(지정안하면 전부)에
 *                  @ExceptionHandler , @InitBinder 기능을 부여해주는 역할을 한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/30        kanghyun Kim      최초 생성
 */
@Slf4j
//@RestControllerAdvice
//@RestControllerAdvice(annotations = RestController.class) // 해당 어노테이션에 적용
@RestControllerAdvice(basePackages = "hello.exception.api") // 패키지 아래 적용
//@RestControllerAdvice(assignableTypes = {ApiExceptionController.class, ApiExceptionV2Controller.class}) // 직접 지정
public class ExControllerAdvice {
    /**
     * ExceptionHandler docs
     * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-exceptionhandler-args
     *
     * ControllerAdvice docs
     * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-controller-advice
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 없으면 정상응답으로 나감
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] illegalExHandler ", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userHandler(UserException e) {
        log.error("[exceptionHandler] userHandler ", e);
        ErrorResult errorResult = new ErrorResult("User ex", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    // 파라미터 클래스의 자식 클래스까지 다 처리해줌(IllegalArgumentException, UserException) 이외는 일로 들어옴
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] exHandler ", e);
        return new ErrorResult("ex", "내부오류");
    }
}
