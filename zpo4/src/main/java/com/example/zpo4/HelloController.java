package com.example.zpo4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private TextField classNameField;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextArea consoleArea;

    private Object currentObject;
    private Class<?> currentClass;

    private final Map<Field, Control> controls = new HashMap<>();

    @FXML
    public void createObject() {

        try {

            gridPane.getChildren().clear();
            controls.clear();

            String className = classNameField.getText();

            currentClass = Class.forName(className);

            Constructor<?> constructor =
                    currentClass.getDeclaredConstructor();

            currentObject = constructor.newInstance();

            Field[] fields = currentClass.getDeclaredFields();

            int row = 0;

            for (Field field : fields) {

                String fieldName = field.getName();

                Label label = new Label(fieldName);

                String getterName =
                        "get" + capitalize(fieldName);

                Method getter =
                        currentClass.getMethod(getterName);

                Object value =
                        getter.invoke(currentObject);

                Control control;

                if(fieldName.toLowerCase().contains("text")) {

                    TextArea area = new TextArea();

                    if(value != null)
                        area.setText(value.toString());

                    area.setPrefHeight(80);

                    control = area;

                } else {

                    TextField textField = new TextField();

                    if(value != null)
                        textField.setText(value.toString());

                    control = textField;
                }

                gridPane.add(label, 0, row);
                gridPane.add(control, 1, row);

                controls.put(field, control);

                row++;
            }

            consoleArea.appendText(
                    "Utworzono obiekt klasy: "
                            + className + "\n");

        }
        catch (Exception e) {

            consoleArea.appendText(
                    "Błąd: " + e.getMessage() + "\n");
        }
    }

    @FXML
    public void saveChanges() {

        for(Map.Entry<Field, Control> entry
                : controls.entrySet()) {

            Field field = entry.getKey();

            Control control = entry.getValue();

            try {

                String text;

                if(control instanceof TextField) {

                    text =
                            ((TextField) control).getText();

                } else {

                    text =
                            ((TextArea) control).getText();
                }

                Object value =
                        convertValue(text, field.getType());

                String setterName =
                        "set" + capitalize(field.getName());

                Method setter =
                        currentClass.getMethod(
                                setterName,
                                field.getType()
                        );

                setter.invoke(currentObject, value);

                consoleArea.appendText(
                        "Zapisano: "
                                + field.getName()
                                + " = "
                                + value
                                + "\n");

            }
            catch (Exception e) {

                consoleArea.appendText(
                        "Nie można zapisać pola: "
                                + field.getName()
                                + "\n");
            }
        }
    }

    private Object convertValue(
            String text,
            Class<?> type
    ) {

        if(type == String.class)
            return text;

        if(type == int.class
                || type == Integer.class)
            return Integer.parseInt(text);

        if(type == boolean.class
                || type == Boolean.class)
            return Boolean.parseBoolean(text);

        if(type == double.class
                || type == Double.class)
            return Double.parseDouble(text);

        if(type == long.class
                || type == Long.class)
            return Long.parseLong(text);

        if(type == float.class
                || type == Float.class)
            return Float.parseFloat(text);

        if(type == short.class
                || type == Short.class)
            return Short.parseShort(text);

        if(type == byte.class
                || type == Byte.class)
            return Byte.parseByte(text);

        if(type == char.class
                || type == Character.class)
            return text.charAt(0);

        return null;
    }

    private String capitalize(String text) {

        return text.substring(0,1).toUpperCase()
                + text.substring(1);
    }
}