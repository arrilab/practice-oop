package org.example.s3_servlet;

import org.example.s1_oop.s0_calculator.v3_valueobject.application.Calculator;
import org.example.s1_oop.s0_calculator.v3_valueobject.domain.vo.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/calculate")
public class CalculateServletV1 extends GenericServlet {
    private static final Logger log = LoggerFactory.getLogger(CalculateServletV1.class);

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
}
