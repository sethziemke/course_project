FROM openjdk:17-jdk-oraclelinux7
COPY FrontEnd.jar /usr/src/
WORKDIR /usr/src
ENV DISPLAY=172.26.32.1:0.0
RUN yum install -y \
    libXext.x86_64 \
    libXrender.x86_64 \
    libXtst.x86_64
CMD ["java", "-jar", "--enable-preview", "FrontEnd.jar"]