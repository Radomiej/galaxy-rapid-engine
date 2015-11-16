Current version **0.0.1-SNAPSHOT**

Project is under **Apache License 2.0**

Install Maven:


```maven
<repositories>
	<repository>
		<id>maven-repo</id>
		<name>SilverRepo</name>
		<url>http://maven.ra-studio.ovh</url>
	</repository>
</repositories>

<dependencies>
	<dependency>
		<groupId>galaxy-rapid-framework</groupId>
	        <artifactId>galaxy-rapid-framework</artifactId>
	        <version>0.0.1-SNAPSHOT</version>
	</dependency>
</dependencies>
```


Install Gradle:


```gradle

allprojects {
    apply plugin: "eclipse"
    apply plugin: "idea"	

    version = '1.0'
    ext {
        appName = 'HelloEnglish'
        gdxVersion = '1.7.0'
        roboVMVersion = '1.9.0'
		galaxyRapidVersion = '0.0.1-SNAPSHOT'
    }

    repositories {
        mavenCentral()
	mavenLocal()
        maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
        maven { url "https://oss.sonatype.org/content/repositories/releases/" }
        maven { url "http://maven.ra-studio.ovh/" }
    }
}

```


```gradle
compile "galaxy-rapid-framework:galaxy-rapid-framework:$galaxyRapidVersion"

```
