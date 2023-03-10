package de.puente.terraform.types;

import lombok.*;

/**
 * Nachricht an den User über Erfolg/Misserfolg einer Operation
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserMessage {
    /**
     * Typ der Nachricht (info / error) zur Unterscheidung im HTML-Template
     */
    private String level;
    /**
     * Text der Nachricht
     */
    private String message;

}
