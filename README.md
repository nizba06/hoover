# Robotic Hoover Service

This project is a Spring Boot application that simulates a robotic hoover navigating through a grid-based room. 
The hoover follows instructions to move around the room and clean dirt patches.


## Setup

1. Clone the repository:

   ```bash
   
   
2. Build the project:
   mvn clean install

3. Run the application:
   mvn spring-boot:run

4. Using the API
   Endpoint: POST /api/hoover/navigate
   Content-Type: application/json

   curl -X POST http://localhost:8080/api/hoover/navigate \
   -H "Content-Type: application/json" \
   -d '{
   "roomSize": [5, 5],
   "coords": [1, 2],
   "patches": [[1, 0], [2, 2], [2, 3]],
   "instructions": "NNESEESWNWW"
   }'

5. Running Tests
   mvn test