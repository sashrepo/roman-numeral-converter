exec java \
  -javaagent:/app/apm-agent.jar \
  -Delastic.apm.service_name=roman-numeral-converter \
  -Delastic.apm.server_url=http://apm-server:8200 \
  -Delastic.apm.application_packages=com.converter \
  -Delastic.apm.log_level=TRACE \
  -Delastic.apm.log_file=_AGENT_HOME_/logs/elastic-apm.log \
  -jar app.jar