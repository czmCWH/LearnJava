# 基础镜像
FROM openjdk:11.0-jre-buster
# 设定容器内部使用的时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
# 拷贝jar包，Dockerfile 文件需 和 docker-demo.jar 在同一个目录
COPY docker-demo.jar /app.jar
# 入口
ENTRYPOINT ["java", "-jar", "/app.jar"]