package com.example.lab4;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private TextField classNameField;

    @FXML
    private VBox propertiesBox;

    @FXML
    private TextArea consoleArea;

    private Object currentObject;
    private final Map<Field, Control> fieldsMap = new HashMap<>();

    @FXML
    public void initialize() {
        classNameField.setText("com.example.lab4.Song");
    }

    @FXML
    public void createObject() {
        propertiesBox.getChildren().clear();
        fieldsMap.clear();
        consoleArea.clear();

        try {
            String className = classNameField.getText().trim();

            Class<?> clazz = Class.forName(className);
            currentObject = clazz.getDeclaredConstructor().newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                String fieldName = field.getName();

                Method getter = clazz.getMethod(createGetterName(fieldName));
                Object value = getter.invoke(currentObject);

                Label label = new Label("<- " + fieldName);
                label.setPrefWidth(120);

                Control input;

                if (fieldName.toLowerCase().contains("text")) {
                    TextArea textArea = new TextArea();
                    textArea.setPrefRowCount(4);
                    textArea.setText(value == null ? "" : value.toString());
                    input = textArea;
                } else {
                    TextField textField = new TextField();
                    textField.setText(value == null ? "" : value.toString());
                    input = textField;
                }

                HBox row = new HBox(10);
                row.getChildren().addAll(input, label);

                propertiesBox.getChildren().add(row);
                fieldsMap.put(field, input);
            }

            consoleArea.appendText("Object created: " + className + "\n");

        } catch (Exception e) {
            consoleArea.appendText("Cannot create object: " + e.getMessage() + "\n");
        }
    }

    @FXML
    public void saveChanges() {
        if (currentObject == null) {
            consoleArea.appendText("First create object.\n");
            return;
        }

        Class<?> clazz = currentObject.getClass();

        for (Map.Entry<Field, Control> entry : fieldsMap.entrySet()) {
            Field field = entry.getKey();
            Control control = entry.getValue();

            String fieldName = field.getName();
            String textValue = getControlValue(control);

            try {
                Object convertedValue = convertValue(textValue, field.getType());

                Method setter = clazz.getMethod(
                        createSetterName(fieldName),
                        field.getType()
                );

                setter.invoke(currentObject, convertedValue);

                consoleArea.appendText(fieldName + "=" + textValue + "\n");

            } catch (Exception e) {
                consoleArea.appendText(
                        "The property named " + fieldName + " can't be changed: " + e.getMessage() + "\n"
                );
            }
        }
    }

    private String getControlValue(Control control) {
        if (control instanceof TextField textField) {
            return textField.getText();
        }

        if (control instanceof TextArea textArea) {
            return textArea.getText();
        }

        return "";
    }

    private String createGetterName(String fieldName) {
        return "get" + capitalize(fieldName);
    }

    private String createSetterName(String fieldName) {
        return "set" + capitalize(fieldName);
    }

    private String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    private Object convertValue(String value, Class<?> type) {
        if (type == String.class) {
            return value;
        }

        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        }

        if (type == byte.class || type == Byte.class) {
            return Byte.parseByte(value);
        }

        if (type == short.class || type == Short.class) {
            return Short.parseShort(value);
        }

        if (type == long.class || type == Long.class) {
            return Long.parseLong(value);
        }

        if (type == float.class || type == Float.class) {
            return Float.parseFloat(value);
        }

        if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        }

        if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        }

        if (type == char.class || type == Character.class) {
            if (value.length() != 1) {
                throw new IllegalArgumentException("Character must have exactly one sign");
            }
            return value.charAt(0);
        }

        throw new IllegalArgumentException("Unsupported type: " + type.getSimpleName());
    }
}