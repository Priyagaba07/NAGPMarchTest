  pipeline {
    agent any
      tools{
    maven 'Maven'
    }
    triggers {
        cron('H H(8-15)/2 * * 1-5')
    }
   parameters {

	 
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

				echo "Tests got failed"
                   }
        
       }
	    }}

    }

    }
    
