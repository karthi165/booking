java  -Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8485 -Dservices.auth.context.prefix=authentication -Dauthentication-service.protocol=https -Dauthentication-service.url=https://oct23-env-as00.aa.teradata.com/authentication -Dauthentication-service.clientId=metadata -Dauthentication-service.clientSecret=secret -Dtdaa.database.hostname=oct23-env-tddb.aa.teradata.com -Dserver.protocol=https -Dtdaa.database.name=TACDB -Dtdaa.database.username=TACU -Dtdaa.database.password=perform -Dsecurity.client.id=metadata -Dsecurity.jwt.enabled=true -Dspring.cloud.vault.ssl.key-store -Dtdaa.service.name=connected-identity  -Dvault.enabled=false -Dserver.ssl.enabled=false -Dspring.application.name=connected-identity  -Dserver.servlet.context-path=/connected-identity  -Dsecurity.client.secret=secret -Dproxy=https://oct23-env-as00.aa.teradata.com -Dtdaa.proxy=https://oct23-env-as00.aa.teradata.com -Dtdaa.proxy.hostname=vcx-mdm-as00.aa.teradata.com -Dtdaa.rule.startup.migration.enabled=false  -DAUTHENTICATION_REDIRECT_URIS_INCLUDE=http://localhost:4200/**,https://localhost:4200/**,https://10.0.0.2:4200/** -DCORS_DEVELOPMENT=true -jar target\booking-0.0.1-SNAPSHOT.jar
pause;


