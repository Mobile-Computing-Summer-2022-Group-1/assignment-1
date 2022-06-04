Steps to start the server

1. Go to flask_web_server directory
2. create Virtual env: python3 -m venv <virtual_env_name>
3. activate the the virtual environment
	for Windows powershell: /path_to_virtual_env_folder/bin/activate
	for MacOS/Unix bash shell: source /path/to/venv/bin/activate

4. pip install -r requirements.txt

For Windows:
5. set FLASK_APP=upload
6. flask run --host=0.0.0.0 --port=8085

For Linux:
5. export FLASK_APP=upload
6. flask run --host=0.0.0.0 --port=8085