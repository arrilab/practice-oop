package org.example.s7_mvc.p5_view;

public class ModelAndView {
    private final String viewName;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
