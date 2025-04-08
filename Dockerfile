# Utiliser une image de base avec JDK 8
FROM openjdk:8-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR/WAR de l'application
COPY ceminace-college.war /app/ceminace-college.war

# Copier le dossier et ke jar de Payara Micro
COPY payara-micro-5.2022.5.jar /app/payara-micro.jar
#COPY payara-micro /app/payara-micro

# Télécharger et extraire Payara Micro
#RUN wget https://repo1.maven.org/maven2/fish/payara/extras/payara-micro/5.2022.5/payara-micro-5.2022.5.jar -O payara-micro.jar

# Exposer le port sur lequel Payara Micro écoute (par défaut 8080)
EXPOSE 8080

# Commande pour démarrer Payara Micro et déployer l'application
CMD ["java", "-jar", "payara-micro.jar", "--rootDir", "./payara-micro", "--deploy", "ceminace-college.war", "--port", "8080"]