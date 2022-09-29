package hello.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * packageName    : hello.exception.exhandler
 * fileName       : ErrorResult
 * author         : kanghyun Kim
 * date           : 2022/09/29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        kanghyun Kim      최초 생성
 */
@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
