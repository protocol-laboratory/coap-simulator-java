package io.github.protocol.coap.model;

public class Payload {

    private TextType textType;

    private String text;

    public Payload(TextType textType, String text) {
        this.textType = textType;
        this.text = text;
    }

    public TextType getTextType() {
        return textType;
    }

    public void setTextType(TextType textType) {
        this.textType = textType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
