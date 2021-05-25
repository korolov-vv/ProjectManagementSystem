package ua.goit.dao.model;

import lombok.Getter;

@Getter
public enum Stack {
    JAVA("Java"),
    CPLUS("C++"),
    CSHARP("C#"),
    JS("JS");

    private String value;

    Stack(String value) {
        this.value = value;
    }
}
