# 🚀 Syncro – Remote Team Productivity API

**Syncro** is a Spring Boot-powered microservice backend built for modern remote teams. It helps you manage users and tasks securely, with full JWT-based authentication, role-based access, and clean REST APIs. Oh, and it's Dockerized too. 🐳



## ✨ Features

- 🔐 **JWT Authentication** – Secure login, signup, and role-based access control (Spring Security + JWT)
- 🧰 **Modular Microservice Design** – User and Task services for easy scalability
- 🛡️ **Spring Security** – Protects your endpoints like a fortress
- 🧠 **JPA + PostgreSQL** – Easy, clean data access with Hibernate and Postgres
- 📦 **Dockerized** – Spin it up anywhere in seconds
- 🚀 **CI/CD Ready** – Built for smooth deployments



## 🛠️ Tech Stack

| Layer        | Technology               |
|--------------|--------------------------|
| Backend      | Spring Boot, Spring Security |
| Auth         | JWT (JSON Web Tokens)    |
| DB           | PostgreSQL + JPA (Hibernate) |
| DevOps       | Docker, CI/CD            |
| Testing      | JUnit, Postman           |



## 🔑 Authentication Flow

1. **Sign Up** – `POST /api/auth/register`  
2. **Log In** – `POST /api/auth/login`  
   ✅ Returns a **JWT Token**  
3. **Use JWT** – Include in request header:  
   `Authorization: Bearer <your_token_here>`  
4. **Access Protected Routes** – e.g., `GET /api/tasks`

**Note:** Passwords are securely hashed using **bcrypt** before storage.



## 📦 Running Locally (with Docker)

```bash
# Backend (Spring Boot + Postgres)
cd backend
docker-compose up --build
```

Then visit: [http://localhost:8080](http://localhost:8080)



## 📬 API Endpoints (Examples)

### 🔐 Auth
- `POST /api/auth/register` — Sign up a new user  
- `POST /api/auth/login` — Get JWT token

### 👤 Users
- `GET /api/users/me` — Get current user info (JWT required)  
- `GET /api/users/` — Admin-only: List all users

### ✅ Tasks
- `GET /api/tasks/` — List tasks for the current user  
- `POST /api/tasks/` — Create a new task  
- `DELETE /api/tasks/{id}` — Delete a task



## 🧪 Testing JWT in Postman

1. **Login** via `/api/auth/login` to get your JWT token.
2. In **Postman**, go to the **Authorization** tab.
3. Set Type to: `Bearer Token`
4. Paste your token and test any secured endpoints!



## 📸 Screenshots (Optional)

> Add Postman screenshots, Docker logs, or your favorite DB UI here to show it in action!  
> _(You can also include .gif walkthroughs for extra flair.)_



## 🙋🏻‍♂️ About the Author

Built with: 🌙 **Late nights** | 💻 **Love for clean code** | 🌐 **Belief in remote productivity**


Have questions or want to connect?  
Find me on [LinkedIn](https://www.linkedin.com/in/arvil-dey/) or contact me through my [Website](https://arvil-portfolio-swe.vercel.app/)!



## 📃 License

**MIT License** — Fork it, clone it, build on it, and make it your own!
