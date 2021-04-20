pipeline {
   agent any
//    environment {
//        // use your actual issuer URL here and NOT the placeholder {yourOktaDomain}
//        OKTA_OAUTH2_ISSUER           = '{yourOktaDomain}/oauth2/default'
//        OKTA_OAUTH2_CLIENT_ID        = credentials('OKTA_OAUTH2_CLIENT_ID')
//        OKTA_OAUTH2_CLIENT_SECRET    = credentials('OKTA_OAUTH2_CLIENT_SECRET')
//    }

   stages {
      stage('Build') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/niceGeek/quarkus-sse.git'

            // Run Maven on a Unix agent.
            //sh "./mvnw -Dmaven.test.failure.ignore=true clean package"

             // To run Maven on a Windows agent, use
            bat "mvn clean package"
         }

//          post {
//             // If Maven was able to run the tests, even if some of the test
//             // failed, record the test results and archive the jar file.
//             success {
//                junit '**/target/surefire-reports/TEST-*.xml'
//                archiveArtifacts 'target/*.jar'
//             }
//          }
      }

      stage('Run all tests') {
          steps {
                      // Get some code from a GitHub repository
                      git 'https://github.com/niceGeek/quarkus-sse.git'

                      // Run unit and integration tests.
                      bat "mvn clean verify"
                   }
      }

       stage('Deploy') {
        steps {
            echo "Deployment should happen in this stage!"
        }

            }
   }
}