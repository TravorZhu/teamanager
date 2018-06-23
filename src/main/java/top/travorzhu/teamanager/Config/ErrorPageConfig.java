package top.travorzhu.teamanager.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorPageConfig implements ErrorPageRegistrar {

    private static final Logger logger = LoggerFactory.getLogger(ErrorPageConfig.class);

    @Override
    public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
        ErrorPage e400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400.html");
        ErrorPage e401 = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.html");
        ErrorPage e403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403.html");
        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.html");
        ErrorPage e405 = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405.html");
        ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
        ErrorPage e502 = new ErrorPage(HttpStatus.BAD_GATEWAY, "/error/502.html");
        ErrorPage e503 = new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/error/503.html");
        ErrorPage e504 = new ErrorPage(HttpStatus.GATEWAY_TIMEOUT, "/error/504.html");
        errorPageRegistry.addErrorPages(e400, e401, e403, e404, e405, e500, e502, e503, e504);
    }

}
