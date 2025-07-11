# GScore-BE

## Installation

1. Clone the repository:

```bash
git clone https://github.com/nguyencanhduy12840/GScore-BE.git
cd GScore-BE
2. Install Maven:
   Check if Maven is already installed:
   <pre>mvn -v</pre>
   If not, follow these steps:
   Install Maven:
   Download from: https://maven.apache.org/download.cgi
   Extract the archive
   Set the MAVEN_HOME environment variable
   Add MAVEN_HOME/bin to your system PATH
   After setup, run mvn -v again to verify installation.
3. Configure the Database:
   In src/main/resources/application.properties:
   <pre> ```spring.datasource.username=<your_username>
            spring.datasource.password=<your_password>``` </pre>
4. Run the Project
   <pre> ```mvn spring-boot:run``` </pre>

## API Documentation
This project uses Swagger (via Springdoc/OpenAPI) to provide interactive API documentation for testing and exploring endpoints directly in the browser.
<pre>```http://localhost:8080/swagger-ui/index.html```</pre>


