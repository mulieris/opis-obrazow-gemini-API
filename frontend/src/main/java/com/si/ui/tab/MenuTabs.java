package com.si.ui.tab;

import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;


public class MenuTabs extends Tabs {

    public MenuTabs() {

        try {
            buildMenu();
        } catch (Exception e) {
            System.out.println("[MenuTabs] Error while build UI.");
        }
    }

    private void buildMenu() {

        Tab tab1 = new Tab(new RouterLink("GrammarCheckTab", GrammarCheckTab.class));
        Tab tab2 = new Tab(new RouterLink("Tab2", Tab2.class));
        Tab tab3 = new Tab(new RouterLink("Tab3", Tab3.class));

        add(tab1, tab2, tab3);
        setOrientation(Tabs.Orientation.VERTICAL);
    }
}
