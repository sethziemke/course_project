FROM openjdk:17-jdk-oraclelinux7
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
ENV DISPLAY=localhost:0.0
RUN yum install -y \
    libXext.x86_64 \
    libXrender.x86_64 \
    libXtst.x86_64
CMD ["java", "-jar", "--enable-preview", "FrontEnd.jar"]