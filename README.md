# Wanderers Blog

Welcome to Wanderers Blog, a platform where travel enthusiasts can connect, share their experiences, and find travel buddies. This README provides an overview of the "Wanderers Blog" site created using the Spring MVC framework.

## Features

The Wanderers Blog site offers the following features:

1. **User Authentication and Account Management:** Users can create an account, log in, and manage their profile. Account creation requires a unique username and a secure password. Users can update their profile information, including their bio and profile picture.

2. **Subscription System:** Users can subscribe to other accounts to receive updates on their posts and activities. This feature allows users to stay connected with their favorite travel bloggers and discover new content.

3. **Post Creation and Interaction:** Users can create posts to share their travel experiences, stories, and tips. They can add images and descriptions to their posts. Other users can like, comment on, and share these posts. The site also supports post categorization and searching by tags.

4. **Commenting and Messaging:** Users can comment on posts to engage in discussions and share their thoughts. Additionally, users can send private messages to each other, allowing them to communicate and plan trips together.

5. **Offer Creation for Travel Buddies:** Users can create offers to find travel buddies for specific destinations or trips. These offers include details such as destination, duration, preferred activities, and contact information. Interested users can connect with the offer creator to join the trip.

## Technology Stack

The Wanderers Blog site is built using the following technologies and frameworks:

- **Java:** The site is developed using the Java programming language, which provides robustness and reliability.

- **Spring MVC:** The Spring MVC framework is used for implementing the Model-View-Controller architectural pattern. It enables the development of scalable and maintainable web applications.

- **Spring Security:** Spring Security is utilized for user authentication, authorization, and securing sensitive endpoints.

- **Hibernate:** Hibernate is an object-relational mapping (ORM) library used to interact with the database. It simplifies database operations and provides an abstraction layer over the underlying database technology.

- **MySQL:** The MySQL database is used to store user account information, posts, comments, messages, and travel buddy offers.

- **HTML, CSS, and JavaScript:** The front-end of the site is built using HTML for structure, CSS for styling, and JavaScript for interactive behavior.

- **Thymeleaf:** Thymeleaf is used as the server-side Java template engine for rendering dynamic content on the webpages.

## Installation and Setup

To run the Wanderers Blog site locally, follow these steps:

1. Make sure you have Java JDK installed on your system.

2. Clone the repository:

   ```bash
   git clone https://github.com/your-username/wanderers-blog.git
   ```
3. Configure the MySQL database by creating a new database and updating the database connection settings in the application.properties file.

4. Build the project using Gradle or Maven.

5. Run the application using the provided command or script.

6. Access the site using a web browser:
	```bash
	http://localhost:8080
	```
	
## Project Structure

The project follows a standard Maven/Gradle project structure. The main directories and files include:

- `src/main/java`: Contains the Java source code for the project.

- `src/main/resources`: Contains configuration files, static resources, and HTML templates.

- `src/main/webapp`: Contains web-related resources such as CSS, JavaScript, and images.

- `pom.xml` : The project configuration file for Maven.

## Development Team

The Wanderers Blog site was developed by:
 - Jakub Kowalczyk
 - Aleksandra Wlazlo

