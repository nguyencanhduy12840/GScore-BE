# GScore-BE

## Installation
1. Clone the repository:
   <pre> git clone https://github.com/nguyencanhduy12840/GScore-BE.git 
      cd GScore-BE 
   </pre>
2. Install Maven:
   Check if Maven is already installed:
   <pre>mvn -v</pre>
   If not, follow these steps:<br/>
   Install Maven:<br/>
   Download from: https://maven.apache.org/download.cgi<br/>
   Extract the archive<br/>
   Set the MAVEN_HOME environment variable<br/>
   Add MAVEN_HOME/bin to your system PATH<br/>
   After setup, run mvn -v again to verify installation.<br/>
3. Configure the Database:<br/>
   In src/main/resources/application.properties:<br/>
   <pre>spring.datasource.username=<your_username>
      spring.datasource.password=<your_password></pre>
   Replace your_username, and your_password with your actual database credentials.<br/>
4. Run the Project
   <pre>mvn spring-boot:run</pre>

## API Documentation
This project uses Swagger (via Springdoc/OpenAPI) to provide interactive API documentation for testing and exploring endpoints directly in the browser.
<pre>```http://localhost:8080/swagger-ui/index.html```</pre>


