package com.si;

import com.si.ui.Header;
import com.si.ui.tab.MenuTabs;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("")
@PageTitle("Aplikacja si")
@CssImport("./styles/styles.css")
public class MainView extends AppLayout {

    public MainView() {

        addClassName("main-view");
        addToNavbar(new Header());
        addToDrawer(new MenuTabs());
    }
}
