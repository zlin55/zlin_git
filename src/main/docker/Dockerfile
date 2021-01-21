#项目所依赖的jdk镜像
FROM java:8
VOLUME /tmp
#将maven构建好的jar添加到镜像中，第二个为别名
ADD demo.jar computer.jar
#暴露的端口号(和项目端口号等同)
EXPOSE 8090
#镜像所执行的命令
ENTRYPOINT ["java","-jar","/computer.jar"]