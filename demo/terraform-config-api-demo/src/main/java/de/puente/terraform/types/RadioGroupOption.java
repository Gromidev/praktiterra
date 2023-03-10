package de.puente.terraform.types;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Option zum Inputs in eine Konfiguration, die als Radio-Group dargestellt werden
 */
@Getter
@Setter
public class RadioGroupOption extends FormatInputOption {
    /**
     * Menge von Möglichen Auswahlen (= Radio Buttons) für diese Option
     */
    private List<RadioGroupOptionChoice> choices;
    public RadioGroupOption(String label, String name, List<RadioGroupOptionChoice> choices) {
        super("radio", label, name);
        this.choices = choices;
    }
}
