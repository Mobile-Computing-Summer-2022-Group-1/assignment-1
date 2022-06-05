## How to start Flask web server

1. Go to **flask_web_server** directory
2. Create a python-3 venv: 
   
   ```
   python3 -m venv venv
   ```
3. Activate virtual environment

   MacOS/Unix:
   ```
   source venv/bin/activate
   ```
   
   Windows:
   ```
   /path_to_virtual_env_folder/bin/activate
   ```
5. Install all the dependencies in **requirements.txt**:
   
   ```pip install -r requirements.txt```
4. Create a folder **uploaded_files**
5. Run the following commands:

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
6. Open server URL on browser to verify
