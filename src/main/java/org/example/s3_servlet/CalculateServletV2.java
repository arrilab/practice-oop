package org.example.s3_servlet;

import org.example.s1_oop.s0_calculator.v3_valueobject.application.Calculator;
import org.example.s1_oop.s0_calculator.v3_valueobject.domain.vo.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/calculate")
public class CalculateServletV2 extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CalculateServletV2.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("service servlet(doGet)");
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

        PrintWriter writer = resp.getWriter();
        writer.println(result);
    }
}
