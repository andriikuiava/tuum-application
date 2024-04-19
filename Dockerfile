FROM ubuntu:latest
LABEL authors="andriikuiava"

ENTRYPOINT ["top", "-b"]