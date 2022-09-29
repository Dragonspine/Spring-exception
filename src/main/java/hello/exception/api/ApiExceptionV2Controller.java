package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.Optional;

/**
 * packageName    : hello.exception.api
 * fileName       : ApiExceptionV2Controller
 * author         : kanghyun Kim
 * date           : 2022/09/29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        kanghyun Kim      최초 생성
 */
@Slf4j
@RequestMapping("/api2")
@RestController
public class ApiExceptionV2Controller {

    /**
     * ExceptionHandler docs
     * https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-exceptionhandler-args
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

    @GetMapping("/members/{id}")
    public ApiExceptionController.MemberDto getMember(@PathVariable("id") String id) {

        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }


        return new ApiExceptionController.MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
