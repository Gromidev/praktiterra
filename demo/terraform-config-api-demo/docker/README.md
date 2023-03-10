# Docker Validerung erzeugter Terraform Konfigurationen

Enthält Dockerfile zum Validieren der erzeugten Konfigurationen gegen das Terraform tool "terraform validate"

Benutzung: "docker-compuse run terraform"

Installiert Terraform in einem Docker Container und validiert dort alle in ../out/ erzeugten Konfigurations-Dateien