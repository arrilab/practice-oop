package org.example.s3_servlet;

import org.example.s1_oop.s0_calculator.v3_valueobject.application.Calculator;
import org.example.s1_oop.s0_calculator.v3_valueobject.domain.vo.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/calculate")
public class CalculateServletV0 implements Servlet {
    private static final Logger log = LoggerFactory.getLogger(CalculateServletV0.class);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        log.info("init servlet");
    }

    @Override
    public void service(ServletRequest req, ServletResponse rep) throws ServletException, IOException {
        log.info("service servlet");
        // 연산 입력
        int operand1 = Integer.parseInt(req.getParameter("operand1"));
        String operator = req.getParameter("operator");
        int operand2 = Integer.parseInt(req.getParameter("operand2"));

        // 연산 결과
        int result = Calculator.calculate(
                new PositiveNumber(operand1),
                operator,
                new PositiveNumber(operand2)
        );

        PrintWriter writer = rep.getWriter();
        writer.println(result);
    }

    @Override
    public void destroy() {
        log.info("destroy servlet");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return "";
    }
}
