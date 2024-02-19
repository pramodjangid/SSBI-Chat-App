# ChatApp Project

## Overview

ChatApp is a real-time chat application designed to facilitate communication between users. It provides a platform for users to exchange messages in real-time over WebSocket connections. The project aims to create a seamless and user-friendly chatting experience.

## Installation

To install and set up the ChatApp project locally, follow these steps:

1. Clone the project repository from GitHub:
   ```
   https://github.com/pramodjangid/SSBI-Chat-App.git
   ```

2. Navigate to the project directory:
   ```
   cd SSBI-Chat-App
   ```

3. Install project dependencies using Maven:
   ```
   mvn clean install
   ```

4. Configure the database settings in `application.properties` file.

5. Run the application:
   ```
   mvn spring-boot:run
   ```

## Usage

Once the ChatApp project is set up and running, users can access the chat application using a web browser. Here's how to use the application:

1. Open a web browser and navigate to `http://localhost:9090`.
2. Enter your username and start chatting with other users.
3. Users can send messages, view chat history, and receive real-time updates.

## Features

The ChatApp project offers the following features:

- Real-time messaging
- User authentication
- Chat history
- Backend User management

## API Documentation

### Get All Users

- **URL**: `/admin/users`
- **Method**: `GET`
- **Description**: Retrieves a list of all users registered in the chat application.
- **Response**: List of strings containing usernames.

### Get Messages by Username

- **URL**: `/admin/messages/{username}`
- **Method**: `GET`
- **Description**: Retrieves messages sent by a specific user.
- **Parameters**: 
  - `username`: Username of the user whose messages are to be retrieved.
- **Response**: List of strings containing messages sent by the specified user.

### Delete Message by Username

- **URL**: `/admin/messages/{username}`
- **Method**: `DELETE`
- **Description**: Deletes all messages sent by a specific user.
- **Parameters**:
  - `username`: Username of the user whose messages are to be deleted.
- **Response**: No content.

### Delete Message by ID and Username

- **URL**: `/admin/messages/{id}/{username}`
- **Method**: `DELETE`
- **Description**: Deletes a specific message sent by a user.
- **Parameters**:
  - `id`: ID of the message to be deleted.
  - `username`: Username of the user who sent the message.
- **Response**: String indicating the success of the operation.

### Update Message by ID

- **URL**: `/admin/messages/{id}`
- **Method**: `PUT`
- **Description**: Updates a specific message by its ID.
- **Parameters**:
  - `id`: ID of the message to be updated.
- **Request Body**: JSON object containing the updated message details.
- **Response**: Updated ChatMessage object.

## Testing

The ChatApp project includes unit tests to ensure functionality and reliability. Testing is performed using JUnit, and dummy objects are used where necessary.

To run the tests, execute the following command:
```
mvn test
```

## Contributing

Contributions to the ChatApp project are welcome! Here's how you can contribute:

1. Report bugs and issues by opening GitHub issues.
2. Suggest new features or improvements.
3. Submit pull requests for bug fixes or new features.
4. Follow coding standards and guidelines outlined in the project.


## Contact

For questions, feedback, or support, please contact [jangidpramod99@gmail.com] or visit our GitHub repository.

## TechStack Used

- Java Spring Boot
- WebSocket
- HTML/CSS/JavaScript
- JUnit
