# 🚀 Track Your Time

A full-stack, cloud-native time-management web application built with Java (Servlets/JSP) and deployed via a fully automated CI/CD pipeline.

# 🌐 Live Application

**Access the live deployed version here**:

👉 https://track-your-time.onrender.com

# 📸 Application Preview
<table> <tr> <td align="center"><strong>Home Page</strong></td> <td align="center"><strong>Responsive Activity Dashboard</strong></td> </tr> <tr> <td><img src="https://github.com/user-attachments/assets/6c9e875e-a83f-4bdb-b84b-34ab1dcee82f" alt="Home Page"></td> <td><img src="https://github.com/user-attachments/assets/fca1902d-974a-4177-9539-0242e9849725" alt="Activities Dashboard"></td> </tr> </table>

# ✨ Core Features

- **User Authentication** — Secure registration, login, and logout.

- **Activity Management** — Add, view, and track daily tasks.

- **Responsive Frontend** — Clean UI built with CSS, optimized for desktop and mobile.

- **Cloud-Native Architecture** — Database, app server, CI/CD — everything runs in the cloud.

# 🛠️ Tech Stack & Architecture
<table>
  <thead>
    <tr>
    <td>Category</td>
    <td>Technology</td>
    </tr>
  </thead>
  <tr>
    <td>Backend</td>
    <td>Java Servlets (Jakarta EE), JSP</td>
  </tr>
  <tr>
    <td>Database</td>
    <td>Aiven Cloud MySQL</td>
  </tr>
  <tr>
    <td>DB Pooling</td>
    <td>HikariCP</td>
  </tr>
  <tr>
    <td>Build Tool</td>
    <td>Maven</td>
  </tr>
  <tr>
    <td>Container</td>
    <td>Docker + Apache Tomcat</td>
  </tr>
  <tr>
    <td>CI/CD</td>
    <td>GitHub Actions</td>
  </tr>
  <tr>
    <td>Hosting</td>
    <td>	Render (PaaS)</td>
  </tr>
</table>


# 🏛️ Architecture Overview

This project follows the **Model-View-Controller (MVC)** pattern:

- **Model**: The DAOs (`UsersDAO`, `ActivitiesDAO`) and the ConnectionManager (which implements a Singleton pattern for the `HikariCP` pool).

- **View**: All JSPs are securely placed in the WEB-INF/views folder to prevent direct browser access.

- **Controller**: The Java Servlets (`LoginServlet`, `RegisterServlet`, `ActivityServlet`, etc.) handle all HTTP requests.


# 🔄 CI/CD Pipeline (GitHub Actions)
This project is fully automated. The `deploy.yml` workflow orchestrates the entire build and deployment process.

1. On `git push`: The workflow triggers on any push to the `main` branch.

2. CI Job (`build`):
  - Spins up an Ubuntu runner.
  - Sets up Java 21 and caches Maven dependencies.
  - Runs `mvn clean install` inside the project directory to compile all code and run tests, ensuring the build is stable.

3. CD Job (`deploy-to-render`):
  - This job `needs` the `build` job to succeed first, preventing broken code from being deployed.
  - It pings a secret `RENDER_DEPLOY_HOOK` (stored in GitHub Secrets).

4. Render's Server:
  - Receives the hook and pulls the latest code from `main`.
  - Builds the multi-stage Dockerfile, which:
    (Build Stage) Compiles the `.war` file using Maven.
    (Run Stage) Copies the `.war` file into a clean Tomcat server.
  - Launches the new container, making the changes live.

# 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. **Fork** the repository.
2. Create your Feature Branch (`git checkout -b feature/Feature`).
3. Commit your changes (`git commit -m 'Add Feature'`).
4. Push to the branch (`git push origin feature/Feature`).
5. Open a **Pull Request**.

# 🎯 Future Goals
- Add "Edit" and "Delete" functionality for activities.

- Add a dashboard with charts to visualize time spent.

# 📄 License
This project is distributed under the MIT License. See the `LICENSE` file for more information.
