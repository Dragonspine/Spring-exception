package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * packageName    : hello.exception.exception
 * fileName       : BadRequestException
 * author         : kanghyun Kim
 * date           : 2022/09/29
 * description    : 스프링 부트가 기본으로 제공하는 ExceptionResolver
 *                  1. ExceptionHandlerExceptionResolver
 *                  2. ResponseStatusExceptionResolver : HTTP 응답 코드 변경
 *                  3. DefaultHandlerExceptionResolver : 스프링 내부 예외 처리
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        kanghyun Kim      최초 생성
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "error.bad")
public class BadRequestException extends RuntimeException {
//    ResponseStatusExceptionResolver
}
