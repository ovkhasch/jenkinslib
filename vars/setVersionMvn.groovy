def call(String version, String scope) {
  if (version != '') {
    major = scope == 'major' ? 'true' : 'false'
    minor = scope == 'major' || scope == 'minor' ? 'true' : 'false'
    sh "mvn versions:set -DnewVersion=${version} -Dincludes=com.kaiburr:*,org.cloudply:* -s settings.xml"
    sh "mvn versions:use-latest-versions -DallowMajorUpdates=${major} -DallowMinorUpdates=${minor}  -Dincludes=org.cloudply:*,com.kaiburr:* -s settings.xml"
  }
}
