LABEL authors="cristianosantosdesouza"

# Usar uma imagem base do Tomcat
FROM tomcat:10.1-jdk17-temurin

# Remover as aplicações padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar o arquivo WAR gerado para o diretório webapps do Tomcat
COPY target/desafio.war /usr/local/tomcat/webapps/ROOT.war

# Exponha a porta padrão do Tomcat
EXPOSE 8080

# Comando para iniciar o Tomcat
CMD ["catalina.sh", "run"]
