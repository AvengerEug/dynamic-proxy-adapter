# install thirdparty-1.0-SNAPSHOT.jar into maven local repository

mvn install:install-file -Dfile=./thirdparty-1.0-SNAPSHOT.jar -DgroupId=com.eugene.sumarry -DartifactId=thirdparty -Dversion=1.0-SNAPSHOT -Dpackaging=jar