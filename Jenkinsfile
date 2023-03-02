  pipeline {
    agent any
      tools{
    maven 'Maven'
    }
    triggers {
        cron('H H(8-15)/2 * * 1-5')
    }
   parameters {
        booleanParam(name: "testPassed", defaultValue: true)
	 booleanParam(name: "sonarPassed", defaultValue: true)
	 
    }
    stages {
       
          stage('Code Build') {
            steps {
                bat 'mvn clean'
            }
        }
        
       stage('Unit Test') {
            steps {
           
               
            
	        script {
			try {
				 bat 'mvn test'
			}
			catch (e){
                   unstable('Testing failed!')
				testPassed = false
				echo "Tests got failed"
                   }
        
       }
	    }}
       
	   
  
        
         stage('Sonar Qube') {
          
              		   steps {
			         script {
			try {
				     echo "Executing Sonar as tests passed"
                bat 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectKey=pipline -Dsonar.login=sqp_195a52fc52d28abfe766092c0c4a9a92b6b8f25a'
            
			}
			catch (e){
                   failure('SonarQube failed')
				sonarPassed = false
				echo "Tests got failed"
                   }
            
                }
            
    
            }
	 }
	    stage("Publish to Artifactory"){
	
            steps{
                  script {
			try {
			echo "Publishing to Artifactory as Sonar and tests passed"
                rtMavenDeployer(
                    id: 'deployer',
                    serverId: '12345@artifactory',
                    releaseRepo: 'priyaArtifactory',
                    snapshotRepo: 'priyaArtifactory'
                )
                rtMavenRun(
                    pom: 'pom.xml',
                    goals: 'clean install',
                    deployerId: 'deployer'
                    )
                rtPublishBuildInfo(
                    serverId:'12345@artifactory',
                )
			}
			catch (e){
                   failure('Publishing to Artifactory failed')
				
				echo "Error while publishing to Artifactory"
                   }
            
                }
                     
          
            }        
        }
    
    }
            post {
      
        success {
            echo "All tests , Sonar steps and Artifactory Steps got passed"
        }
        failure {
            echo 'There is some failure in the build'
        }
		
}
    }
    
