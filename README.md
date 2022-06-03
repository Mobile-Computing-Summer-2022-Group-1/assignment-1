## How to start Flask web server

1. Go to **flask_web_server** directory
2. Create a folder **uploaded_files**
3. Run the following commands:

    Linux/OS X
    ```
    export FLASK_APP=upload
    flask run --host=0.0.0.0 --port=8085
    ```

    Windows
    ```
    set FLASK_APP=upload
    flask run --host=0.0.0.0 --port=8085
    ```
4. Open server URL on browser to verify