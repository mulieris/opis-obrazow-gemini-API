package com.si.ui;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Header extends HorizontalLayout {

    private Span clock;

    public Header() {

        setClassName("header");
        setWidthFull();
        setHeight("36px");
        getStyle().setColor("yellow");
        try {
            buildUI();
        } catch (Exception e) {
            System.out.println("[Header] Error while building UI. " + e);
        }
    }

    private void buildUI() {

        DrawerToggle burger = new DrawerToggle();
        burger.addClassName("header-burger");
        clock = new Span(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        clock.addClassName("header-clock");
        add(burger, clock);
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {

        UI ui = attachEvent.getUI();
        ui.setPollInterval(1000);

        ui.addPollListener(e -> {
            clock.setText(LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        });
    }
}
