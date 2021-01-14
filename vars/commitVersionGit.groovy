def call(String version, String scope) {
  if (version != '') {      
    sh "git commit -a -m 'updating version to ${version}'"
    sh "git tag ${version}"
    sh 'git config --local credential.helper "!f() { echo username=$GIT_AUTH_USR; echo password=$GIT_AUTH_PSW; }; f"'
    sh "git push origin ${version}"
    sh "git config --local --unset credential.helper"
  }
}
