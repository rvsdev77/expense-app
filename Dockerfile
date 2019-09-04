FROM centos

RUN yum install -y java

VOLUME /tmp

ADD /build/libs/expense-app-0.0.1-SNAPSHOT.war expense-app.war
RUN sh -c 'touch /expense-app.war'


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", "/expense-app.war"]