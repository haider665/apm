-- Gateway Service
java -javaagent:elk-agent/elastic-apm-agent.jar \
-Delastic.apm.service_name=gateway \
-Delastic.apm.api_key=NzJ5Ry1KUUI2am45OXlJZDkzQUs6eHItWDNVUnhTVy12ZUlNdTdyb1BpZw== \
-Delastic.apm.server_url=https://my-observability-project-cef419.apm.us-east-1.aws.elastic.cloud:443 \
-Delastic.apm.environment=local \
-Delastic.apm.application_packages=com.elk.apm.gateway \
-jar gateway/build/libs/gateway-0.0.1-SNAPSHOT.jar


-- Service A
java -javaagent:elk-agent/elastic-apm-agent.jar \
-Delastic.apm.service_name=service_a \
-Delastic.apm.api_key=NzJ5Ry1KUUI2am45OXlJZDkzQUs6eHItWDNVUnhTVy12ZUlNdTdyb1BpZw== \
-Delastic.apm.server_url=https://my-observability-project-cef419.apm.us-east-1.aws.elastic.cloud:443 \
-Delastic.apm.environment=local \
-Delastic.apm.application_packages=com.elk.apm.service_a \
-jar service_a/build/libs/service_a-0.0.1-SNAPSHOT.jar

-- Service B
java -javaagent:elk-agent/elastic-apm-agent.jar \
-Delastic.apm.service_name=service_b \
-Delastic.apm.api_key=NzJ5Ry1KUUI2am45OXlJZDkzQUs6eHItWDNVUnhTVy12ZUlNdTdyb1BpZw== \
-Delastic.apm.server_url=https://my-observability-project-cef419.apm.us-east-1.aws.elastic.cloud:443 \
-Delastic.apm.environment=local \
-Delastic.apm.application_packages=com.elk.apm.service_b \
-jar service_b/build/libs/service_b-0.0.1-SNAPSHOT.jar