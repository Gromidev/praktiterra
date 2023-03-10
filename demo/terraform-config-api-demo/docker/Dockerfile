# terraform docker-image von hashicorp (minimales linux "alpine", terraform installiert)
FROM hashicorp/terraform:latest

# installiere bash
RUN apk update
RUN apk add bash

# interactive (debug)
#WORKDIR /root/confs
#ENTRYPOINT [ "bash" ]

# non-interactive
WORKDIR /root/confs
ENTRYPOINT [ "terraform", "validate" ]