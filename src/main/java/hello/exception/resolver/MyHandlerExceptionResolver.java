package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * packageName    : hello.exception.resolver
 * fileName       : MyHandlerExceptionResolver
 * author         : kanghyun Kim
 * date           : 2022/09/29
 * description    : Controller 에서 넘어온 Exception 을 DispatcherServlet 이 받아
 *                  ExceptionResolver 에게 해결을 맞김
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/09/29        kanghyun Kim      최초 생성
 */
@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    /**
     * 1. 빈 ModelAndView 를 반환하면 뷰를 렌더링 하지않고, 정상 흐름으로 서블릿이 리턴된다.
     * 2. ModelAndView 지정하면 뷰를 렌더링함
     * 3. null 을 반환하면 다음 ExceptionResolver 를 찾고 더이상 없으면 예외를 서블릿 밖으로 던짐
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("call resolver", ex);
        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView(); // exception을 처리해서 정상 흐름처럼 변경
            }

        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
    }
}
