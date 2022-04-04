# qs3

<h3>By William G. Tresselt and Olav F. P. Larsen</h3>

To run this application you need to run two different program (frontend and backend).
You also need to be connected to the NTNU network, either with a VPN (Cisco) or directly.

<h2>Running frontend</h2>
To run the frontend app you need to be the frontend directory. It is important that you have NPM and Node.js installed. 
In the terminal run:

```bash
npm install
```

then
```bash
npm run serve
```
To run the application.
To run the different frontend tests use the command:
```bash
npm run test:unit
```
We have used Lint with prettify for good looking code. If you have problems with lint errors please run:
```bash
npm run lint -- --fix
```
The application should be running on http://localhost:8080/

<h2>Backend</h2>
As mentioned before it is important that you have access to NTNUs local network to be able to connect to the database.
To run the backend server make sure that you have maven installed. After installing maven you can run:

```bash
mvn spring-boot:run
```

To run the backend server. The server should be running on http://localhost:8085.

For API please read JavaDoc and/or visit http://localhost:8085/swagger-ui.html
