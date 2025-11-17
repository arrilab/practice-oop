package org.example.s7_mvc.p5_view;

import static org.example.s7_mvc.p5_view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JpaViewResolver implements ViewResolver {
    @Override
    public View resolveView(String viewName) {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            return new RedirectView(viewName);
        }

        return new JpaView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
