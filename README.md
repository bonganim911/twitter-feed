# Twitter User Activity Feeds

# Project Assumption:
  * The tweet file and user file will be past as arguments to the main.
    - In this order: tweet.txt and user.txt

# Project setup:

      ## Prerequisite:
      * Java 1.8
      
      * Gradle 2.0 
        - Avoid gradle wrapper if possible seems to be giving issues on Java 1.8 when running test.
        - after installing gradle run:
              gradle -v

              ------------------------------------------------------------
               Gradle 2.0
              ------------------------------------------------------------

              Build time:   2014-07-01 07:45:34 UTC
              Build number: none
              Revision:     b6ead6fa452dfdadec484059191eb641d817226c

              Groovy:       2.3.3
              Ant:          Apache Ant(TM) version 1.9.3 compiled on December 23 2013
              JVM:          1.8.0_65 (Oracle Corporation 25.65-b01)
              OS:           Mac OS X 10.10.5 x86_64
              
      * git (https://git-scm.com/book/en/v2/Getting-Started-Install-Git)
      
      * Terminal/Iterm


### Run project:

      1. git clone https://github.com/bonganim911/twitter-feed.git
      2. cd twitter-feed
      3. Terminal/Command line
        - gradle build (build project)
        - gradle test (running tests)
        - gradle runApplication (running the application)
      
