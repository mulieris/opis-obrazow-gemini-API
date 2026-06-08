package com.si.ui.tab;

import com.si.MainView;
import com.si.config.ApplicationContext;
import com.si.servis.PhotoToChange;
import com.si.servis.WebService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
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
@CssImport("./styles/tab3.css")
@Route(value = "/tab3", layout = MainView.class)
public class Tab3 extends VerticalLayout {
    Image imageToChange = new Image();
    Button buttonPhotoChange = new Button("Opisz");
    MemoryBuffer memoryBufferChange = new MemoryBuffer();
    TextField textFieldChange = new TextField();

    public Tab3() {

        addClassName("tab3");
        try {
            buildUI();
        } catch (Exception e) {
            System.out.println("[Tab3] Error while building UI.");
        }
    }

    private void buildUI() {

        HorizontalLayout mainLayout = new HorizontalLayout();
        VerticalLayout content = new VerticalLayout();
        content.addClassName("content-tab3");
        Span description = new Span("Prześlij zdjecie: ");

        Upload upload = new Upload(memoryBufferChange);
        upload.setAcceptedFileTypes("image/png", "image/jpeg");

        HorizontalLayout layout = new HorizontalLayout();
        buttonPhotoChange.addClickListener(event -> {
            try {
                String text = textFieldChange.getValue();
                byte[] image = memoryBufferChange.getInputStream().readAllBytes();
                WebService webService = ApplicationContext.getBean(WebService.class);
                PhotoToChange result = webService.isValidChange(text, image);

                String message;
                if (result.isFound()) {
                    message = result.getAnswer().toString();
                } else {
                    message = "To nie jest coś co mogę opisać.";
                }
                Notification notification = new Notification(message, 15000);
                notification.open();
            } catch (Exception e) {
                Notification.show("Błąd / Error: " + e.getMessage(), 15000, Notification.Position.MIDDLE);
            }
        });
        textFieldChange.addValueChangeListener(e -> {
            enabledButton();
        });
        upload.addSucceededListener(e -> {

            try {
                byte[] bytes = memoryBufferChange.getInputStream().readAllBytes();
                StreamResource resource = new StreamResource(
                        memoryBufferChange.getFileName(),
                        () -> new ByteArrayInputStream(bytes)
                );
                imageToChange.setSrc(resource);
                imageToChange.setHeight("550px");
                imageToChange.setWidth("550px");
                enabledButton();
            } catch (IOException ex) {

            }
        });
        upload.addFileRemovedListener(event->{

            memoryBufferChange = new MemoryBuffer();
            upload.setReceiver(memoryBufferChange);
            enabledButton();
        });
        buttonPhotoChange.setEnabled(false);
        layout.add(textFieldChange, buttonPhotoChange);

        content.add(description, upload, layout);
        mainLayout.add(content, imageToChange);
        add(mainLayout);
    }

    private void enabledButton() {

        buttonPhotoChange.setEnabled(!textFieldChange.isEmpty() && memoryBufferChange.getFileData() != null);
    }
}