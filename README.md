🌀 Syncro – Remote Team Productivity API

Syncro is a modern backend system designed for managing users and tasks in distributed teams. It showcases a clean, secure, and scalable Spring Boot microservices architecture, built with real-world DevOps practices in mind.

🔧 Tech Stack
Java 17, Spring Boot 3

Spring Security + JWT

PostgreSQL, Maven

Docker, Docker Compose

GitHub Actions (CI/CD)

(Optional) Spring Cloud Gateway

📦 Microservices
👤 User Service
Register and log in users

Role-based access control

Issues JWT tokens for authenticated access

✅ Task Service
Create, read, update, delete tasks

Secures access using JWT

Ensures task ownership and authorization

🛡️ Security
Passwords encrypted with BCrypt

Stateless authentication using JWT

Spring Security filters for protection

🚀 Getting Started (Local)
bash
Copy code
# Clone and navigate
git clone https://github.com/your-username/syncro.git
cd syncro

# Build each service
cd user-service && mvn clean install
cd ../task-service && mvn clean install

# Run everything with Docker
docker-compose up --build
localhost:8081 → User Service

localhost:8082 → Task Service

📂 Project Structure
sql
Copy code
syncro/
├── user-service/
├── task-service/
├── docker-compose.yml
└── README.md
🧪 CI/CD
Built with GitHub Actions to automate:

Code build & test

(Optional) Docker image builds

(Optional) Deployments to Railway or AWS

🌱 Future Improvements
OAuth2 login (Google, GitHub)

Spring Cloud Gateway routing

Frontend integration (Next.js or React)

Notification & label system for tasks

👋 About
Built with ☕ & focus by Arvil.
Feel free to fork, explore, or contribute!
