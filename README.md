# Task Management System using Microservices

## Overview

This project implements a Task Management system using microservices architecture. The system consists of three main microservices: UserService, TaskService, and TaskSubmissionService. Additionally, Eureka is used for service discovery, and an API Gateway is implemented to manage API requests.

## Microservices Architecture
![Task Management System Architecture](https://github.com/iammahesh123/Task-Management-System/raw/master/tms.png)

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Cloud
- API Gateway (Spring Cloud Routing)
- MongoDB 
- Microservices Architecture

## Project Structure

- `service-1`: User Service
- `service-2`: Task Service
- `service-3`: Task Submission
- `service-4`: Server configuration
- `api-gateway`: API Gateway

### UserService

The `UserService` microservice is responsible for managing user-related operations within the Task Management system. It focuses on user registration, authentication, and profile management.

#### Features:

- **User Registration:**
  - Endpoint: `POST auth/signup`
  - Allows new users to register by providing necessary details.
  - Utilizes web security to ensure secure registration.

- **User Authentication:**
  - Endpoint: `POST auth/signin`
  - Implements web security and JWT token authentication for secure user login.
  - Generates a JWT token upon successful authentication.

- **Get User Details:**
  - Endpoint: `GET /api/user/profile`
  - Retrieves user details based on the provided user ID.

#### Security:

The `UserService` employs web security to safeguard user registration and login processes. JWT token authentication is implemented to ensure secure communication and user authentication. This ensures that only authorized users can access the system's functionalities.

#### Usage:

- This microservice is an essential component for user management in the overall Task Management system.
- It provides the necessary endpoints for user registration, authentication, and fetching user details.

### TaskService

The `TaskService` microservice is dedicated to managing tasks within the Task Management system. It facilitates the creation of tasks, assignment to users, and retrieval of task details.

#### Features:

- **Create a New Task (Admin Only):**
  - Endpoint: `POST /api/tasks/create`
  - Allows administrators to create new tasks by providing task details.
  - Only users with admin privileges can access this endpoint.

- **Get Task Details:**
  - Endpoint: `GET /api/tasks/{taskId}`
  - Retrieves detailed information about a specific task based on the provided task ID.

- **Assign Task to User (Admin Only):**
  - Endpoint: `PUT /api/tasks/{taskId}/assign/{userId}`
  - Permits administrators to assign a task to a specific user.
  - Requires admin privileges for access.

#### Security:

- Task creation and assignment operations are restricted to users with admin privileges.
- This ensures that only authorized administrators can create tasks and assign them to users.

#### Usage:

- The `TaskService` plays a vital role in task management within the overall Task Management system.
- It offers endpoints for creating tasks, assigning tasks to users, and fetching detailed task information.
- Admin privileges are required for task creation and assignment, emphasizing role-based access control.

### TaskSubmissionService

The `TaskSubmissionService` microservice is dedicated to handling the submission of completed tasks within the Task Management system. It provides functionality for users to submit their completed tasks and retrieve details about task submissions.

#### Features:

- **Submit Completed Task:**
  - Endpoint: `POST /api/submissions/submit`
  - Allows users to submit completed tasks, providing necessary details about the submission.

- **Get Submission Details:**
  - Endpoint: `GET /api/submissions/{submissionId}`
  - Retrieves detailed information about a specific task submission based on the provided submission ID.

#### Security:

- Submission of completed tasks is open to all authenticated users.
- Authentication ensures that only authorized users can submit their completed tasks.

#### Usage:

- The `TaskSubmissionService` facilitates the submission of completed tasks, enhancing the user experience within the Task Management system.
- Users can utilize the provided endpoints to submit their completed tasks and retrieve details about their submissions.

### Eureka Server Configuration

The `Eureka Server` is a crucial component that enables service discovery within the Task Management system. It efficiently manages the UserService, TaskService, and TaskSubmissionService, allowing seamless communication between microservices.

#### Service Discovery:

- **Purpose:**
  - Eureka Server facilitates service discovery, allowing microservices to register and discover each other dynamically.

- **Usage:**
  - Each microservice (UserService, TaskService, TaskSubmissionService) registers itself with the Eureka Server during startup.
  - Other microservices can discover and communicate with registered services using Eureka's service registry.

### OpenFeign for Microservice Communication

The `OpenFeign` library is employed to simplify communication between microservices in the Task Management system. It offers a declarative way to define web service clients.

#### Communication Features:

- **Declarative REST Clients:**
  - OpenFeign allows the definition of declarative REST clients using annotations, reducing boilerplate code.

- **Microservice Communication:**
  - Microservices, such as the API Gateway, can utilize OpenFeign to communicate with other services seamlessly.

#### Usage:

- Microservices can define Feign clients, specifying the target service and its API endpoints using annotated interfaces.
- This simplifies the communication process, enhancing the maintainability of the system.

### API Gateway

The `API Gateway` serves as the single entry point for the Task Management system, directing and managing requests to the corresponding microservices. This component is implemented using Spring Cloud Gateway for efficient routing.

#### Purpose:

- **Routing Requests:**
  - Routes incoming requests to the appropriate microservices based on predefined patterns.

- **Centralized Entry Point:**
  - Provides a centralized entry point for client applications to interact with the system.

#### Configuration:

- **Route Configuration:**
  - Utilizes Spring Cloud Gateway to define routes for UserService, TaskService, and TaskSubmissionService.
  - Enables seamless communication between microservices.

#### Security:

- **Endpoint Protection:**
  - Implements security measures to protect API endpoints.
  - Enforces authentication and authorization as configured in the microservices.

#### Usage:

- **Single Port Access:**
  - The API Gateway is configured to run on a single port (e.g., 8080).
  - Microservices communicate through the gateway using defined routes.

- **Simplified Communication:**
  - Enhances the simplicity of communication between client applications and microservices.
  - Utilizes Spring Cloud Gateway's flexibility for routing and load balancing.
## Final Endpoints
![Task Management System Architecture](https://github.com/iammahesh123/Task-Management-System/raw/master/endpoints.png)


## Getting Started

Welcome to the Task Management microservices project! Follow these steps to set up and run the project on your local machine.

### Step 1: Clone the Project

1. Open a terminal on your computer.

2. Clone the project repository by running the following command:
   ```bash
   git clone [<repository-url>](https://github.com/iammahesh123/Task-Management-System-using-microservices.git)
   cd Task-Management-System-using-microservices

### Step 2: Run Eureka Server

1. Navigate to the Eureka directory:.

2. Start the Eureka Server using the following command::
   ```bash
   cd EurekaServerConfiguration
   ./gradlew bootRun
3.Open your web browser and visit http://localhost:8081 to access the Eureka Server dashboard.
### Step 3: Run Microservices
For each microservice (UserService, TaskService, TaskSubmissionService), navigate to its directory and run it. Here's an example for UserService:
1. Navigate to the Eureka directory:.
2. Start the Eureka Server using the following command::
   ```bash
   cd UserService
   ./gradlew bootRun
3.Repeat the process for TaskService and TaskSubmissionService.
### Step 4: Run API Gateway
For each microservice (UserService, TaskService, TaskSubmissionService), navigate to its directory and run it. Here's an example for UserService:
1. Navigate to the API Gateway directory:
2. Start the API Gateway using the following command:
   ```bash
   cd APIGateway
   ./gradlew bootRun
3.Repeat the process for TaskService and TaskSubmissionService.

### Step 5: Verify Local Setup
Open your web browser and visit http://localhost:8089 to access the API Gateway.
You've successfully set up the Task Management microservices project on your local machine! Feel free to explore and test the functionalities.
Feel free to copy and paste this version into your README file. Let me know if there's anything else I can help you with!
## Contributions

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
