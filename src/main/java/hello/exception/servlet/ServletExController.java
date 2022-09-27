package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * packageName    : hello.exception.servlet
 * fileName       : ServletExController
 * author         : kanghyun Kim
 * date           : 2022/09/27
 * description    : 서블릿 예외 처리는 아래 2가지 방법을 사용한다
 *  1. Exception (예외)
 *  2. response.sendError(HTTP 상태 코드, 오류 메시지)
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/27        kanghyun Kim      최초 생성
 */
@Slf4j
@Controller
public class ServletExController {

    @GetMapping("/error-ex")
    public void errorEx() {
        // WAS(여기까지 전파) <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러(예외발생)
        throw new RuntimeException("예외 발생!");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404 오류!"); // sendError는 WAS에서 호출 기록을 확인한다
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }
}
