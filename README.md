# GScore-BE

## Installation
1. Clone the repository:<br/><br/>
   <pre>git clone https://github.com/nguyencanhduy12840/GScore-BE.git<br/>cd GScore-BE 
   </pre>
2. Install Maven:<br/><br/>
   Check if Maven is already installed:<br/>
   <pre>mvn -v</pre>
   - If not, follow these steps:<br/>
     - Install Maven:<br/>
     - Download from: https://maven.apache.org/download.cgi<br/>
     - Extract the archive<br/>
     - Set the MAVEN_HOME environment variable<br/>
     - Add MAVEN_HOME/bin to your system PATH<br/>
     - After setup, run mvn -v again to verify installation.<br/><br/>
3. Initialize Database:<br/><br/>
   - You can find the SQL script to initialize the database [GScore.sql](./GScore.sql)<br/>
   - Then download file SQL script and run it in MySQL Workbench.<br/><br/>
4. Configure the Database:<br/><br/>
   In src/main/resources/application.properties:<br/>
   <pre>spring.datasource.username=your_username<br/>spring.datasource.password=your_password</pre>
   Replace your_username, and your_password with your actual database credentials.<br/><br/>
5. Run the Project<br/><br/>
   <pre>mvn spring-boot:run</pre>

## API Documentation
This project uses Swagger (via Springdoc/OpenAPI) to provide interactive API documentation for testing and exploring endpoints directly in the browser.<br/>
<pre>http://localhost:8080/swagger-ui/index.html</pre>


