package de.puente.terraform.web;

import de.puente.terraform.services.TerraformFormatService;
import de.puente.terraform.types.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller für Interaktionen des Browsers (= der Website) mit dem Server
 */
@Controller
@RequestMapping("terraform")
public class TerraformController {

    /**
     * Optionen, die dem Nutzer im Formular zur Auswahl gestellt werden
     */
    public static final List<FormatInputOption> FORMAT_INPUT_OPTIONS = List.of(
            new RadioGroupOption("cpuCount", "Wie viele CPUs", List.of(
                    new RadioGroupOptionChoice("1", "1"),
                    new RadioGroupOptionChoice("2", "2"),
                    new RadioGroupOptionChoice("4", "4"),
                    new RadioGroupOptionChoice("8", "8"))),
            new RadioGroupOption("memorySize", "Wie viel Arbeitsspeicher", List.of(
                    new RadioGroupOptionChoice("1024", "1 GB"),
                    new RadioGroupOptionChoice("4096", "4 GB"),
                    new RadioGroupOptionChoice("16384", "16 GB"))),
            new RadioGroupOption("diskSize", "Wie groß soll die Festplatte sein", List.of(
                    new RadioGroupOptionChoice("20", "20 GB"),
                    new RadioGroupOptionChoice("100", "100 GB"))),
            new TextOption("diskLabel", "Bezeichnung der ersten Festplatte", "disk-0"),
            new RadioGroupOption("secondaryDiskSize", "Wie groß soll die zweite Festplatte sein", List.of(
                    new RadioGroupOptionChoice("0", "Keine zweite Festplatte"),
                    new RadioGroupOptionChoice("20", "20 GB"),
                    new RadioGroupOptionChoice("100", "100 GB"))),
            new TextOption("secondaryDiskLabel", "Bezeichnung der zweiten Festplatte", "disk-1")
    );

    /**
     * Referenz auf Format-Service
     */
    @Autowired
    TerraformFormatService service;

    /**
     * Erzeugt die Website in Form einer View (html aus den Templates) und eines Models (Key-Value-Paare)
     */
    private ModelAndView createFormView() {
        // View
        return new ModelAndView("create-form")
                // Welche Optionen dem User angeboten werden
                .addObject("options", FORMAT_INPUT_OPTIONS);
    }

    /**
     * wie auf Anfragen nach /terraform/create regiert wird
     */
    @GetMapping("create")
    public ModelAndView getCreateForm() {
        // einfach Webseite anzeigen
        return createFormView();
    }

    /**
     * Wie auf Absenden des Formulars reagiert wird
     */
    @PostMapping(value = "create")
    public ModelAndView postCreateForm(
            // Es werden valide FormatInputs als Formular-Parameter erwartet
            @ModelAttribute @Valid FormatInputs formatInputs) throws IOException {
        // Formatieren und Speichern
        String configurationName = service.formatAndSave(formatInputs);
        // Webseite anzeigen
        return createFormView()
                // mit Hinweis auf Erfolg
                .addObject("messages", List.of(
                        new UserMessage("info", "successfully created configuration with name " + configurationName))
                );
    }

    /**
     * Wie auf Fehler beim Validieren von Daten (= FormatInputs) reagiert wird
     */
    @ExceptionHandler(BindException.class)
    public ModelAndView handleBindException(BindException exception) {
        // Pro Fehler mit den Input-Daten einen Hinweis geben
        List<UserMessage> messages = new ArrayList<>();
        for (FieldError error : exception.getFieldErrors()) {
            messages.add(new UserMessage("error", error.getField() + " " + error.getDefaultMessage()));
        }
        // Webseite anzeigen
        return createFormView()
                // mit Fehlern
                .addObject("messages", messages);
    }

    /**
     * Wie auf Fehler beim Lesen/Schreiben der Konfiguration reagiert wird
     */
    @ExceptionHandler(IOException.class)
    public ModelAndView handleIoException(IOException exception) {
        // Webseite anzeigen
        return createFormView()
                // mit Fehler
                .addObject("messages", List.of(new UserMessage("error", "Fehler beim Lesen oder Schreiben der Konfigurationsdatei - " + exception.getMessage())));
    }
}
