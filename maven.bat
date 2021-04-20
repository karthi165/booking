Rem Set the correct paths
set path=C:\Program Files (x86)\Java\zulu11.37.17-ca-jdk11.0.6-win_x64;%path%
set path=C:\Apache\apache-maven-3.6.3\bin;%path%
set path=C:\Program Files\Git\bin;%path%
set JAVA_HOME=C:\Program Files (x86)\Java\zulu11.37.17-ca-jdk11.0.6-win_x64
 

call mvn clean package -DskipTests=true
pause;





