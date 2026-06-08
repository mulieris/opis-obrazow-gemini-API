package com.si.ui.tab;

import com.si.MainView;
import com.si.config.ApplicationContext;
import com.si.servis.ImageResponse;
import com.si.servis.WebService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@Route(value = "/tab2", layout = MainView.class)
public class Tab2 extends VerticalLayout {

    Image image = new Image();
    Button button = new Button("Sprawdź");
    MemoryBuffer memoryBuffer = new MemoryBuffer();
    TextField textField = new TextField();

    public Tab2() {

        addClassName("tab2");
        try {
            buildUI();
        } catch (Exception e) {
            System.out.println("[Tab2] Error while building UI.");
        }
    }

    private void buildUI() {

        HorizontalLayout mainLayout = new HorizontalLayout();
        VerticalLayout content = new VerticalLayout();
        content.addClassName("content-tab2");
        Span description = new Span("Prześlij zdjecie: ");

        Upload upload = new Upload(memoryBuffer);
        upload.setAcceptedFileTypes("image/png", "image/jpeg");

        HorizontalLayout layout = new HorizontalLayout();

        button.addClickListener(event -> {
            try {
                String number = textField.getValue();
                byte[] image = memoryBuffer.getInputStream().readAllBytes();
                WebService webService = ApplicationContext.getBean(WebService.class);
                ImageResponse result = webService.isValidNumber(number, image);

                String message;
                if (result.isFound()) {
                    message = "Rozpoznano tablice";
                } else {
                    message = "Nie rozpoznano";
                }
                Notification notification = new Notification(message, 15000);
                notification.open();
            } catch (Exception e) {

            }
        });
        textField.addValueChangeListener(e -> {
            enabledButton();
        });
        upload.addSucceededListener(e -> {

            try {
                byte[] bytes = memoryBuffer.getInputStream().readAllBytes();
                StreamResource resource = new StreamResource(
                        memoryBuffer.getFileName(),
                        () -> new ByteArrayInputStream(bytes)
                );
                image.setSrc(resource);
                image.setHeight("550px");
                image.setWidth("550px");
                enabledButton();
            } catch (IOException ex) {

            }

        });
        upload.addFileRemovedListener(event->{

            memoryBuffer = new MemoryBuffer();
            upload.setReceiver(memoryBuffer);
            enabledButton();
        });
        upload.addFileRejectedListener(fileRejectedEvent -> {
            memoryBuffer = new MemoryBuffer();
            upload.setReceiver(memoryBuffer);
            enabledButton();
        });
        button.setEnabled(false);
        layout.add(textField, button);

        content.add(description, upload, layout);
        mainLayout.add(content, image);
        add(mainLayout);
    }

    private void enabledButton() {

        button.setEnabled(!textField.isEmpty() && memoryBuffer.getFileData() != null);
    }
}
