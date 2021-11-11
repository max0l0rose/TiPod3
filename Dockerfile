#COPY . /usr/src/myapp
#WORKDIR /usr/src/myapp
#RUN javac Main.java
#CMD ["java", "Main"]

FROM alpine:latest
RUN apk add bash\
&& apk add openjdk11\
&& apk add maven\
&& apk add postgresql\
&& apk add mc\
&& apk add sudo\
&& apk add --no-cache curl
COPY . /myapp
RUN mkdir /usr/PGDATA
RUN chown postgres /usr/PGDATA
RUN echo export PDDATA=/usr/PGDATA > /etc/profile.d/my1.sh
RUN export PGDATA=/usr/PGDATA
ENV PGDATA=/usr/PGDATA
RUN echo "listen_addresses='*'" >> /usr/PGDATA/postgresql.auto.conf
#RUN set
#RUN echo $PGDATA

RUN sudo -u postgres pg_ctl -D /usr/PGDATA init
RUN sudo -u postgres pg_ctl -D /usr/PGDATA -o '-p5432' start
#ENTRYPOINT ["java", "--version"]
#ENTRYPOINT ["bash"]
