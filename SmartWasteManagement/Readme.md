\# Smart Waste Management System – Backend 🗑️🚛



🔗 Frontend Repository:  

👉 https://github.com/sudeshkar/SMW_frontend.git



---



\## 📌 Overview

This repository contains the \*\*backend implementation\*\* of the Smart Waste Management System, developed using \*\*Spring Boot\*\*.  

The project was inspired by real waste collection challenges in \*\*Kandy, Sri Lanka\*\*, where unpredictable truck schedules often cause inconvenience to citizens.



The backend provides \*\*secure REST APIs\*\*, \*\*role-based access control\*\*, \*\*IoT data handling\*\*, and \*\*analytics support\*\* for a smart city waste management solution.



---



\## 🔐 Security \& Authentication

\- JWT Authentication (Access Token + Refresh Token)

\- Role-Based Access Control (Admin / Driver / Citizen)

\- OTP-based user registration using \*\*Java Mail Starter\*\*

\- Spring Security for API protection



---



\## 🧠 Core Backend Features

\- RESTful API architecture

\- Layered design (Controller → Service → Repository)

\- Secure API routing based on user roles

\- IoT sensor data ingestion (simulated)

\- Alert and analytics support

\- PostgreSQL database integration



---



\## 🌐 IoT Simulation

Since physical IoT hardware is not yet integrated, the system includes \*\*IoT simulation APIs\*\* to represent real-time sensor behavior.



\### Simulation Endpoints

| Endpoint | Description |

|--------|------------|

| `/api/iot/start-simulation` | Start continuous sensor data generation |

| `/api/iot/stop-simulation` | Stop simulation |

| `/api/iot/generate-one` | Generate a single sensor reading |



> The architecture is designed to support \*\*real IoT device integration\*\* (GPS, fill-level sensors, MQTT/HTTP) in the future.



---



\## 🏗️ Backend Architecture



```mermaid

flowchart TB



&nbsp;   Client\[React Admin / Mobile App]

&nbsp;   API\[Spring Boot REST API]

&nbsp;   Security\[JWT + RBAC]

&nbsp;   Service\[Service Layer]

&nbsp;   Repository\[Repository Layer]

&nbsp;   DB\[(PostgreSQL)]

&nbsp;   IoTSim\[IoT Simulator]



&nbsp;   Client --> API

&nbsp;   API --> Security

&nbsp;   API --> Service

&nbsp;   Service --> Repository

&nbsp;   Repository --> DB

&nbsp;   IoTSim --> API



