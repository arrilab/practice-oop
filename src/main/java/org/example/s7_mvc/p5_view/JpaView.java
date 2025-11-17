package org.example.s7_mvc.p5_view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JpaView implements View {

    private final String viewName;

    public JpaView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        model.forEach(request::setAttribute);

        request.getRequestDispatcher(viewName).forward(request, response);
    }
}
