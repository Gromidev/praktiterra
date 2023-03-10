package de.puente.terraform.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Eine einzelne Auswahlmöglichkeit für den User zum Belegen einer RadioGroupOption
 */
@AllArgsConstructor
@Getter
@Setter
public class RadioGroupOptionChoice {
    /**
     * Name der Auswahlmöglichkeit, zum Zurücksenden an den Server
     */
    private String name;
    /**
     * Label der Auswahlmöglichkeit, zur Anzeige auf der Webseite
     */
    private String label;
}
