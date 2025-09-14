# .gitpod.Dockerfile
FROM gitpod/workspace-full

# Instalar dependencias necesarias
RUN sudo apt-get update && sudo apt-get install -y zip unzip
