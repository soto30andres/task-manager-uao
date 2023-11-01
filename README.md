# Task manager API

El Backend de la aplicación está desarrollado en JAVA 17 utilizando el Framework Spring Boot.

## Requisitos

[Java 17](https://openjdk.org/projects/jdk/17/)

## Run services

1. Al haber Instalado Java 17, cree la variable de entorno **JAVA_HOME** con la ruta de donde se instalo el JDK de java, para el sistema operativo windows fue el siguiente:

    `C:\Program Files\Eclipse Adoptium\jdk-17.0.4.101-hotspot`

2. Clonar el repositorio:

    `git clone https://github.com/camilohreina/task-manager-app.git`

3. Abrir la consola y ubicarse en el repositorio clonado.

4. Ingresar a la carpeta **Backend/TaskManager** 

5. Ejecutar el comando para compilar y desplegar localmente los servicios

    `gradlew bootRun`

6. Ingresar a la siguiente URL para validar que se desplegó correctamente

    `http://localhost:8000/taskmanager`

## Login service

1. Para el efecto de pruebas, se creo un usuario por defecto para probar el inicio de sesión. Cree una petición con los siguientes parametros:

    `URL: http://localhost:8000/taskmanager/api/v1/authentication/signin` <br>
    `Metodo: POST` <br>
    `Payload:` 
    ```json 
    {
        "email": "andres@gmail.com",
        "password": "123456"
    }
    ```

2. El servicio le debe responser con el siguiente JSON: 
    ```json 
    {
        "token": "JWT_TOKEN_GENERADO"
    }
    ```

3. Para comprobar el token generado, por favor cree la siguiente petición:

    `URL: http://localhost:8000/taskmanager/api/v1/ping` <br>
    `Metodo: GET` <br>
    `Agregar el header Authorization con el token generado. Por ejemplo Authorization: Bearer JWT_TOKEN_GENERADO`
    

4. Si consume el servicio correctamente le debe retornar **PING** con un codigo http 200. De lo contrario, retornará un codigo http 403.
